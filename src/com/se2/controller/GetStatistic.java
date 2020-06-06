package com.se2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se2.config.JsonConverter;
import com.se2.daoImpl.StatisticDaoImpl;
import com.se2.model.Statistic;

/**
 * Servlet implementation class GetStatistic
 */
@WebServlet("/statistics")
public class GetStatistic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatisticDaoImpl sImpl;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetStatistic() {
		super();
		sImpl = new StatisticDaoImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		String action = (request.getParameter("code") == null) ? "" : request.getParameter("code");
		switch (action) {
		case "world":
			getWorldStatistic(request, response);
			break;
		case "continent":
			getContinentStatistic(request, response);
			break;
		case "country":
			getCountry(request, response);
			break;
		case "city":
			getCity(request, response);
			break;
		}
	}

	private void getCity(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletOutputStream out = response.getOutputStream();
			List<Statistic> statistics = sImpl.listAllCities(request.getParameter("name"));
			JsonConverter converter = new JsonConverter();
			String output = converter.convertToJson(statistics);
			out.print(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCountry(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletOutputStream out = response.getOutputStream();
			List<Statistic> statistics = sImpl.listAllCountries(request.getParameter("name"));
			JsonConverter converter = new JsonConverter();
			String output = converter.convertToJson(statistics);
			out.print(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getContinentStatistic(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletOutputStream out = response.getOutputStream();
			List<Statistic> statistics = sImpl.listAllContinents(request.getParameter("name"));
			JsonConverter converter = new JsonConverter();
			String output = converter.convertToJson(statistics);
			out.print(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getWorldStatistic(HttpServletRequest request, HttpServletResponse response) {
		try {
			ServletOutputStream out = response.getOutputStream();
			List<Statistic> statistics = sImpl.listWorlds();
			JsonConverter converter = new JsonConverter();
			String output = converter.convertToJson(statistics);
			out.print(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
