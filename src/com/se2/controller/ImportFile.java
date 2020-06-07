package com.se2.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
		String action = (request.getParameter("code") == null) ? "" : request.getParameter("code");
		switch (action) {
		case "world":
			importWorld(request, response);
			break;
		case "continent":
			importContinent(request, response);
			break;
		case "country":
			importCountry(request, response);
			break;
		case "vietnam":
			importVietnam(request, response);
			break;
//		case "city":
//			importCity(request, response);
//			break;
		}
	}

//	private void importCity(HttpServletRequest request, HttpServletResponse response) {
//		URL url = null;
//		int responsecode = 0;
//		try {
//			url = new URL(request.getParameter("api"));
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.connect();
//			responsecode = conn.getResponseCode();
//		} catch (IOException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//
//		if (responsecode != 200)
//			throw new RuntimeException("HttpResponseCode: " + responsecode);
//		else {
//			try {
//				String inline = "";
//				Scanner sc = new Scanner(url.openStream());
//				while (sc.hasNext()) {
//					inline += sc.nextLine();
//				}
//				sc.close();
//				inline = inline.substring(1, inline.length() - 1).replace("\"", "").replace("{", "").replace("}", "");
//				String[] str = inline.split(",");
//				List<Statistic> list = new ArrayList<Statistic>();
//				for (int i = 0; i < (str.length / 9); i++) {
//					String country="";
//					String code = "city", name = "", recovered = "", confirmed = "", deaths = "";
//					java.sql.Date pub_date = null;
//					for (int j = 0 + 9 * i; j < 9 + 9 * i; j++) {
//						String[] spl = str[j].split(":");
//						switch (spl[0]) {
//						case "country":
//							country = spl[1];
//							break;
//						case "province":
//							name = spl[1];
//							break;
//						case "stats":
//							confirmed = spl[2];
//							break;
//						case "recovered":
//							recovered = spl[1];
//							break;
//						case "deaths":
//							deaths = spl[1];
//							break;
//						case "updatedAt":
//							try {
//								Date date1 = new SimpleDateFormat("yyy-MM-dd").parse(spl[1].split(" ")[0]);
//								pub_date = new java.sql.Date(date1.getTime());
//							} catch (ParseException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							break;
//						}
//					}
//					if(country.equals("Vietnam")) {
//					 list.add(new Statistic(code, name, Integer.parseInt(confirmed), Integer.parseInt(deaths),
//								Integer.parseInt(recovered), pub_date));
//					}
//				}
//				for(Statistic ss:list) {
//					System.out.println(ss.toString());
//				}
////				try {
////					sImpl.updateManyCities(list);
////				} catch (SQLException e1) {
////					// TODO Auto-generated catch block
////					e1.printStackTrace();
////				}
//
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//		try {
//			response.sendRedirect("home");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	private void importWorld(HttpServletRequest request, HttpServletResponse response) {
		URL url = null;
		int responsecode = 0;
		try {
			url = new URL(request.getParameter("api"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responsecode = conn.getResponseCode();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			try {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				inline = inline.substring(1, inline.length()-1).replace("}", "").replace("{", "")
						.replace("\"", "").replace("cases:", "").replace("deaths:", "").replace("recovered:", "");
				String[] str = inline.split(",");
				List<Statistic> list = new ArrayList<Statistic>();
				for (int i = 0; i < 30; i++) {
					String code = "world", name = "world", recovered = "", confirmed = "", deaths = "";
					java.sql.Date pub_date = null;
					confirmed = str[i].split(":")[1];
					recovered = str[i + 60].split(":")[1];
					deaths = str[i + 30].split(":")[1];
					pub_date = extractDate(str[i].split(":")[0]);
					list.add(new Statistic(code, name, Integer.parseInt(confirmed), Integer.parseInt(deaths),
							Integer.parseInt(recovered), pub_date));
				}
				try {
					sImpl.updateManyCities(list);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try

		{
			response.sendRedirect("home");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void importContinent(HttpServletRequest request, HttpServletResponse response) {
		URL url = null;
		int responsecode = 0;
		try {
			url = new URL(request.getParameter("api"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responsecode = conn.getResponseCode();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			try {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				inline = inline.replace("[", "").replace("]", "")
						.replace("\"", "");
				String[] str = inline.split("},");
				List<Statistic> list = new ArrayList<Statistic>();
				for(String string : str) {
					string = string.replace("{", "");
					String[] spl = string.split(",");
					String code = "continent", name = "", recovered = "", confirmed = "", deaths = "";
					java.sql.Date pub_date = new java.sql.Date(new Date().getTime());
					name=spl[14].split(":")[1];
					confirmed = spl[1].split(":")[1];
					recovered = spl[5].split(":")[1];
					deaths = spl[3].split(":")[1];
					list.add(new Statistic(code, name, Integer.parseInt(confirmed), Integer.parseInt(deaths),
							Integer.parseInt(recovered), pub_date));
				}
				try {
					sImpl.updateManyCities(list);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try

		{
			response.sendRedirect("home");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void importCountry(HttpServletRequest request, HttpServletResponse response) {
		URL url = null;
		int responsecode = 0;
		try {
			url = new URL(request.getParameter("api"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responsecode = conn.getResponseCode();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			try {
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
					Statistic check = new Statistic(code, name, Integer.parseInt(confirmed), Integer.parseInt(deaths),
							Integer.parseInt(recovered), pub_date);
					boolean isIn = false;
					for(Statistic s : list) {
						if(s.getName().equals(check.getName())) {
							s.setConfirmed(s.getConfirmed()+check.getConfirmed());
							s.setDeaths(s.getDeaths()+check.getDeaths());
							s.setRecovered(s.getRecovered()+check.getRecovered());
							isIn = true;
						}
					}
					if(!isIn) list.add(check);
				}
				try {
					sImpl.updateManyCities(list);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try {
			response.sendRedirect("home");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void importVietnam(HttpServletRequest request, HttpServletResponse response) {
		URL url = null;
		int responsecode = 0;
		try {
			url = new URL(request.getParameter("api"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			responsecode = conn.getResponseCode();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			try {
				String inline = "";
				Scanner sc = new Scanner(url.openStream());
				while (sc.hasNext()) {
					inline += sc.nextLine();
				}
				sc.close();
				inline = inline.replace("[", "").replace("]", "").replace("{", "").replace("}", "")
						.replace("\"timeline\":\"cases\":", "").replace("\"recovered\":", "")
						.replace("\"deaths\":", "").replace("\"", "");
				System.out.println(inline);
				String[] str = inline.split(",");
				List<Statistic> list = new ArrayList<Statistic>();
				for (int i = 2; i < 32; i++) {
					String code = "country", name = "Vietnam", recovered = "", confirmed = "", deaths = "";
					java.sql.Date pub_date = null;
					confirmed = str[i].split(":")[1];
					recovered = str[i + 60].split(":")[1];
					deaths = str[i + 30].split(":")[1];
					pub_date = extractDate(str[i].split(":")[0]);
					list.add(new Statistic(code, name, Integer.parseInt(confirmed), Integer.parseInt(deaths),
							Integer.parseInt(recovered), pub_date));
				}
				try {
					sImpl.updateManyCities(list);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		try

		{
			response.sendRedirect("home");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private java.sql.Date extractDate(String d) {
		String[] ds = d.split("/");
		if(Integer.parseInt(ds[0])<10) {
			ds[0]="0"+ds[0];
		}
		if(Integer.parseInt(ds[1])<10) {
			ds[1]="0"+ds[1];
		}
		ds[2]="20"+ds[2];
		String x = ds[0]+"/"+ds[1]+"/"+ds[2];
		Date date = null;
		try {
			date = new SimpleDateFormat("MM/dd/yyyy").parse(x);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new java.sql.Date(date.getTime());
	}
}
