package com.se2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.se2.config.JdbcConnection;
import com.se2.dao.StatisticDao;

import com.se2.model.Statistic;

public class StatisticDaoImpl implements StatisticDao {

	private Connection con;

	public StatisticDaoImpl() {
		// TODO Auto-generated constructor stub
		this.con = JdbcConnection.getInstance().getConnection();
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int insertStatistic(Statistic statistic) throws SQLException {
		try (PreparedStatement preparedStatement = con.prepareStatement(
				"insert into statistic (code,name,confirmed,recovered,deaths,pub_date) values (?,?,?,?,?,?);")) {
			preparedStatement.setString(1, statistic.getCode());
			preparedStatement.setString(2, statistic.getName());
			preparedStatement.setInt(3, statistic.getConfirmed());
			preparedStatement.setInt(4, statistic.getRecovered());
			preparedStatement.setInt(5, statistic.getDeaths());
			preparedStatement.setDate(6, statistic.getDate());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return 0;

	}

	@Override
	public boolean updateStatistic(Statistic statistic) throws SQLException {
		boolean rowUpdated = true;
		PreparedStatement statement = con.prepareStatement(
				"update statistic set confirmed=?,recovered=?,deaths=? where code=? and name=? and pub_date=?");
		statement.setString(4, statistic.getCode());
		statement.setString(5, statistic.getName());
		statement.setInt(1, statistic.getConfirmed());
		statement.setInt(2, statistic.getRecovered());
		statement.setInt(3, statistic.getDeaths());
		statement.setDate(6, statistic.getDate());
		int rUpdated = statement.executeUpdate();
		rowUpdated = rUpdated>0;
		if (rUpdated == 0) {
			try (PreparedStatement st = con.prepareStatement(
					"insert into statistic (code,name,confirmed,recovered,deaths,pub_date) values (?,?,?,?,?,?);");) {
				st.setString(1, statistic.getCode());
				st.setString(2, statistic.getName());
				st.setInt(3, statistic.getConfirmed());
				st.setInt(4, statistic.getRecovered());
				st.setInt(5, statistic.getDeaths());
				st.setDate(6, statistic.getDate());
				rowUpdated = st.executeUpdate() > 0;
			}
		}
		return rowUpdated;
	}

	@Override
	public List<Statistic> listAllContinent() {
		List<Statistic> continent = new ArrayList<>();
		try (PreparedStatement preparedStatement = con.prepareStatement(
				"WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='continent';");) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent.add(new Statistic(id, code, name, confirmed,deaths ,recovered ));

			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	}

	@Override
	public List<Statistic> listWorld() {
		List<Statistic> continent = new ArrayList<>();
		try (PreparedStatement preparedStatement = con.prepareStatement(
				"WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='world';");) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent.add(new Statistic(id, code, name, confirmed,deaths ,recovered ));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	}

	@Override
	public Statistic selectStatistic(int id) {
		Statistic statistic = null;
		try (PreparedStatement prep = con.prepareStatement("select * from statistic where id=? ");) {
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				statistic = new Statistic(code, name, confirmed,deaths , recovered);
				statistic.setId(id);
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return statistic;
	}

	@Override
	public List<Statistic> listAllCountry() {
		List<Statistic> country = new ArrayList<>();
		try (PreparedStatement preparedStatement = con.prepareStatement(
				"WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='country';");) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				country.add(new Statistic(id, code, name, confirmed,deaths , recovered));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return country;
	}

	@Override
	public List<Statistic> listAllCity() {
		List<Statistic> city = new ArrayList<>();
		try (PreparedStatement preparedStatement = con.prepareStatement(
				"WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='city';");) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				city.add(new Statistic(id, code, name, confirmed,deaths , recovered));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return city;
	}

	@Override
	public List<Statistic> listAllCountries(String s) {
		List<Statistic> country = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("select * from statistic where code=? and name=? order by pub_date ASC limit 30");
			preparedStatement.setString(1, "country");
			preparedStatement.setString(2, s);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				country.add(new Statistic(id, code, name, confirmed,deaths , recovered, rs.getDate("pub_date")));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return country;
	}

	@Override
	public List<Statistic> listAllCities(String s) {
		List<Statistic> city = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("select * from statistic where code=? and name =? order by pub_date ASC limit 30");
			preparedStatement.setString(1, "city");
			preparedStatement.setString(2, s);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				city.add(new Statistic(id, code, name, confirmed,deaths , recovered, rs.getDate("pub_date")));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return city;
	}

	@Override
	public List<Statistic> listAllContinents(String s) {
		List<Statistic> continent = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con
					.prepareStatement("select * from statistic where code=? and name=? order by pub_date ASC limit 30");
			preparedStatement.setString(1, "continent");
			preparedStatement.setString(2, s);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent.add(new Statistic(id, code, name, confirmed,deaths , recovered, rs.getDate("pub_date")));

			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	}

	@Override
	public List<Statistic> listWorlds() {
		List<Statistic> continent = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("select * from statistic where code=? order by pub_date ASC limit 30");
			preparedStatement.setString(1, "world");
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent.add(new Statistic(id, code, name, confirmed,deaths , recovered, rs.getDate("pub_date")));

			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	}

	public int[] updateManyCities(List<Statistic> list) throws SQLException {
		Statistic stt = list.get(0);
		if (checkUpdateOrInsert(stt)) {
			System.out.println("1");
			PreparedStatement statement = con.prepareStatement(
					"update statistic set confirmed=?,recovered=?,deaths=? where code=? and name=? and pub_date=?");
			Iterator<Statistic> it = list.iterator();
			while (it.hasNext()) {
				Statistic s = it.next();
				statement.setString(4, s.getCode());
				statement.setString(5, s.getName());
				statement.setInt(1, s.getConfirmed());
				statement.setInt(2, s.getRecovered());
				statement.setInt(3, s.getDeaths());
				statement.setDate(6, s.getDate());
				statement.addBatch();
			}
			return statement.executeBatch();
		} else {
			System.out.println("2");
			PreparedStatement st = con.prepareStatement(
					"insert into statistic (code,name,confirmed,recovered,deaths,pub_date) values (?,?,?,?,?,?);");
			Iterator<Statistic> i = list.iterator();
			while (i.hasNext()) {
				Statistic sta = i.next();
				st.setString(1, sta.getCode());
				st.setString(2, sta.getName());
				st.setInt(3, sta.getConfirmed());
				st.setInt(4, sta.getRecovered());
				st.setInt(5, sta.getDeaths());
				st.setDate(6, sta.getDate());
				st.addBatch();
			}
			 return st.executeBatch();
		}
	}

	private boolean checkUpdateOrInsert(Statistic stt) {
		try {
			PreparedStatement sm = this.con
					.prepareStatement("select * from statistic where code=? and name=? and pub_date=?");
			sm.setString(1, stt.getCode());
			sm.setString(2, stt.getName());
			sm.setDate(3, stt.getDate());
			ResultSet rs = sm.executeQuery();
			System.out.println(rs.next());
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
