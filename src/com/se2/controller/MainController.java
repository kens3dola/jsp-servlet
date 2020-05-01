package com.se2.controller;

import com.se2.dao.CountryDao;
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
import com.se2.daoImpl.CountryDaoImpl;
import com.se2.daoImpl.WorldDaoImpl;
import com.se2.model.Country;
import com.se2.model.Feed;
import com.se2.model.FeedMessage;
import com.se2.model.World;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/home")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WorldDao wDao;
        private CountryDao countryDao;
	
	@Override
	public void init() throws ServletException {
		super.init();
		wDao= new WorldDaoImpl();
                countryDao = new CountryDaoImpl();
	}
       
    public MainController() {
        super();
    }

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Country> listCountry = new ArrayList<Country>();
            try {	
                 listCountry = countryDao.listCountry();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        RSSFeedParser parser = new RSSFeedParser( "https://thanhnien.vn/rss/viet-nam.rss");
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
                request.setAttribute("listCountry", listCountry);
		RequestDispatcher rd = request.getRequestDispatcher("views/home.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
                
	}

}
