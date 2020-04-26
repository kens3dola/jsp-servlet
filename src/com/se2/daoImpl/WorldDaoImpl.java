package com.se2.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.se2.config.JdbcConnection;
import com.se2.dao.WorldDao;
import com.se2.model.World;

public class WorldDaoImpl implements WorldDao{

	private Connection con;
	
	public WorldDaoImpl() {
		con = JdbcConnection.getConnection();
	}

	@Override
	public World get() throws SQLException {
		World w = null;
		String sql = "select * from world";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			w = new World(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
		}
		return w;
	}

	@Override
	public void update(World w) {
		try {
			String sql = "update world set confirmed =  "+w.getConfirmed()+", recovered =  "+w.getRecovered()+", deaths = "+w.getDeaths()+" where id=1";
			PreparedStatement st = con.prepareStatement(sql);
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete() {
		String sql = "update world set confirmed=0, recovered=0, deaths=0 where id=1";
		try {
			Statement st = con.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
