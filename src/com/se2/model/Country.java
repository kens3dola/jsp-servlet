package com.se2.model;

public class Country {
	private int id;
	private String name;
	private int confirmed;
	private int recovered;
	private int deaths;
	private int continent_id;
	public Country(int id, String name, int confirmed, int recovered, int deaths, int continent_id) {
		super();
		this.id = id;
		this.name = name;
		this.confirmed = confirmed;
		this.recovered = recovered;
		this.deaths = deaths;
		this.continent_id = continent_id;
	}
	
	

	public Country() {
		// TODO Auto-generated constructor stub
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
	public int getContinent_id() {
		return continent_id;
	}
	public void setContinent_id(int continent_id) {
		this.continent_id = continent_id;
	}
	
	
}
