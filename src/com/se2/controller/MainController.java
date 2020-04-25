package com.se2.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se2.dao.RSSFeedParser;
import com.se2.dao.WorldDao;
import com.se2.daoImpl.WorldDaoImpl;
import com.se2.model.Feed;
import com.se2.model.FeedMessage;
import com.se2.model.World;

@WebServlet("/home")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorldDao wDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		wDao= new WorldDaoImpl();
	}
       
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RSSFeedParser parser = new RSSFeedParser(
                "https://thanhnien.vn/rss/viet-nam.rss");
        Feed feed = parser.readFeed();
        request.setAttribute("feed", feed.getMessages());
		World w = null;
		try {
			w = wDao.get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("world", w);
		RequestDispatcher rd = request.getRequestDispatcher("views/home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
