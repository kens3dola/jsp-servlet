package com.se2.dao;

import java.sql.SQLException;
import java.util.List;

import com.se2.model.City;

public interface CityDao {
	List<City> listCity() throws SQLException;
	City selectCity(int id);
	void insertCity(City city) throws SQLException;
	boolean updateCity(City city) throws SQLException;
	boolean deleteCity(int id) throws SQLException;
}
