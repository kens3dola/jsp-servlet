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

import com.se2.dao.ContinentDao;
import com.se2.daoImpl.ContinentDaoImpl;
import com.se2.model.Continent;

/**
 * Servlet implementation class Continent
 */
@WebServlet("/continent")
public class ContinentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       /**
     * @see HttpServlet#HttpServlet()
     */
	private ContinentDaoImpl continentDao;
   
	public void init() {
    	continentDao= new ContinentDaoImpl();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = (request.getParameter("action")==null)?"":request.getParameter("action");

		try {
			switch (action) {
			case "new":
				showNewForm(request, response);
				break;
			case "insert":
				insertContinent(request, response);
				break;
			case "delete":
				deleteContinent(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "update":
				updateContinent(request, response);
				break;
            default:
            listContinent(request, response);
                break;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			throw new ServletException(ex);
		}
	}

	private void listContinent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException, ClassNotFoundException {
	List<Continent> listContinent = continentDao.listAllContinent();
		request.setAttribute("listContinent", listContinent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/continent/continent.jsp");
		dispatcher.forward(request, response);
		
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/continent/showFormContinent.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		  int id = Integer.parseInt(request.getParameter("id"));
		  System.out.print(id);
		  Continent existingContinent = continentDao.selectContinent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("views/continent/showFormContinent.jsp");
		request.setAttribute("continent",existingContinent);
		dispatcher.forward(request, response);
		
//		String id = request.getParameter("id");
//
//	    int x = 0;
//	    if(id!=null){
//	      try{
//	        x = Integer.parseInt(id);
//	       }catch(Exception e){
//	       }
//
//	    }
//		ContinentModel existingContinent = continentDao.selectContinent(x);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("views/continent/showFormcontinent.jsp");
//		request.setAttribute("continent", existingContinent);
//		dispatcher.forward(request, response);

	}

	private void insertContinent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		String name = request.getParameter("name");
		int confirmed = Integer.parseInt(request.getParameter("confirmed"));
		int recovered = Integer.parseInt(request.getParameter("recovered"));
		int deaths = Integer.parseInt(request.getParameter("deaths"));
	
		Continent continent = new Continent(name, confirmed,recovered,deaths);
	    continentDao.insertContinent(continent);
		response.sendRedirect("continent");
	}

	private void updateContinent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		 int id = Integer.parseInt(request.getParameter("id"));
		 String name = request.getParameter("name");
		int confirmed = Integer.parseInt(request.getParameter("confirmed"));
		int recovered = Integer.parseInt(request.getParameter("recovered"));
		int deaths = Integer.parseInt(request.getParameter("deaths"));
		Continent continent = new Continent(id,name, confirmed,recovered,deaths);
		continentDao.updateContinent(continent);
		response.sendRedirect("continent");
	}

	private void deleteContinent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		int id = Integer.parseInt(request.getParameter("id"));
		continentDao.deleteContinent(id);
		response.sendRedirect("continent");

	}


}