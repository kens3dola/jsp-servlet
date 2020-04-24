package com.se2.dao;

import java.sql.SQLException;
import java.util.List;

import com.se2.model.Continent;

public interface ContinentDao {
	void insertContinent(Continent continent) throws SQLException,ClassNotFoundException ;
	boolean updateContinent(Continent continent) throws SQLException,ClassNotFoundException ;
	boolean deleteContinent(int id) throws SQLException,ClassNotFoundException ;
	List<Continent> listAllContinent() throws ClassNotFoundException  ;
	Continent selectContinent(int id);
}