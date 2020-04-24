package com.se2.model;

public class City {
	private int id;
	private String name;
	private int confirmed;
	private int recovered;
	private int deaths;
	
	public City(int id, String name, int confirmed, int recovered, int deaths) {
		super();
		this.id = id;
		this.name = name;
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.deaths = deaths;
	}
	public City(String  name, int confirmed, int recovered, int deaths) {
		super();
		this.name = name;
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.deaths = deaths;
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
	public int getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}
	public int getRecovered() {
		return recovered;
	}
	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}
	public int getDeaths() {
		return deaths;
	}
	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

}
