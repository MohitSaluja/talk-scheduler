package com.scope.challenge.services.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scope.challenge.configurations.ChallengeConfigurations;
import com.scope.challenge.model.Talk;
import com.scope.challenge.model.Track;
import com.scope.challenge.services.ITalkSchedulerService;

@Component
public class TalkSchedulerServiceImpl implements ITalkSchedulerService {

	@Autowired
	private ChallengeConfigurations challengeConfigurations;

	@Override
	public List<Track> schedule(List<Talk> talks) {

		// get morningTimeSlotDuration by subtracting track start time and lunch
		// start time
		long morningTimeSlotDuration = java.time.Duration.between(LocalTime.of(challengeConfigurations.getMorningStartHour(), challengeConfigurations.getMorningStartMinutes()), LocalTime.of(challengeConfigurations.getLunchHour(), challengeConfigurations.getLunchMinutes())).toMinutes();

		// get lunch end time by adding lunch start time and lunch duration
		LocalTime lunchEndTime = LocalTime.of(challengeConfigurations.getLunchHour(), challengeConfigurations.getLunchMinutes()).plusMinutes(challengeConfigurations.getLunchDuration());

		// get afternoonTimeSlotDuration by subtracting lunch end time and track
		// end time/meet your colleagues latest possible start time
		long afternoonTimeSlotDuration = java.time.Duration.between(lunchEndTime, LocalTime.of(challengeConfigurations.getAfternoonEndHour(), challengeConfigurations.getAfternoonEndMinutes())).toMinutes();

		List<Track> tracks = new ArrayList<>();
		// initialize track by providing morningTimeSlotDuration and
		// afternoonTimeSlotDuration
		tracks.add(new Track(morningTimeSlotDuration, afternoonTimeSlotDuration));

		outer: for (Iterator<Talk> talkIterator = talks.iterator(); talkIterator.hasNext();) {
			Talk talk = talkIterator.next();

			for (int i = 0; i < tracks.size(); i++) {

				Track track = tracks.get(i);

				if (track.getMorningSlot() >= talk.getDuration()) {
					// if morning slot of track can accommodate this talk
					// add this talk to track's morning talks
					track.getMorningTalks().add(talk);

					// reduce track's morning slot by talk's duration
					track.setMorningSlot(track.getMorningSlot() - talk.getDuration());

					// no need to iterate other tracks if current talk has been
					// allocated
					continue outer;
				} else if (track.getAfternoonSlot() >= talk.getDuration()) {
					// if afternoon slot of track can accommodate this talk

					// add this talk to track's afternoon talks
					track.getAfternoonTalks().add(talk);

					// reduce track's afternoon slot by talk's duration
					track.setAfternoonSlot(track.getAfternoonSlot() - talk.getDuration());

					// no need to iterate other tracks if current talk has been
					// allocated
					continue outer;
				} else if (tracks.size() == i + 1) {
					// if this is the last track in iteration
					// create new track to accommodate talk in consideration
					Track newTrack = new Track(morningTimeSlotDuration, afternoonTimeSlotDuration);

					if (newTrack.getMorningSlot() >= talk.getDuration()) {
						// if morning slot of track can accommodate this talk
						// add this talk to track's morning talks
						newTrack.getMorningTalks().add(talk);

						// reduce track's morning slot by talk's duration
						newTrack.setMorningSlot(newTrack.getMorningSlot() - talk.getDuration());

						// add this newly created track to list of tracks
						tracks.add(newTrack);
						// this talk shouldn't re-iterate newly created track
						continue outer;

					} else if (newTrack.getAfternoonSlot() >= talk.getDuration()) {
						// if afternoon slot of track can accommodate this talk

						// add this talk to track's afternoon talks
						newTrack.getAfternoonTalks().add(talk);

						// reduce track's afternoon slot by talk's duration
						newTrack.setAfternoonSlot(newTrack.getAfternoonSlot() - talk.getDuration());

						// add this newly created track to list of tracks
						tracks.add(newTrack);
						// this talk shouldn't re-iterate newly created track
						continue outer;
					}
					// talk greater than maximum possible time slot would be
					// ignored
				}
			}
		}
		return tracks;

	}

}
