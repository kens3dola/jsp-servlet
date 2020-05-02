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

import com.se2.dao.CountryDao;
import com.se2.daoImpl.CountryDaoImpl;
import com.se2.model.Country;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/country")
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CountryDao countrydao;
	
	public void init() {
		countrydao = new CountryDaoImpl();
	}
        @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
		throws ServletException, IOException{
            try {
                String action = (req.getParameter("action")==null)?"":req.getParameter("action");
                switch(action){
                    case "new":
                        insertCountry(req, res);
                        break;
                    case "update":
                        updateCountry(req, res);
                        break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(CountryController.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                
	}
        @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		String action = (req.getParameter("action")==null)?"":req.getParameter("action");
                System.out.println(action);
		try {
			switch(action) {
			case "new":
				showNewForm(req, res);
				break;
			case "delete":
				deleteCountry(req, res);
				break;
			case "update":
				showNewForm(req, res);
				break;
			default:
				listCountry(req, res);
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
	
	private void updateCountry(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));
                int continent_id = Integer.parseInt(req.getParameter("continent_id"));
		
		Country updateCountry = new Country(id, name, confirmed, recovered, deaths, continent_id);
		countrydao.updateCountry(updateCountry);
		res.sendRedirect("country");
	}
	private void deleteCountry(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException{
		int id = Integer.parseInt(req.getParameter("id"));
		countrydao.deleteCountry(id);
		res.sendRedirect("country");
	}
	private void insertCountry(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException{
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));
                int continent_id = Integer.parseInt(req.getParameter("continent_id"));
		
		Country newCountry = new Country(name, confirmed, recovered, deaths, continent_id);
                System.out.println(newCountry.toString());
		countrydao.insertCountry(newCountry);
		res.sendRedirect("country");
	}
	private void showNewForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException, ServletException{
                if(req.getParameter("id")!=null){
                    Country c = countrydao.selectCountry(Integer.parseInt(req.getParameter("id")));
                    req.setAttribute("country", c);
                }
		RequestDispatcher dis = req.getRequestDispatcher("views/country/showFormCountry.jsp");
		dis.forward(req, res);
		
	}
}
