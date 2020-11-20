package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Customer;
import com.qa.ims.util.Utils;
import com.qa.ims.dao.CustomerDAO;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
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

		verify(utils, times(3)).getString();
		verify(utils, times(1)).getLong();
		verify(custDAO, times(1)).create(customer);
			
	}

	@Test
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
	
	@Test
	public void testUpdate() {
		testRead();
		String f_name ="jake";
		String l_name = "mac";
		String email = "change@gmail.com";
		Long phone = 1234L ;
		Customer updated = new Customer(f_name, l_name, email, phone);
			
		when(this.utils.getString()).thenReturn(updated.getFirst_name(), updated.getLast_name(),updated.getEmail());
		when(this.utils.getLong()).thenReturn(updated.getPhone());
		when(this.custDAO.update(updated)).thenReturn(updated);

		assertEquals(updated, this.custCon.update());
	
		verify(this.utils,times(3)).getString();
		verify(this.utils, times(1)).getLong();
		verify(this.custDAO, times(1)).update(updated);
			
	}
	@Test
	public void testDelete() {
		testRead();
		Long id = 1L;
		when(this.utils.getLong()).thenReturn(id);
		when(this.custDAO.deleteById(id)).thenReturn(null);
		assertNull(null,this.custDAO.deleteById(id));
		verify(this.utils, times(1)).getLong();
		verify(this.custDAO, times(1)).deleteById(id);
			
		}
	
}

