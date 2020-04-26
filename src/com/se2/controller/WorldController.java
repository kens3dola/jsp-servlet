package com.se2.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se2.dao.WorldDao;
import com.se2.daoImpl.WorldDaoImpl;
import com.se2.model.World;

/**
 * Servlet implementation class WorldStatisticsController
 */
@WebServlet("/world")
public class WorldController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorldDao wDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WorldController() {
		super();
		wDao = new WorldDaoImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = (request.getParameter("action")==null)?"":request.getParameter("action");

		try {
			switch (action) {
			case "delete":
				delete(request, response);
				break;
			case "update":
				showUpdateForm(request, response);
				break;
			default:
				request.setAttribute("world",wDao.get());
				RequestDispatcher dispatcher = request.getRequestDispatcher("views/world/world.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {		
		try {
			World w = wDao.get();
			RequestDispatcher dispatcher = request.getRequestDispatcher("views/world/showForm.jsp");
			request.setAttribute("world",w);
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		wDao.delete();
		try {
			response.sendRedirect(request.getContextPath()+"/world");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		World w = new World();
		w.setConfirmed(Integer.parseInt(request.getParameter("confirmed")));
		w.setRecovered(Integer.parseInt(request.getParameter("recovered")));
		w.setDeaths(Integer.parseInt(request.getParameter("deaths")));

		wDao.update(w);
		response.sendRedirect(request.getContextPath()+"/world");
	}

}
