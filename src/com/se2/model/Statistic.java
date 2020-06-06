package com.se2.model;

import java.sql.Date;

public class Statistic {

	private int id;
	private String code;
	private String name;
	private int confirmed;
	private int deaths;
	private int recovered;
	private Date date;

	public Statistic(int id, String code, String name, int confirmed, int deaths, int recovered, Date date) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.date = date;
	}

	public Statistic(int id, String code, String name, int confirmed, int deaths, int recovered) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;

	}

	public Statistic(String code, String name, int confirmed, int deaths, int recovered) {
		super();
		this.code = code;
		this.name = name;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;

	}

	public Statistic(String code, String name, int confirmed, int deaths, int recovered, Date date) {
		super();
		this.code = code;
		this.name = name;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;
		this.date = date;
	}

	public Statistic(String name, int confirmed, int deaths, int recovered) {
		super();
		this.name = name;
		this.confirmed = confirmed;
		this.deaths = deaths;
		this.recovered = recovered;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Statistic [id=" + id + ", code=" + code + ", name=" + name + ", confirmed=" + confirmed + ", deaths="
				+ deaths + ", recovered=" + recovered + ", date=" + date + "]";
	}
}
