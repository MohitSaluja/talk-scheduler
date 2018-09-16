package com.scope.challenge.model;

import java.util.ArrayList;
import java.util.List;

public class Track {

	private List<Talk> morningTalks = new ArrayList<>();
	private List<Talk> afternoonTalks = new ArrayList<>();
	private long morningSlot;
	private long afternoonSlot;

	public Track(long morningSlot, long afternoonTalks) {
		this.morningSlot = morningSlot;
		this.afternoonSlot = afternoonTalks;
	}

	public List<Talk> getMorningTalks() {
		return morningTalks;
	}

	public void setMorningTalks(List<Talk> morningTalks) {
		this.morningTalks = morningTalks;
	}

	public List<Talk> getAfternoonTalks() {
		return afternoonTalks;
	}

	public void setAfternoonTalks(List<Talk> afternoonTalks) {
		this.afternoonTalks = afternoonTalks;
	}

	public long getMorningSlot() {
		return morningSlot;
	}

	public void setMorningSlot(long morningSlot) {
		this.morningSlot = morningSlot;
	}

	public long getAfternoonSlot() {
		return afternoonSlot;
	}

	public void setAfternoonSlot(long afternoonSlot) {
		this.afternoonSlot = afternoonSlot;
	}

	@Override
	public String toString() {
		return "Track [morningTalks=" + morningTalks + ", afternoonSlot=" + afternoonTalks + "]";
	}

}
