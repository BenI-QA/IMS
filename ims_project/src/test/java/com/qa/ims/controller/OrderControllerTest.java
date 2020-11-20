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
		private OrderDAO orderDAO;
		
		
		@Mock
		private Utils utils;
			
		@InjectMocks
		private OrderController orderCon;
		
		@Test
		public void testCreate() {
			
		    final Date order_date = utils.getDate();
		    final Long cust_id = 2L;
			final Long item_id = 3L;
			final Long quantity = 5L;
			
		
			final Order order = new Order(order_date, item_id, quantity, cust_id);
			
				
				when(utils.getDate()).thenReturn(order_date);
				when(utils.getLong()).thenReturn(item_id,quantity,cust_id);
				when(orderDAO.create(order)).thenReturn(order);
			
				assertEquals(order, orderCon.create());
			
				verify(utils, times(1)).getDate();
				verify(utils, times(3)).getString();
				verify(utils, times(3)).getLong();
				verify(orderDAO, times(1)).create(order);
				
				
		}
		@Test
		public void testRead() {
			final Long id = 3L;
			final Date order_date = utils.getDate();
			final Long item_id = 3L;
			final Long quantity = 5L;
			final Long cust_id = 2L;
			String item_name = "adidas";
			final double price = 15;
			final double tprice = 44;
			final String selection = "all";
			List<Order> orders = new ArrayList<>();
			orders.add(new Order(id, cust_id, item_name, price, quantity));
			orders.add(new Order(id, order_date, tprice, cust_id));
			orders.add(new Order(id, order_date, item_id, quantity, cust_id));
			
			when(utils.getString()).thenReturn(selection);
			when(orderDAO.readAll()).thenReturn(orders);

			assertEquals(orders, orderCon.readAll());

			verify(orderDAO, times(1)).readAll();
			
		}
		@Test
		public void testUpdate() {
			final Date order_date = utils.getDate();
			final Long cust_id = 2L;
			final Long item_id = 3L;
			final Long quantity = 5L;
			final Order update = new Order(order_date, item_id, quantity, cust_id);
			
			
			when(this.utils.getDate()).thenReturn(update.getOrder_date());
			when(this.utils.getLong()).thenReturn(update.getItem_id(), update.getQuantity(), update.getCust_id() );
		
			
			
			assertEquals(update, this.orderCon.update());
			
			verify(this.utils, times(1)).getLong();
			verify(this.utils, times(2)).getString();
			verify(this.orderDAO, times(1)).update("add", 2L, 4L , 5L);
			
		}
		@Test
		public void testDelete() {
			final long id = 1;

			when(utils.getLong()).thenReturn(id);
			when(orderDAO.deleteById(id)).thenReturn(1);

			assertEquals(1L, this.orderCon.delete());

			verify(utils, times(1)).getLong();
			verify(orderDAO, times(1)).deleteById(id);
			
		}

}
