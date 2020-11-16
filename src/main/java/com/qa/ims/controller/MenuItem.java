package com.qa.ims.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.domain.Item;
import com.qa.ims.dao.ItemDAO;

public class MenuItem implements CrudController<Item>{
	
	private static Logger LOGGER = LogManager.getLogger();
	ItemDAO itemDAO = new ItemDAO();
	Scanner scanner = new Scanner(System.in);
	Scanner scanner2 = new Scanner(System.in);
	
	@Override
	public Item create() {
		LOGGER.info("Shoe Name");
		String Shoe_name = scanner.nextLine();
		LOGGER.info("Shoe Size");
		int size = scanner.nextInt();
		LOGGER.info("Shoe Brand");
		String brand = scanner.nextLine();
		LOGGER.info("Set Price");
		int price = scanner.nextInt();
		LOGGER.info("Number in Stock");
		int stock = scanner.nextInt();
		
		itemDAO.create(new Item(Shoe_name, size, brand, price, stock));
		return null;
	}

	@Override
	public Item read() {
		LOGGER.info("View all or View an individual Item data?");
		LOGGER.info("  1) All Item  2) Single Item");
		int select2= scanner.nextInt();
		
		ResultSet result;
		switch (select2) {
			case 1:
				result = itemDAO.readAll();
				try {
					while(result.next()) {
						
						LOGGER.info(result.getString("item_name") + " " + result.getString("size") + " " + result.getString("brand") + " " + result.getString("price") + " " + result.getInt("stock") + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				//obtains an individual item information based on its id
				LOGGER.info("Select Item ID");
				int id = scanner.nextInt();
				result = itemDAO.read(id);
				
				try {
					while(result.next()) {
						
						LOGGER.info(result.getString("item_name") + " " + result.getString("size") +" " + result.getString("brand") + " " + result.getString("price") + " " + result.getInt("stock") + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
		}
		return null;
	}

	@Override
	public Item update() {
		
		return null;
	}

	@Override
	public Item delete() {
		//user can select either to delete a customer from system with either their id or name
				LOGGER.info("Do you want to delete record by Item ID or Item Name?");
				LOGGER.info("  1) ID \n  2) Item Name");
				int select2 = scanner.nextInt();
				switch (select2) {
					case 1:
						LOGGER.info("    Select by ID:");
						int id = scanner.nextInt(); 
						itemDAO.deleteById(id);
						break;
					case 2:
						LOGGER.info("    Select by Item Name:");
						String name = scanner2.nextLine(); 
						itemDAO.deleteByName(name);
						break;
				}
		return null;
	}
}
