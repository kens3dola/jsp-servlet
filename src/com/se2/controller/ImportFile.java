package com.se2.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.se2.daoImpl.StatisticDaoImpl;
import com.se2.model.Statistic;

/**
 * Servlet implementation class ImportFile
 */
@WebServlet("/import")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50)
public class ImportFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatisticDaoImpl sImpl;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportFile() {
		super();
		sImpl = new StatisticDaoImpl();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("api") != null)
			request.setAttribute("api", request.getParameter("api"));
		request.getRequestDispatcher("views/import.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		URL url = new URL(request.getParameter("api"));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responsecode = conn.getResponseCode();

		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			String inline = "";
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();
			}
			sc.close();
			inline = inline.substring(1, inline.length() - 1).replace("\"", "").replace("{", "").replace("}", "");
			String[] str = inline.split(",");
			List<Statistic> list = new ArrayList<Statistic>();
			for (int i = 0; i < (str.length / 9); i++) {
				String code = "country", name = "", recovered = "", confirmed = "", deaths = "";
				java.sql.Date pub_date = null;
				for (int j = 0 + 9 * i; j < 9 + 9 * i; j++) {
					String[] spl = str[j].split(":");
					switch (spl[0]) {
					case "country":
						name = spl[1];
						break;
					case "stats":
						confirmed = spl[2];
						break;
					case "recovered":
						recovered = spl[1];
						break;
					case "deaths":
						deaths = spl[1];
						break;
					case "updatedAt":
						try {
							Date date1 = new SimpleDateFormat("yyy-MM-dd").parse(spl[1].split(" ")[0]);
							pub_date = new java.sql.Date(date1.getTime());
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}
				list.add(new Statistic(code, name, Integer.parseInt(confirmed), Integer.parseInt(deaths),
						Integer.parseInt(recovered), pub_date));
			}
			try {
				sImpl.updateManyCities(list);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		response.sendRedirect("home");
	}
}
