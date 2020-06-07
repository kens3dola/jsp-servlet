package com.se2.config;

import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.se2.model.Statistic;
import com.google.gson.Gson;

public class JsonConverter {

	private final com.google.gson.Gson gson;

	public JsonConverter() {
		GsonBuilder builder = new GsonBuilder();
		gson = builder.create();
	}

	public String convertToJson(List<Statistic> statistics) {

		com.google.gson.JsonArray jarray = gson.toJsonTree(statistics).getAsJsonArray();
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("statistics", jarray);

		return jsonObject.toString();
	}
}