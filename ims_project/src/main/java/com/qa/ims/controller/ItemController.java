package com.qa.ims.controller;


import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.domain.Item;
import com.qa.ims.util.Utils;
import com.qa.ims.dao.ItemDAO;

public class ItemController implements CrudController<Item>{
	
	private static Logger LOGGER = LogManager.getLogger();
	ItemDAO itemDAO = new ItemDAO();
	Utils util = new Utils();
	
	public ItemController(ItemDAO itemDAO, Utils util) {
		super();
		this.itemDAO = itemDAO;
		this.util = util;
	}
	
	/**
	 * Creates a Items by taking in user input
	 */
	
	@Override
	public Item create() {
		LOGGER.info("Shoe Name");
		String item_name = util.getString();
		LOGGER.info("Shoe Size");
		double size = util.getDouble();
		LOGGER.info("Set Price");
		double price = util.getDouble();
		LOGGER.info("Number in Stock");
		long stock = util.getLong();
		Item newItem = itemDAO.create(new Item(item_name, size, price, stock));
		LOGGER.info("\n");
		return newItem;
	}
	
	/**
	 * Reads all items
	 */

	@Override
	public List<Item> readAll() {
		LOGGER.info("List Of Items: \n");
		List<Item> items = itemDAO.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		
		return items;	
		
	}
	

	/**
	 * Updates an existing items by taking in user input
	 */
	
	@Override
	public Item update() {
		
		readAll();
	
		LOGGER.info("State the shoe name you wish to update");
		String name = util.getString().toLowerCase();;
		LOGGER.info("State the size you wish to update");
		double size = util.getDouble();
		LOGGER.info("Change price");
		double price = util.getDouble();
		LOGGER.info("Change amount in stock");
		long stock = util.getLong();
		LOGGER.info("\n");
		return itemDAO.update(new Item(name,size,price,stock));
		
		
		
	}
	
	/**
	 * Deletes an existing item by the id or name of the item
	 * 
	 * 
	 */

	@Override
	public Item delete() {
		readAll();
		LOGGER.info("\n");
		//user can select either to delete a customer from system with either their id or name
		LOGGER.info("Do you want to delete record by Item ID");
		long id = util.getLong();
		itemDAO.deleteById(id);
				
				
		return null;
	}

	
}
