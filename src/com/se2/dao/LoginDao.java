package com.se2.dao;

import com.se2.model.Account;

public interface LoginDao {
	
	public boolean validate(Account account) throws ClassNotFoundException;
	
}
