package com.scope.challenge;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.scope.challenge.configurations.ChallengeConfigurations;
import com.scope.challenge.model.Talk;
import com.scope.challenge.model.Track;
import com.scope.challenge.services.ITalkSchedulerService;
import com.scope.challenge.utility.FileUtility;

/**
 * We have used @SpringBootApplication annotation to enable auto configuration.
 * Spring boot will automatically call the run method of this class after the
 * application context has been loaded since this class implements
 * CommandLineRunner interface.
 * 
 * @author Mohit.Saluja
 */
@SpringBootApplication
public class ChallengeApplication implements CommandLineRunner {

	@Autowired
	private ChallengeConfigurations challengeConfigurations;

	@Autowired
	private ITalkSchedulerService talkSchedulerService;

	private static Logger logger = LoggerFactory.getLogger(ChallengeApplication.class);

	public static void main(String[] args) {
		logger.info("STARTING CHALLENGE APPLICATION");
		SpringApplication.run(ChallengeApplication.class, args);
		logger.info("CHALLENGE APPLICATION EXECUTION FINISHED");
	}

	/**
	 * Spring boot will automatically call the run method of this class after
	 * the application context has been loaded since this class implements
	 * CommandLineRunner interface.
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("run method -- start");

		try (Scanner scanner = new Scanner(System.in)) {

			logger.info("PLEASE PROVIDE INPUT FILE'S FILENAME AND ABSOLUTE PATH. e.g /Users/Mohit.Saluja/Desktop/scope/Sessions.txt");
			String inputFile = scanner.next();

			logger.info("PLEASE PROVIDE OUTPUT FILE'S FILENAME AND ABSOLUTE PATH. e.g /Users/Mohit.Saluja/Desktop/scope/Sessions-output.txt");
			String outputFile = scanner.next();

			// read file available at provided path using FileUtility.readFile
			List<String> input = FileUtility.readFile(inputFile);

			// parse input talks data and create list of talks which are sorted
			// by duration in descending order
			List<Talk> talks = parseAndSortTalks(input);

			// call talkSchedulerService.schedule to schedule input talks in
			// tracks
			List<Track> tracks = talkSchedulerService.schedule(talks);

			// iterate through talks and prepare desired output
			List<String> output = prepareOutput(tracks);

			// write output to provided output file
			FileUtility.writeFile(output, outputFile);
		}
		logger.info("run method -- end");
	}

	/**
	 * This method iterates through tracks and prepares output
	 * 
	 * @param tracks
	 * @return
	 */
	private List<String> prepareOutput(List<Track> tracks) {
		List<String> output = new ArrayList<>();
		int i = 0;
		// Date time pattern to format talk's time
		final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_H_MM_A);

		for (Iterator<Track> iterator = tracks.iterator(); iterator.hasNext();) {
			Track track = iterator.next();

			// add track's number
			output.add(TRACK_PLUS_SPACE + ++i + COLON_STRING);

			// get track's start time from configurations
			LocalTime time = LocalTime.of(challengeConfigurations.getMorningStartHour(), challengeConfigurations.getMorningStartMinutes());

			// iterate through morning talks of track
			for (Iterator<Talk> talksIterator = track.getMorningTalks().iterator(); talksIterator.hasNext();) {
				Talk talk = talksIterator.next();
				// add formatted talk's start time and talk's name
				output.add(timeFormatter.format(time) + STRING_WITH_SPACE + talk.getName());

				// add talk's duration
				time = time.plusMinutes(talk.getDuration());
			}

			// get lunch start time from configuration
			time = LocalTime.of(challengeConfigurations.getLunchHour(), challengeConfigurations.getLunchMinutes());
			// add lunch's formatted time and "lunch" to output
			output.add(timeFormatter.format(time) + STRING_WITH_SPACE + LUNCH);

			// get lunch's duration from configuration and add it to get lunch's
			// end time
			time = time.plusMinutes(challengeConfigurations.getLunchDuration());

			// iterate through afternoon talks of track
			for (Iterator<Talk> talksIterator = track.getAfternoonTalks().iterator(); talksIterator.hasNext();) {
				Talk talk = talksIterator.next();

				// add formatted talk's start time and talk's name
				output.add(timeFormatter.format(time) + STRING_WITH_SPACE + talk.getName());

				// add talk's duration
				time = time.plusMinutes(talk.getDuration());
			}

			// get meet your colleagues earliest start time from configuration
			LocalTime meetYourColleaguesEarliestStartTime = LocalTime.of(challengeConfigurations.getMeetYourColleaguesEarliestStartHour(), challengeConfigurations.getMeetYourColleaguesEarliestStartMinutes());

			// in case end time of last talk is greater then meet your
			// colleagues earliest start time then it's fine else change time to
			// meetYourColleaguesEarliestStartTime
			time = time.compareTo(meetYourColleaguesEarliestStartTime) >= 0 ? time : meetYourColleaguesEarliestStartTime;

			// add Meet Your Colleagues Event's formatted time and "Meet Your
			// Colleagues Event" to output
			output.add(timeFormatter.format(time) + STRING_WITH_SPACE + MEET_YOUR_COLLEAGUES_EVENT);
			// add a line break
			output.add(EMPTY_STRING);
		}

		return output;
	}

	/**
	 * This method parses input talks data and returns sorted Talks list having
	 * talk name and duration
	 * 
	 * @param input
	 * @return
	 */
	private List<Talk> parseAndSortTalks(List<String> input) {
		List<Talk> talks = new ArrayList<>();
		int i = 0;
		// iterate input talks
		for (Iterator<String> iterator = input.iterator(); iterator.hasNext();) {
			String talk = iterator.next();

			// get duration of talk from input
			String durationString = talk.substring(talk.lastIndexOf(STRING_WITH_SPACE) + 1);

			// if meeting is a lightning one then get its time duration from
			// configuration else get meeting minutes
			int meetingDuration = durationString.equals(LIGHTNING) ? challengeConfigurations.getLightningMeetingDuration() : Integer.parseInt(durationString.replaceAll(REGEX_DIGIT_OCCURENCES, EMPTY_STRING));

			// create new talk and add to list of talks
			talks.add(new Talk(++i, talk, meetingDuration));

			// sort talks by duration in desc order
			talks.sort((t1, t2) -> t2.getDuration() - t1.getDuration());
		}
		return talks;
	}

	private static final String DATE_TIME_PATTERN_H_MM_A = "h:mm a";
	private static final String COLON_STRING = ":";
	private static final String REGEX_DIGIT_OCCURENCES = "\\D+";
	private static final String EMPTY_STRING = "";
	private static final String STRING_WITH_SPACE = " ";
	private static final String LIGHTNING = "lightning";
	private static final String TRACK_PLUS_SPACE = "Track ";
	private static final String LUNCH = "Lunch";
	private static final String MEET_YOUR_COLLEAGUES_EVENT = "Meet Your Colleagues Event";

}
