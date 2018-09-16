package com.scope.challenge.model;

public class Talk {
	private int id;
	private String name;
	private int duration;

	public Talk(int id, String name, int duration) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Talk [id=" + id + ", name=" + name + ", duration=" + duration + "]";
	}

}
