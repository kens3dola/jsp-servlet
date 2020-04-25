package com.se2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se2.config.JdbcConnection;
import com.se2.dao.CountryDao;
import com.se2.model.Country;

public class CountryDaoImpl implements CountryDao {
	private static final String INSERT_COUNTRY = "insert into country (name, confirmed, recovered, deaths) values "+"(?, ?, ?, ?);";
	private static final String SELECT_COUNTRY_BY_ID = "select * from country where id= ?";
	private static final String SELECT_ALL_COUNTRY = "select * from country";
	private static final String DELETE_COUNTRY_BY_ID = "delete from country where id = ?";
	private static final String UPDATE_COUNTRY = "update country set name=?, confirmed=?, recovered=?, deaths=? where id=?;";
	
	public CountryDaoImpl() {}
	@Override
	public List<Country> listCountry() throws SQLException {
		List<Country> countrys = new ArrayList<>();
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(SELECT_ALL_COUNTRY)){

			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int countryid = rs.getInt("id");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				int continent_id = rs.getInt("continent_id");
				countrys.add(new Country(countryid, name, confirmed, recovered, deaths,continent_id));
			}
		}catch(SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return countrys;
	}

	@Override
	public Country selectCountry(int id) {
		Country country = null;
		try(Connection conn = JdbcConnection.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(SELECT_COUNTRY_BY_ID)){
				prep.setInt(1, id);
				ResultSet rs = prep.executeQuery();
				while(rs.next()) {
					int userid = rs.getInt("id");
					String name = rs.getString("name");
					int confirmed = rs.getInt("confirmed");
					int recovered = rs.getInt("recovered");
					int deaths = rs.getInt("deaths");

					int continent_id = rs.getInt("continent_id");
					country = new Country(userid, name, confirmed, recovered, deaths,continent_id);
				}
			}catch(SQLException e) {
				JdbcConnection.printSQLException(e);
			}
			return country;
	}

	@Override
	public void insertCountry(Country country) throws SQLException {
		try(
			Connection conn =JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(INSERT_COUNTRY)){
			prep.setString(1, country.getName());
			prep.setInt(2, country.getConfirmed());
			prep.setInt(3, country.getRecovered());
			prep.setInt(4, country.getDeaths());
			prep.executeUpdate();
		}catch(SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		
	}

	@Override
	public boolean updateCountry(Country country) throws SQLException {
		boolean rowDeleted;
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(UPDATE_COUNTRY)){
			prep.setString(1, country.getName());
			prep.setInt(2, country.getConfirmed());
			prep.setInt(3, country.getRecovered());
			prep.setInt(4, country.getDeaths());
			prep.setInt(5, country.getId());
			rowDeleted = prep.executeUpdate() >0;
		}
		return rowDeleted;
	}

	@Override
	public boolean deleteCountry(int id) throws SQLException {
		boolean rowDeleted;
		try(Connection conn = JdbcConnection.getConnection(); 
			PreparedStatement prep = conn.prepareStatement(DELETE_COUNTRY_BY_ID)){
			prep.setInt(1, id);
			rowDeleted = prep.executeUpdate() >0;
		}
		return rowDeleted;
	}
	
}
