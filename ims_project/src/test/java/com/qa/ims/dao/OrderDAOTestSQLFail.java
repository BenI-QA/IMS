package com.qa.ims.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Customer;
import com.qa.ims.domain.Order;
import com.qa.ims.util.DBUtils;
import com.qa.ims.util.Utils;


@RunWith(MockitoJUnitRunner.class)
public class OrderDAOTestSQLFail {
	@Mock
	private DBUtils dbutils;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	private OrderDAO orderDAO;

	
	
	@Test
	public void createException() {
		final Date order_date = utils.getDate();
		
		Order order = new Order(order_date, 1L, 5L, 2L);
		assertEquals(null, orderDAO.create(order));
	}
	@Test
	public void readException() {
		assertEquals(null, orderDAO.readAll());
	}
	@Test
	public void readLatestException() {
		assertEquals(null, orderDAO.readLatest());
	}
	

	@Test
	public void updateaddException() {
	
		assertEquals(null, orderDAO.updateAdd(1L,2L,4));
	}
	@Test
	public void updateEditException() {
	
		assertEquals(null, orderDAO.updateEdit("edit",1L,2L,4));
	}
}
