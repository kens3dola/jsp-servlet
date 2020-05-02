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
import com.se2.daoImpl.CityDaoImpl;
import com.se2.model.City;
import com.se2.model.Country;


@WebServlet("/city")
public class CityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CityDao citydao;
	
	public void init() {
		citydao = new CityDaoImpl();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
		String action = (req.getParameter("action")==null)?"":req.getParameter("action");
		try {
			switch(action) {
			case "new":
				insertCity(req, res);
				break;
			case "delete":
				deleteCity(req, res);
				break;
			case "update":
				updateCity(req, res);
				break;
			default:
				listCity(req, res);
				break;
			}
		}catch(SQLException e) {
			throw new ServletException(e);
		}
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		String action = (req.getParameter("action")==null)?"":req.getParameter("action");
		try {
			switch(action) {
			case "new":
				showNewForm(req, res);
				break;
			case "delete":
				deleteCity(req, res);
				break;
			case "update":
				showNewForm(req, res);
				break;
			default:
				listCity(req, res);
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
	private void updateCity(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));		
		
		City updateCity = new City(id, name, confirmed, recovered, deaths);
		citydao.updateCity(updateCity);
		res.sendRedirect("city");
	}
	private void deleteCity(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		citydao.deleteCity(id);
		res.sendRedirect("city");
	}
	private void insertCity(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));
		City newCity = new City(name, confirmed, recovered, deaths);
		citydao.insertCity(newCity);
		res.sendRedirect("city");
	}
	private void showNewForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException, ServletException{
		if(req.getParameter("id")!=null) {
			City c = citydao.selectCity(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("city", c);
		}
		RequestDispatcher dis = req.getRequestDispatcher("views/city/showNewForm.jsp");
		dis.forward(req, res);
	}
}
