package com.se2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.se2.config.JdbcConnection;
import com.se2.dao.ContinentDao;
import com.se2.model.Continent;

public class ContinentDaoImpl implements ContinentDao{
	
	@Override
	public void insertContinent(Continent continent) throws SQLException, ClassNotFoundException {
		try(Connection connection = JdbcConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement("insert into continent (name,confirmed,recovered,deaths) values (?,?,?,?);")){
			preparedStatement.setString(1,continent.getName());
			preparedStatement.setInt(2, continent.getConfirmed());
			preparedStatement.setInt(3, continent.getRecovered());
			preparedStatement.setInt(4,continent.getDeaths());
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		
	}

	@Override
	public boolean updateContinent(Continent continent) throws SQLException, ClassNotFoundException {
		boolean rowUpdated;
		try (Connection connection = JdbcConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("update continent set name =?,confirmed=?,recovered=?,deaths=? where id=?;");) {
			
			statement.setString(1, continent.getName());
			statement.setInt(2, continent.getConfirmed());
			statement.setInt(3, continent.getRecovered());
			statement.setInt(4, continent.getDeaths());
			statement.setInt(5, continent.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	@Override
	public boolean deleteContinent(int id) throws SQLException, ClassNotFoundException {
		boolean rowDeleted;
		try (Connection connection = JdbcConnection.getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from continent where id = ?;");) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	@Override
	public List<Continent> listAllContinent() throws ClassNotFoundException {
		List<Continent> continent = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = JdbcConnection.getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement("select * from continent ;");) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent.add(new Continent(id,name,confirmed,recovered,deaths));
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	
	}

	@Override
	public Continent selectContinent(int id) {
		Continent continent = null;
		// Step 1: Establishing a Connection
		try (Connection connection = JdbcConnection.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement("select id,name,confirmed,recovered,deaths from continent where id= ?");) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
			
				String name = rs.getString("name");
				int confirmed = rs.getInt("confirmed");
				int recovered = rs.getInt("recovered");
				int deaths = rs.getInt("deaths");
				continent = new Continent(id,name,confirmed,recovered,deaths);
			}
		} catch (SQLException e) {
			JdbcConnection.printSQLException(e);
		}
		return continent;
	}
    
}