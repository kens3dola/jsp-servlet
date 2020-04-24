package com.se2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se2.config.JdbcConnection;
import com.se2.dao.CityDao;
import com.se2.model.City;

public class CityDaoImpl implements CityDao {
	private static final String INSERT_CITY = "insert into city (name, confirmed, recovered, deaths) values "+"(?, ?, ?, ?);";
	private static final String SELECT_CITY_BY_ID = "select * from city where id= ?";
	private static final String SELECT_ALL_CITY = "select * from city";
	private static final String DELETE_CITY_BY_ID = "delete from city where id = ?";
	private static final String UPDATE_CITY = "update user set name=?, confirmed=?, recovered=?, deaths=? where id=?;";
	
	public CityDaoImpl() {}
	@Override
	public List<City> listCity() throws SQLException {
		List<City> citys = new ArrayList<>();
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(SELECT_ALL_CITY)){

			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int cityid = rs.getInt("id");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				citys.add(new City(cityid, name, confirmed, recovered, deaths));
			}
		}catch(SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return citys;
	}

	@Override
	public City selectCity(int id) {
		City city = null;
		try(Connection conn = JdbcConnection.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(SELECT_CITY_BY_ID)){
				prep.setInt(1, id);
				ResultSet rs = prep.executeQuery();
				while(rs.next()) {
					int userid = rs.getInt("id");
					String name = rs.getString("name");
					int confirmed = rs.getInt("confirmed");
					int recovered = rs.getInt("recovered");
					int deaths = rs.getInt("deaths");
					city = new City(userid, name, confirmed, recovered, deaths);
				}
			}catch(SQLException e) {
				JdbcConnection.printSQLException(e);
			}
			return city;
	}

	@Override
	public void insertCity(City city) throws SQLException {
		try(
			Connection conn =JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(INSERT_CITY)){
			prep.setString(1, city.getName());
			prep.setInt(2, city.getConfirmed());
			prep.setInt(3, city.getRecovered());
			prep.setInt(4, city.getDeaths());
			prep.executeUpdate();
		}catch(SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		
	}

	@Override
	public boolean updateCity(City city) throws SQLException {
		boolean rowDeleted;
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(UPDATE_CITY)){
			prep.setString(1, city.getName());
			prep.setInt(2, city.getConfirmed());
			prep.setInt(3, city.getRecovered());
			prep.setInt(4, city.getDeaths());
			prep.setInt(5, city.getId());
			rowDeleted = prep.executeUpdate() >0;
		}
		return rowDeleted;
	}

	@Override
	public boolean deleteCity(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(DELETE_CITY_BY_ID)){
			prep.setInt(1, id);
			rowDeleted = prep.executeUpdate() >0;
		}
		return rowDeleted;
	}
	
}
