package com.se2.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se2.dao.StatisticDao;
import com.se2.daoImpl.StatisticDaoImpl;
import com.se2.model.Statistic;

/**
 * Servlet implementation class StatisticController
 */
@WebServlet("/statistic")
public class StatisticController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private StatisticDao statisticDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StatisticController() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
    	statisticDao = new StatisticDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			String action = (req.getParameter("action")==null)?"":req.getParameter("action");
			try {
				switch(action) {
				case "new":
					showNewForm(req, res);
					break;
				case "update":
					showNewForm(req, res);
					break;
				
				}
			}catch(SQLException e) {
				throw new ServletException(e);
			}
		}
    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
        try {
            String action = (req.getParameter("action")==null)?"":req.getParameter("action");
            switch(action){
                case "new":
                    insertStatistic(req, res);
                    break;
                case "update":
                    updateStatistic(req, res);
                    break;
            }
        } catch (SQLException ex) {
            
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            
            
}
    private void updateStatistic(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException, ClassNotFoundException{
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		Statistic updateStatistic = new Statistic(code, name , confirmed,deaths, recovered, date);
		statisticDao.updateStatistic(updateStatistic);
		res.sendRedirect("home");
	}
	private void insertStatistic(HttpServletRequest req, HttpServletResponse res) 
			throws SQLException, IOException, ClassNotFoundException{
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		int confirmed = Integer.parseInt(req.getParameter("confirmed"));
		int recovered = Integer.parseInt(req.getParameter("recovered"));
		int deaths = Integer.parseInt(req.getParameter("deaths"));
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		
		Statistic insertStatistic = new Statistic(code, name , confirmed,deaths, recovered, date);
		statisticDao.insertStatistic(insertStatistic);
		res.sendRedirect("home");
	}
	private void showNewForm(HttpServletRequest req, HttpServletResponse res)
			throws SQLException, IOException, ServletException{
                if(req.getParameter("id")!=null){
                    Statistic statistic = statisticDao.selectStatistic(Integer.parseInt(req.getParameter("id")));
                    req.setAttribute("statistic", statistic);
                }
		RequestDispatcher dis = req.getRequestDispatcher("views/showNewForm.jsp");
		dis.forward(req, res);
		
	}
}
