package com.se2.dao;

import java.sql.SQLException;
import java.util.List;

import com.se2.model.Country;

public interface CountryDao {
	List<Country> listCountry() throws SQLException;
	Country selectCountry(int id);
	void insertCountry(Country country) throws SQLException;
	boolean updateCountry(Country country) throws SQLException;
	boolean deleteCountry(int id) throws SQLException;
}
