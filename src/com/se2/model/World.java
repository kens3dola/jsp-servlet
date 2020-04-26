package com.se2.model;

public class World {
	private int id;
	private int confirmed;
	private int recovered;
	private int deaths;
	
	public World() {
		
	}
	
	public World(int id, int confirmed, int recovered, int deaths) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "World [id=" + id + ", confirmed=" + confirmed + ", recovered=" + recovered + ", deaths=" + deaths + "]";
	}
	
}
