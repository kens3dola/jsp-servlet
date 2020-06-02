package com.se2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.se2.config.JdbcConnection;
import com.se2.dao.StatisticDao;

import com.se2.model.Statistic;

public class StatisticDaoImpl implements StatisticDao{

	@Override
	public void insertStatistic(Statistic statistic) throws SQLException {
		try(Connection connection = JdbcConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("insert into statistic (code,name,confirmed,recovered,deaths) values (?,?,?,?,?);")){
			preparedStatement.setString(1,statistic.getCode());
			preparedStatement.setString(2,statistic.getName());
			preparedStatement.setInt(3, statistic.getConfirmed());
			preparedStatement.setInt(4, statistic.getRecovered());
			preparedStatement.setInt(5,statistic.getDeaths());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		
		
	}

	@Override
	public boolean updateStatistic(Statistic statistic) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = JdbcConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("insert into statistic (code,name,confirmed,recovered,deaths) values (?,?,?,?,?);");) {
			statement.setString(1,statistic.getCode());
			statement.setString(2,statistic.getName());
			statement.setInt(3, statistic.getConfirmed());
			statement.setInt(4, statistic.getRecovered());
			statement.setInt(5,statistic.getDeaths());
    

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	@Override
	public List<Statistic> listAllContinent()  {
		List<Statistic> continent = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = JdbcConnection.getConnection();
				//"WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='world';"
				
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='continent';");
				) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent.add(new Statistic(id,code,name,confirmed,recovered,deaths));
				
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	}
	@Override
	public List<Statistic> listWorld() {
		List<Statistic> continent = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = JdbcConnection.getConnection();
				//"WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='world';"
				
				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='world';");) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent.add(new Statistic(id,code,name,confirmed,recovered,deaths));
				
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	}
	

	@Override
	public Statistic selectStatistic(int id) {
		Statistic statistic = null;
		try(Connection conn = JdbcConnection.getConnection(); 
				PreparedStatement prep = conn.prepareStatement("select * from statistic where id=? ");){
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
				while(rs.next()) {
					String code  = rs.getString("code");
					String name = rs.getString("name");
					int confirmed = rs.getInt("confirmed");
					int recovered = rs.getInt("recovered");
					int deaths = rs.getInt("deaths");
					statistic = new Statistic(code,name,confirmed,recovered,deaths);
					statistic.setId(id);
				}
			}catch(SQLException e) {
				JdbcConnection.printSQLException(e);
			}
			return statistic;
	}

	@Override
	public List<Statistic> listAllCountry()  {
		List<Statistic> country = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = JdbcConnection.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='country';");) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				country.add(new Statistic(id, code, name,confirmed,recovered,deaths));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return country;
	}

	@Override
	public List<Statistic> listAllCity()  {
		List<Statistic> city = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = JdbcConnection.getConnection();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("WITH ranked_statistic AS (SELECT m.*, ROW_NUMBER() OVER (PARTITION BY name ORDER BY id DESC) AS rn FROM statistic AS m)SELECT * FROM ranked_statistic WHERE rn = 1 having code ='city';");) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				city.add(new Statistic(id, code ,name,confirmed,recovered,deaths));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return city;
	}

}
