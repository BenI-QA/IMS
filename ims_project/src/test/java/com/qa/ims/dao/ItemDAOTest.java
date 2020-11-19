package com.qa.ims.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;
import com.qa.ims.controller.ItemController;
import com.qa.ims.domain.Customer;
import com.qa.ims.domain.Item;


public class ItemDAOTest {
	
	private final ItemDAO itemDAO = new ItemDAO();
	
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
		final long id = 2;
		final String item_name ="nikey";
		final long size = 12;
		final double price = 13.00;
		final long stock = 5;
		final Item item = new Item(id, item_name, size, price,stock);
		assertEquals(item, itemDAO.create(item));
	}

	@Test
	public void testReadAll() {
		final long id = 3L;
		final String item_name ="nikey";
		final long size = 12;
		final double price = 13.00;
		final long stock = 5;
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(id, item_name, size, price,stock));
		assertEquals(expected, itemDAO.readAll());
	}

	@Test
	public void testReadLatest() {
		final long id = 2;
		final String item_name ="nikey";
		final long size = 12;
		final double price = 13.00;
		final long stock = 5;
		assertEquals(new Item(id, item_name, size, price,stock), itemDAO.readLatest());
	}


	@Test
	public void testUpdate() {
		final Long id = 2L;
		final String item_name ="nikey";
		final Long size = 12L;
		final double price = 13.00;
		final Long stock = 5L;
		final Item updated = new Item(id, item_name, size, price,stock);
		assertEquals(updated, itemDAO.update(updated));

	}
	
	
	@Test
	public void testDelete() {
		assertEquals(null, itemDAO.deleteById(1L));
	}
	
	@Test
	public void createException() {
		Item item = new Item("nikey", 12, 13.00, 5L);
		assertEquals(null, itemDAO.create(item));
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
		Item updated = new Item("nikey", 12, 13.00, 5L);
		assertEquals(null, itemDAO.update(updated));
	}
	
}

	/**
	
	
	@Test
	
	public void createException1() throws SQLException{
		Customer customer
		
		when(dbconnection.getConnection()),DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}
	
	**/

