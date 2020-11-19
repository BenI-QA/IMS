package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.DriverManager;
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
import com.qa.ims.domain.Item;
import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;
import com.qa.ims.controller.ItemController;
import com.qa.ims.dao.ItemDAO;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	

	//mockito can create madeup inputs for objects
		@Mock
		private DBUtils db_Conn;
		
		@Mock
		private ItemController itemCon;
		
			
		@Mock
		private Utils utils;
			
		@InjectMocks
		private ItemDAO itemDAO;
		
		@Test
		public void testCreate() {
			final String item_name ="nikey";
			final long size = 12;
			final double price = 13.00;
			final long stock = 5;
			Item item = new Item(item_name, size, price,stock);	
			when(utils.getString()).thenReturn(item_name);
			when(utils.getLong()).thenReturn(size,stock);
			when(utils.getDouble()).thenReturn(price);
			when(itemDAO.create(item)).thenReturn(item);

			assertEquals(item, itemCon.create());

			verify(utils, times(3)).getString();
			verify(utils, times(1)).getLong();
			verify(itemDAO, times(1)).create(item);
			
		}
		public void testRead() {
			final String item_name ="nikey";
			final long size = 12;
			final double price = 13.00;
			final long stock = 5;
			List<Item> items = new ArrayList<>();
			items.add(new Item(item_name, size, price,stock));

			when(itemDAO.readAll()).thenReturn(items);

			assertEquals(items, itemCon.readAll());

			verify(itemDAO, times(1)).readAll();
		}
		public void testUpdate() {
			final String item_name ="nikey";
			final long size = 12;
			final double price = 13.00;
			final long stock = 5;
			Item updated =new Item(item_name, size, price,stock);

			when(this.utils.getLong()).thenReturn(1L);
			when(this.utils.getString()).thenReturn(updated.getItemName(), updated.getSurname());
			when(this.itemDAO.update(updated)).thenReturn(updated);

			assertEquals(updated, this.itemCon.update());

			verify(this.utils, times(1)).getLong();
			verify(this.utils, times(2)).getString();
			verify(this.itemDAO, times(1)).update(updated);
			
		}
		public void testDelete() {
			final long id = 1L;

			when(utils.getLong()).thenReturn(id);
			when(itemDAO.deleteById(id)).thenReturn(1);

			assertEquals(1L, this.itemCon.delete());

			Mockito.verify(utils, times(1)).getLong();
			Mockito.verify(itemDAO, times(1)).deleteById(id);
			
		}

}
