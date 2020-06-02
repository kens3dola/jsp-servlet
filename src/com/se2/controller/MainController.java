package com.se2.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.se2.dao.RSSFeedParser;
import com.se2.dao.StatisticDao;
import com.se2.daoImpl.StatisticDaoImpl;
import com.se2.model.Feed;
import com.se2.model.Statistic;


@WebServlet("/home")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StatisticDao statisticDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
	
		statisticDao = new StatisticDaoImpl();
	}
       
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RSSFeedParser parser = new RSSFeedParser(
                "https://thanhnien.vn/rss/viet-nam.rss");
        Feed feed = parser.readFeed();
        request.setAttribute("feed", feed.getMessages());
		
		List<Statistic> listWorld = new ArrayList<Statistic>();
		try {
			listWorld = statisticDao.listWorld();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		        request.setAttribute("world", listWorld);
		        List<Statistic> listContinent = new ArrayList<Statistic>();
				try {
					listContinent = statisticDao.listAllContinent();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				        request.setAttribute("continent", listContinent);
			List<Statistic> listCountry = new ArrayList<Statistic>();
						try {
							listCountry = statisticDao.listAllCountry();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						        request.setAttribute("country", listCountry);
			List<Statistic> listCity = new ArrayList<Statistic>();
				try {
					listCity = statisticDao.listAllCity();
					} catch (SQLException e) {
									// TODO Auto-generated catch block
						e.printStackTrace();
								}
					request.setAttribute("city", listCity);
		  
        
		RequestDispatcher rd = request.getRequestDispatcher("views/home.jsp");
		rd.forward(request, response);
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
