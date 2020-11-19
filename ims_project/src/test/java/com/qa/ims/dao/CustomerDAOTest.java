package com.qa.ims.dao;


import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.ims.domain.Customer;
import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;

public class CustomerDAOTest {

	private final CustomerDAO custDAO = new CustomerDAO();

	@BeforeClass
	public static void init() {
		DBUtils.connect("root", "Passwordunknown123!");
	}

	@Before
	public void setup() throws SQLException {
		DBUtils.getInstance().getConnection();
	}

	@Test
	public void testCreate() {
		final Long id = 3L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		final Customer created = new Customer(id, f_name, l_name, email, phone);
		assertEquals(created, custDAO.create(created));
	}

	@Test
	public void testReadAll() {
		final Long id = 1L;
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
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		assertEquals(new Customer(f_name, l_name, email, phone), custDAO.readLatest());
	}

	@Test
	public void testRead() {
		final long ID = 1L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		
		assertEquals(new Customer(f_name, l_name, email, phone), custDAO.read(ID));
	}

	@Test
	public void testUpdate() {
		final Long id = 1L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		final Customer updated = new Customer(id, f_name, l_name, email, phone);
		assertEquals(updated, custDAO.update(updated));

	}
	
	
	@Test
	public void testDelete() {
		assertEquals(1L, custDAO.deleteById(1L));
	}
	
	@Test
	public void testConvert() {
		final long id = 1L;
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		final ResultSet convert = new Customer(id, f_name, l_name, email, phone);
		assertEquals(convert, custDAO.convert(convert));
	}
}

	/**
	@Test
	public void createException() {
		Customer customer = new Customer("Piers", "Barber","email",2321);
		assertEquals(null, custDAO.create(customer));
	}
	
	@Test
	
	public void createException1() throws SQLException{
		Customer customer
		
		when(dbconnection.getConnection()),DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
	
	**/

