package com.se2.dao;

import java.sql.SQLException;

import com.se2.model.World;

public interface WorldDao {

	public World get() throws SQLException;
	
	public void update(World w);
	
	public void delete();
}
