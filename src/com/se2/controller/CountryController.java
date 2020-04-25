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

import com.se2.dao.CountryDAO;
import com.se2.daoImpl.CityDaoImpl;


@WebServlet("/")
public class Country extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CountryDAO countrydao;
	
	public void init() {
		citydao = new CityDaoImpl();
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
			case "/country/new":
				showNewForm(req, res);
				break;
			case "/country/insert":
				insertCountry(req, res);
				break;
			case "/country/delete":
				deleteCountry(req, res);
				break;
			case "/country/update":
				updateCountry(req, res);
				break;
			case "/country/list":
				listCountry(req, res);
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
	private void listCountry(HttpServletRequest req, HttpServletResponse res) 
		throws SQLException, IOException, ServletException{
		List<Country> listCountry = countrydao.listCountry();
		req.setAttribute("listCountry", listCountry);
		RequestDispatcher dis = req.getRequestDispatcher("views/country/country.jsp");
		dis.forward(req, res);
	}
	private void showEditForm(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(req.getParameter("id"));
		Country country = countrydao.selectCountry(id);
		RequestDispatcher dis = req.getRequestDispatcher("views/country/showNewForm.jspS");
		req.setAttribute("country", country);
		dis.forward(req,  res);
	}
	private void updateCountry(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));
		
		Country updateCountry = new Country(id, name, confirmed, recovered, deaths, );
		countrydao.updateCountry(updateCountry);
		res.sendRedirect("country/list");
	}
	private void deleteCountry(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		countrydao.deleteCountry(id);
		res.sendRedirect("country/list");
	}
	private void insertCountry(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));
		
		Country newCountry = new Country(name, confirmed, recovered, deaths);
		countrydao.insertCountry(newCountry);
		res.sendRedirect("city/country");
	}
	private void showNewForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException, ServletException{
		RequestDispatcher dis = req.getRequestDispatcher("views/country/showNewForm.jsp");
		dis.forward(req, res);
		
	}
}
