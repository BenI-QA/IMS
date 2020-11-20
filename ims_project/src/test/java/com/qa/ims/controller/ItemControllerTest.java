package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.domain.Item;
import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;
import com.qa.ims.controller.ItemController;
import com.qa.ims.dao.ItemDAO;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
			
		@Mock
		private ItemDAO itemDAO;
		
			
		@Mock
		private Utils utils;
			
		@InjectMocks
		private ItemController itemCon;
		
		@Test
		public void testCreate() {
			final String item_name ="nikey";
			final double size = 12;
			final double price = 13.00;
			final Long stock = 5L;
			Item item = new Item(item_name, size, price,stock);	
			when(utils.getString()).thenReturn(item_name);
			when(utils.getDouble()).thenReturn(size, price);
			when(utils.getLong()).thenReturn(stock);
			when(itemDAO.create(item)).thenReturn(item);

			assertEquals(item, itemCon.create());

			verify(utils, times(3)).getString();
			verify(utils, times(1)).getLong();
			verify(itemDAO, times(1)).create(item);
			
		}
		@Test
		public void testRead() {
			final String item_name ="nikey";
			final double size = 12;
			final double price = 13.00;
			final Long stock = 5L;
			List<Item> items = new ArrayList<>();
			items.add(new Item(item_name, size, price,stock));

			when(itemDAO.readAll()).thenReturn(items);

			assertEquals(items, itemCon.readAll());

			verify(itemDAO, times(1)).readAll();
		}
		@Test
		public void testUpdate() {
			final String item_name ="nikey";
			final long size = 12;
			final double price = 15.00;
			final long stock = 2;
			Item updated =new Item(item_name, size, price,stock);

			when(this.utils.getString()).thenReturn(updated.getItem_name());
			when(this.utils.getLong()).thenReturn(updated.getStock());
			when(this.utils.getDouble()).thenReturn(updated.getSize(),updated.getPrice());
			
			when(this.itemDAO.update(updated)).thenReturn(updated);
			assertEquals(updated, itemCon.update());
			
			verify(this.utils, times(1)).getString();
			verify(this.utils, times(2)).getLong();
			verify(this.utils, times(1)).getDouble();
			verify(this.itemDAO, times(1)).update(updated);
			
		}
		@Test
		public void testDelete() {
			final long id = 1L;

			when(this.utils.getLong()).thenReturn(id);
			when(this.itemDAO.deleteById(id)).thenReturn(null);

			assertNull(null, itemCon.delete());

			verify(utils, times(1)).getLong();
			verify(itemDAO, times(1)).deleteById(id);
			
		}

}
