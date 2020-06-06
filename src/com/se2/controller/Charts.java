package com.se2.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Charts
 */
@WebServlet("/charts")
public class Charts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Charts() {
		super();
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
		request.getRequestDispatcher("views/world.jsp").forward(request, response);
//		switch (action) {
//		case "world":
//			request.getRequestDispatcher("views/world.jsp").forward(request, response);
//			break;
//		case "continent":
//			request.getRequestDispatcher("views/continent.jsp").forward(request, response);
//			break;
//		case "country":
//			request.getRequestDispatcher("views/country.jsp").forward(request, response);
//			break;
//		case "city":
//			request.getRequestDispatcher("views/city.jsp").forward(request, response);
//			break;
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
