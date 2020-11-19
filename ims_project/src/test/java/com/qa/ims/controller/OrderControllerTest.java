package com.qa.ims.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Item;
import com.qa.ims.domain.Order;
import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;
import com.qa.ims.controller.OrderController;
import com.qa.ims.dao.OrderDAO;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

		@Mock
		private DBUtils db_Conn;
		
		@Mock
		private OrderDAO orderDAO;
		
		
		@Mock
		private Utils utils;
			
		@InjectMocks
		private OrderController orderCon;
		
		@Test
		public void testCreate() {
			final long id = 2L;
			final Date utilDate = new java.util.Date();
		    final Date order_date = new java.sql.Date(utilDate.getTime());
			final long item_id = 1L;
			final long quantity = 5L;
			final long cust_id = 2L;
			final Order order = new Order(id, order_date, item_id, quantity, cust_id);
			final Order order2 = new Order(order_date, item_id, quantity, cust_id);
			
			when(utils.getDate()).thenReturn(order_date);
			when(utils.getLong()).thenReturn(id,item_id,quantity,cust_id);
			when(orderDAO.create(order)).thenReturn(order);

			assertEquals(order, orderCon.create());

			verify(utils, times(3)).getString();
			verify(utils, times(1)).getLong();
			verify(orderDAO, times(1)).create(order);
		}
		@Test
		public void testRead() {
			final long id = 2L;
			final Date order_date = utils.getDate();
			final long item_id = 1L;
			final long quantity = 5L;
			final long cust_id = 2L;
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(id, order_date, item_id, quantity, cust_id));

			when(orderDAO.readAll()).thenReturn(orders);

			assertEquals(orders, orderCon.readAll());

			verify(orderDAO, times(1)).readAll();
			
		}
		@Test
		public void testUpdate() {
			final long o_id = 2L;
			final long i_id = 3L;
			final long quant = 2L;
			Item updated =new order(id,));
			when(this.utils.getLong()).thenReturn(1L);
			when(this.utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
			
			when(this.orderDAO.updateAdd(o_id, i_id, quant)).thenReturn(updated);
			when(this.orderDAO.updateEdit(o_id, i_id, quant)).thenReturn(updated);

			assertEquals(updated, this.orderCon.update());

			verify(this.utils, times(1)).getLong();
			verify(this.utils, times(2)).getString();
			verify(this.orderDAO, times(1)).update();
			
		}
		@Test
		public void testDelete() {
			final long id = 1L;

			when(utils.getLong()).thenReturn(id);
			when(orderDAO.deleteById(id)).thenReturn(1);

			assertEquals(1L, this.orderCon.delete());

			verify(utils, times(1)).getLong();
			verify(orderDAO, times(1)).deleteById(id);
			
		}

}
