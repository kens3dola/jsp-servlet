package com.se2.dao;

import java.sql.SQLException;
import java.util.List;

import com.se2.model.ContinentModel;

public interface ContinentDao {
	void insertContinent(ContinentModel continent) throws SQLException,ClassNotFoundException ;
	boolean updateContinent(ContinentModel continent) throws SQLException,ClassNotFoundException ;
	boolean deleteContinent(int id) throws SQLException,ClassNotFoundException ;
	List<ContinentModel> listAllContinent() throws ClassNotFoundException  ;
	ContinentModel selectContinent(int id);
	
}