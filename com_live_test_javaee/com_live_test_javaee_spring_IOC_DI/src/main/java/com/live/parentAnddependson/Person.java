package com.live.parentAnddependson;

public class Person {

	private int leg;
	private String nationality;
	private String name;

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLeg() {
		return leg;
	}

	public void setLeg(int leg) {
		this.leg = leg;
	}

	@Override
	public String toString() {
		return "Person [leg=" + leg + ", nationality=" + nationality
				+ ", name=" + name + "]";
	}

}
