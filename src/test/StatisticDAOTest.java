package test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.DateFormatter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.se2.daoImpl.StatisticDaoImpl;
import com.se2.model.Statistic;

class StatisticDAOTest {
	private StatisticDaoImpl s;

	@BeforeAll
	void onsetUp() {
		s = new StatisticDaoImpl();
	}

	@Test
	void testStatisticDaoImpl() {
		assertNotNull(s.getCon());
	}

	@Test
	void testInsertStatistic() {
		try {
			assertTrue(s.insertStatistic(new Statistic("world", "world", 10, 10, 10)) > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testUpdateStatistic() {
		try {
			assertTrue(s.insertStatistic(new Statistic("world", "world", 10, 10, 11)) > 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testListAllContinent() {
		assertNotNull(s.listAllContinent());
	}

	@Test
	void testListWorld() {
		assertNotNull(s.listWorld());
	}

	@Test
	void testSelectStatistic() {
		assertNotNull(s.selectStatistic(1));
	}

	@Test
	void testListAllCountry() {
		assertNotNull(s.listAllCountry());
	}

	@Test
	void testListAllCity() {
		assertNotNull(s.listAllCity());
	}

	@Test
	void testListAllCountries() {
		assertNotNull(s.listAllCountries("Vietnam"));
	}

	@Test
	void testListAllCities() {
		assertNotNull(s.listAllCities("Hanoi"));
	}

	@Test
	void testListAllContinents() {
		assertNotNull(s.listAllContinents("Europe"));
	}

	@Test
	void testListWorlds() {
		assertNotNull(s.listWorlds());
	}

	@Test
	void testUpdateManyCities() {
		List<Statistic> list = new ArrayList<Statistic>();
		try {
			java.sql.Date date1 = new Date(new SimpleDateFormat("dd/MM/yyy").parse("01/02/2020").getTime());
			java.sql.Date date2 = new Date(new SimpleDateFormat("dd/MM/yyy").parse("01/02/2020").getTime());
			list.add(new Statistic("world", "world", 10, 1, 1, date1));
			list.add(new Statistic("world", "world", 10, 1, 1, date2));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int[] result = null;
		try {
			result = s.updateManyCities(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean r = false;
		for(int i : result) {
			if(i!=0)r=true;
		}
		assertTrue(r);
	}
}
