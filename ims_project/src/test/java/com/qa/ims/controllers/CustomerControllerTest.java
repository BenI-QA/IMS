package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Customer;
import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.dao.CustomerDAO;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Mock
	private DBUtils db_Conn;	
	
	@Mock
	private Utils utils;
		
	@Mock
	private CustomerDAO custDAO;
		
	@InjectMocks
	private CustomerController custCon;
	
	
	@Test
	public void testCreate() {
		final String f_name ="jake";
		final String l_name = "mac";
		final String email = "jm@gmail.com";
		final Long phone = 0232323L ;
		final Customer customer = new Customer(f_name, l_name, email, phone);
			
		when(utils.getString()).thenReturn(f_name, l_name, email);
		when(utils.getLong()).thenReturn(phone);
		when(custDAO.create(customer)).thenReturn(customer);

		assertEquals(customer, custCon.create());

		verify(utils, Mockito.times(3)).getString();
		verify(utils, Mockito.times(1)).getLong();
		verify(custDAO, Mockito.times(1)).create(customer);
			
	}
	public void testRead() {
		Long id = 2L;
		String f_name ="jake";
		String l_name = "mac";
		String email = "jm@gmail.com";
		Long phone = 0232323L ;
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(id, f_name, l_name, email, phone));

		when(custDAO.readAll()).thenReturn(customers);

		assertEquals(customers, custCon.readAll());

		verify(custDAO,times(1)).readAll();
	}
	public void testUpdate() {
		Long id = 2L;
		String f_name ="jake";
		String l_name = "mac";
		String email = "jm@gmail.com";
		Long phone = 0232323L ;
		Customer updated = new Customer(id, f_name, l_name, email, phone);
			
		when(this.utils.getLong()).thenReturn(1L);
		when(this.utils.getString()).thenReturn(updated.getFirst_name(), updated.getLast_name());
		when(this.custDAO.update(updated)).thenReturn(updated);

		assertEquals(updated, this.custCon.update());
	
		verify(this.utils,times(2)).getString();
		verify(this.utils, times(1)).getLong();
		verify(this.custDAO, Mockito.times(1)).update(updated);
			
	}
	public void testDelete() {
		final long id = 1L;
		when(utils.getLong()).thenReturn(id);
		when(custDAO.delete(id)).thenReturn(1);
		assertEquals(1L, this.custDAO.delete(id));
		verify(utils, times(1)).getLong();
		verify(custDAO, times(1)).delete(ID);
			
		}
	
}
