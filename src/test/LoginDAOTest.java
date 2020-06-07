package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.se2.daoImpl.LoginDaoImpl;
import com.se2.model.Account;

class LoginDAOTest {

	private LoginDaoImpl l;

	@BeforeAll
	private void onsetup() {
		l = new LoginDaoImpl();
	}

	@Test
	void testLoginDaoImpl() {
		assertNotNull(l.getCon());
	}

	@Test
	void testValidate() {
		try {
			assertTrue(l.validate(new Account("admin", "admin")));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
