package com.qa.ims.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.domain.Customer;
import com.qa.ims.util.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO custDAO = new CustomerDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "Passwordunknown123!");
	}

	@Before
	public void setup() throws SQLException {
		DBUtils.getInstance().init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");;
	}

	@Test
	public void testCreate() {
		final Long id = 3L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		final Customer created = new Customer(id,f_name, l_name, email, phone);
		assertEquals(custDAO.readLatest(), custDAO.create(created));
	}

	@Test
	public void testReadAll() {
		final Long id = 3L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		List<Customer> expected = new ArrayList<>();
		expected.add(new Customer(id, f_name, l_name, email, phone));
		assertEquals(expected, custDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		final Long id = 2L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		assertEquals(new Customer(id,f_name, l_name, email, phone), custDAO.readLatest());
	}

	@Test
	public void testUpdate() {
		
		final Customer updated= new Customer(1L, "Jack", "Barns", "changed@qa.com", 3232L);
		assertEquals(updated, custDAO.update(updated));

	}
	
	
	@Test
	public void testDelete() {
		final Long id = 1L;
		assertNull(null, custDAO.deleteById(id));
	}


}

	/**
	
	
	@Test
	
	public void createException1() throws SQLException{
		Customer customer
		
		when(dbconnection.getConnection()),DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
	
	**/

