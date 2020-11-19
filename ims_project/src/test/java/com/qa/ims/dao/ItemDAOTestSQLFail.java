package com.qa.ims.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Item;
import com.qa.ims.util.DBUtils;
import com.qa.ims.util.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemDAOTestSQLFail {
	@Mock
	private DBUtils dbutils;
	
	@Mock
	private Utils utils;
	
	@InjectMocks
	ItemDAO itemDAO;
	

	
	
	@Test
	public void createException() {
		Item item = new Item("adidas", 11, 15 , 23L);
		assertEquals(null,  itemDAO.create(item));
	}
	@Test
	public void readException() {
		assertEquals(null, itemDAO.readAll());
	}
	@Test
	public void readLatestException() {
		assertEquals(null, itemDAO.readLatest());
	}
	

	@Test
	public void updateException() {
		Item updated= new Item("adidas", 11, 15 , 23L);
		assertEquals(null, itemDAO.update(updated));
	}
	
}
