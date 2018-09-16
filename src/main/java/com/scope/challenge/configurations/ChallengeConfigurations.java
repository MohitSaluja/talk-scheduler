package com.scope.challenge.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class is a configuration component. Values of attributes of this class
 * can be configured in appplication.properties
 * 
 * @author Mohit.Saluja
 *
 */
@Component
@ConfigurationProperties("challenge")
public class ChallengeConfigurations {

	private int lightningMeetingDuration;
	private int morningStartHour;
	private int morningStartMinutes;
	private int lunchHour;
	private int lunchMinutes;
	private int lunchDuration;
	private int afternoonEndHour;
	private int afternoonEndMinutes;
	private int meetYourColleaguesEarliestStartHour;
	private int meetYourColleaguesEarliestStartMinutes;

	public int getLightningMeetingDuration() {
		return lightningMeetingDuration;
	}

	public void setLightningMeetingDuration(int lightningMeetingDuration) {
		this.lightningMeetingDuration = lightningMeetingDuration;
	}

	public int getMorningStartHour() {
		return morningStartHour;
	}

	public void setMorningStartHour(int morningStartHour) {
		this.morningStartHour = morningStartHour;
	}

	public int getMorningStartMinutes() {
		return morningStartMinutes;
	}

	public void setMorningStartMinutes(int morningStartMinutes) {
		this.morningStartMinutes = morningStartMinutes;
	}

	public int getLunchHour() {
		return lunchHour;
	}

	public void setLunchHour(int lunchHour) {
		this.lunchHour = lunchHour;
	}

	public int getLunchMinutes() {
		return lunchMinutes;
	}

	public void setLunchMinutes(int lunchMinutes) {
		this.lunchMinutes = lunchMinutes;
	}

	public int getLunchDuration() {
		return lunchDuration;
	}

	public void setLunchDuration(int lunchDuration) {
		this.lunchDuration = lunchDuration;
	}

	public int getAfternoonEndHour() {
		return afternoonEndHour;
	}

	public void setAfternoonEndHour(int afternoonEndHour) {
		this.afternoonEndHour = afternoonEndHour;
	}

	public int getAfternoonEndMinutes() {
		return afternoonEndMinutes;
	}

	public void setAfternoonEndMinutes(int afternoonEndMinutes) {
		this.afternoonEndMinutes = afternoonEndMinutes;
	}

	public int getMeetYourColleaguesEarliestStartHour() {
		return meetYourColleaguesEarliestStartHour;
	}

	public void setMeetYourColleaguesEarliestStartHour(int meetYourColleaguesEarliestStartHour) {
		this.meetYourColleaguesEarliestStartHour = meetYourColleaguesEarliestStartHour;
	}

	public int getMeetYourColleaguesEarliestStartMinutes() {
		return meetYourColleaguesEarliestStartMinutes;
	}

	public void setMeetYourColleaguesEarliestStartMinutes(int meetYourColleaguesEarliestStartMinutes) {
		this.meetYourColleaguesEarliestStartMinutes = meetYourColleaguesEarliestStartMinutes;
	}

}
