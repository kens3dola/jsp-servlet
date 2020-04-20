package com.se2.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se2.dao.CityDao;


@WebServlet("/")
public class City extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CityDao citydao;
	
	public void init() {
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		doGet(req, res);
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		String action = req.getServletPath();
		try {
			switch(action) {
			case "/city/new":
				showNewForm(req, res);
				break;
			case "/city/insert":
				insertCity(req, res);
				break;
			case "/city/delete":
				deleteCity(req, res);
				break;
			case "/city/update":
				updateCity(req, res);
				break;
			case "/city/list":
				listCity(req, res);
				break;
			default:
				RequestDispatcher dis = req.getRequestDispatcher("/");
				dis.forward(req, res);
				break;
			}
		}catch(SQLException e) {
			throw new ServletException(e);
		}
	}
	private void listCity(HttpServletRequest req, HttpServletResponse res) 
		throws SQLException, IOException, ServletException{
		List<City> listCity = citydao.listCity();
		req.setAttribute("listCity", listCity);
		RequestDispatcher dis = req.getRequestDispatcher("views/city/city.jsp");
		dis.forward(req, res);
	}
	private void showEditForm(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(req.getParameter("id"));
		City city = citydao.selectCity(id);
		RequestDispatcher dis = req.getRequestDispatcher("views/city/showNewForm.jspS");
		req.setAttribute("city", city);
		dis.forward(req,  res);
	}
	private void updateCity(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));		
		
		City updateCity = new City(id, name, confirmed, recovered, deaths);
		citydao.updateCity(updateCity);
		res.sendRedirect("city/list");
	}
	private void deleteCity(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		citydao.deleteCity(id);
		res.sendRedirect("city/list");
	}
	private void insertCity(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));	
		
		City newCity = new City(name, confirmed, recovered, deaths);
		citydao.insertCity(newCity);
		res.sendRedirect("city/list");
	}
	private void showNewForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException, ServletException{
		RequestDispatcher dis = req.getRequestDispatcher("views/city/showNewForm.jsp");
		dis.forward(req, res);
		
	}
}
