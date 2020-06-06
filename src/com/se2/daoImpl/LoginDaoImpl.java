package com.se2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.se2.config.JdbcConnection;
import com.se2.dao.LoginDao;
import com.se2.model.Account;

public class LoginDaoImpl implements LoginDao {

	private Connection con;

	public LoginDaoImpl() {
		// TODO Auto-generated constructor stub
		con = JdbcConnection.getInstance().getConnection();
	}

	public boolean validate(Account account) throws ClassNotFoundException {
		boolean status = false;

		Class.forName("com.mysql.cj.jdbc.Driver");

		try (
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = this.con
						.prepareStatement("select * from account where username = ? and password = ? ")) {
			preparedStatement.setString(1, account.getUsername());
			preparedStatement.setString(2, account.getPassword());
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();

		} catch (SQLException e) {
			// process sql exception
			JdbcConnection.printSQLException(e);
		}
		return status;
	}
}