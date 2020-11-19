package com.qa.ims.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Customer;
import com.qa.ims.util.DBUtils;
import com.qa.ims.util.Utils;
import com.qa.ims.util.db_Connection;
import static org.junit.Assert.assertEquals;

import java.sql.DriverManager;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTestSQLFail {
	
	@Mock
	private DBUtils dbutils;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	CustomerDAO custDAO;
	

	
	
	@Test
	public void createException() {
		Customer customer = new Customer("Piers", "Barber","email",2321L);
		assertEquals(null, custDAO.create(customer));
	}
	@Test
	public void readException() {
		assertEquals(null, custDAO.readAll());
	}
	@Test
	public void readLatestException() {
		assertEquals(null, custDAO.readLatest());
	}
	

	@Test
	public void updateException() {
		Customer updated = new Customer("Piers", "Barber","email",2321L);
		assertEquals(null, custDAO.update(updated));
	}
	

	/*public void createException1() throws SQLException{
		Customer customer
		
		when(dbconnection.getConnection()),DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}*/
	
	
}

		
