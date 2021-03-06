package com.se2.dao;

import java.sql.SQLException;
import java.util.List;

import com.se2.model.Statistic;

public interface StatisticDao {
	int insertStatistic(Statistic statistic) throws SQLException;
	boolean updateStatistic(Statistic Statistic) throws SQLException;
	List<Statistic> listWorld() throws SQLException;
	List<Statistic> listAllContinent() throws SQLException  ;
	List<Statistic> listAllCountry() throws SQLException  ;
	List<Statistic> listAllCity() throws SQLException ;
	Statistic selectStatistic(int id);
	List<Statistic> listWorlds();
	List<Statistic> listAllCities(String s);
	List<Statistic> listAllContinents(String s);
	List<Statistic> listAllCountries(String s);
}
