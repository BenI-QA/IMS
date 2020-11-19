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
import com.qa.ims.controller.OrderController;
import com.qa.ims.domain.Customer;
import com.qa.ims.domain.Item;
import com.qa.ims.domain.Order;
import java.util.Date;


public class OrderDAOTest {
	
	private Utils util;
	private final OrderDAO orderDAO = new OrderDAO();
	
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
		final long id = 2L;
		final Date order_date = util.getDate();
		final long item_id = 1L;
		final long quantity = 5L;
		final long cust_id = 2L;
		final Order order = new Order(id, order_date, item_id, quantity, cust_id);
		final Order order2 = new Order(order_date, item_id, quantity, cust_id);
		assertEquals(order, orderDAO.create(order));
		assertEquals(order2, orderDAO.create(order2));
	}

	@Test
	public void testReadAll() {
		final long id = 2L;
		final Date utilDate = new java.util.Date();
	    final Date order_date = new java.sql.Date(utilDate.getTime());
		final long item_id = 1L;
		final long quantity = 5L;
		final long cust_id = 2L;
		List<Order> expected = new ArrayList<>();
		final Order order = new Order(id, order_date, item_id, quantity, cust_id);
		assertEquals(expected, orderDAO.create(order));
	}

	@Test
	public void testReadLatest() {
		final long id = 2L;
		final Date utilDate = new java.util.Date();
	    final Date order_date = new java.sql.Date(utilDate.getTime());
		final long item_id = 1L;
		final long quantity = 5L;
		final long cust_id = 2L;
		assertEquals(new Order(id, order_date, item_id, quantity, cust_id), orderDAO.readLatest());
	}
	

	@Test
	public void testRead() {
		final long id = 2L;
		final String item_name = "addias";
		final long quantity = 5L;
		final double price = 13.00;
		final long cust_id = 2L;
		assertEquals(new Order(id, cust_id, item_name, price, quantity), orderDAO.read(id));
	}


	@Test
	public void testUpdateAdd() {
		final String select = "edit";
		final long o_id = 2L;
		final long i_id = 3L;
		final long quant = 2L;
		assertEquals(new Order(o_id, i_id, quant), orderDAO.updateAdd(o_id, i_id, quant));

	}
	@Test
	public void testUpdateEdot() {
		final long o_id = 2L;
		final long i_id = 3L;
		final long quant = 2L;
		assertEquals(new Order(o_id, i_id, quant), orderDAO.updateEdit(o_id, i_id, quant));

	}
	
	
	@Test
	public void testDelete() {
		assertEquals(1L, orderDAO.deleteById(1L));
	}
	
	@Test
	public void createException() {
		Item item = new Item("nikey", 12, 13.00, 5L);
		assertEquals(null, orderDAO.create(item));
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
	public void updateException() {
		Order updated = new Order("nikey", 12, 13.00, 5L);
		assertEquals(null, orderDAO.update(updated));
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

