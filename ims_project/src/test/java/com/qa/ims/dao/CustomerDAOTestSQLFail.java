package com.qa.ims.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Customer;
import com.qa.ims.util.db_Connection;
import static org.junit.Assert.assertEquals;

import java.sql.DriverManager;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTestSQLFail {
	
	@Mock
	private db_Connection dbConnect;
	
	@InjectMocks
	CustomerDAO customerdao;
	
	@Test
	public void createException() {
		Customer customer = new Customer("Piers", "Barber","email",2321);
		assertEquals(null, customerdao.create(customer));
	}
	
	@Test
	
	public void createException() throws SQLException{
		Customer customer
		
		when(dbconnection.getConnection()),DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
}

	public CustomerDAO(DBUtils dbutils){
		this.dbutils = dbutils;
	}
		
