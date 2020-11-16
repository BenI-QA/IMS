package com.qa.ims.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.dao.OrderDAO;
import com.qa.ims.domain.Order;

public class MenuOrder implements CrudController<Order>{
	
	private static Logger LOGGER = LogManager.getLogger();
	OrderDAO orderDAO = new OrderDAO();
	Scanner scanner = new Scanner(System.in);
	Scanner scanner2 = new Scanner(System.in);
		
	@Override
	public Order create() {
		Date utilDate = new java.util.Date();
	    Date order_date = new java.sql.Date(utilDate.getTime());

		LOGGER.info("Select id of customer you wish make an Order for");
		int cust_id = scanner.nextInt();
		int counter = 0;
		while(counter < 10) {
			if (counter == 0) {
				LOGGER.info("Add to Order");
			}
			else {
				LOGGER.info("Would you like to add another item to your order?");
				String another = scanner.nextLine();
				if(another.toLowerCase() == "yes") {
					break;
				}
			}
			//read all shoes here
			LOGGER.info("Select the id of the shoe from above");
			int item_id = scanner.nextInt();
			LOGGER.info("How many of this pair would you like to purchase?");
			int quantity = scanner.nextInt();
			
			if(counter == 0) {
			orderDAO.create(new Order(order_date, item_id, quantity, cust_id));
			counter++;
			}
			else {
				ResultSet result;
				
				result = orderDAO.read(cust_id);
				try {
					while(result.next()) {
						
						LOGGER.info(result.getString("order_id") + " " + result.getString("customer_id") + " " +result.getString("order_date") + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				LOGGER.info("Input Order ID of your order?");
				int order_id = scanner.nextInt();
				orderDAO.update(order_id, item_id, quantity);
			}
		}
		return null;
	}


	@Override
	public Order read() {
		LOGGER.info("View all or View an individual Order data?");
		LOGGER.info("  1) All Order  2) Single Order");
		int select2= scanner.nextInt();
		
		ResultSet result;
		switch (select2) {
			case 1:
				result = orderDAO.readAll();
				try {
					while(result.next()) {
						
						LOGGER.info(result.getString("order_id") + " " + result.getString("customer_id") + " " +result.getString("order_date")+ " " + result.getString("Total_Price"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				//obtains an individual item information based on its id
				LOGGER.info("Select Customer ID");
				int id = scanner.nextInt();
				result = orderDAO.read(id);
				
				try {
					while(result.next()) {
						
						LOGGER.info(result.getString("order_id") + " " + result.getString("customer_id") + " " +result.getString("order_date") +  " " + result.getString("Total_Price"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
		}
		return null;
	}


	@Override
	public Order update() {
		
		return null;
	}


	@Override
	public Order delete() {
		//User can delete a customer order from system through order id or delete customer item from a specific order
		
				LOGGER.info("  1) Delete Customer Order  2) Delete Item from Order");
				
				int selection = scanner.nextInt(); 
				
				switch (selection) {
					case 1:
						LOGGER.info("Select Order ID you wish to delete");
						int id = scanner.nextInt(); 
						orderDAO.deleteById(id);
						break;
					case 2:
						LOGGER.info("Select Order ID");
						int order_id = scanner.nextInt(); 
						LOGGER.info("Select Item ID");
						int item_id = scanner.nextInt(); 
						orderDAO.deleteItem(order_id, item_id);
						break;
				}
		return null;
	}

}
