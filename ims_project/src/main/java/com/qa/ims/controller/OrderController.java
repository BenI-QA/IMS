package com.qa.ims.controller;


import java.util.Scanner;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.dao.CustomerDAO;
import com.qa.ims.dao.ItemDAO;
import com.qa.ims.dao.OrderDAO;
import com.qa.ims.domain.Customer;
import com.qa.ims.domain.Item;
import com.qa.ims.domain.Order;
import com.qa.ims.util.Utils;

public class OrderController implements CrudController<Order>{
	
	private static Logger LOGGER = LogManager.getLogger();
	ItemDAO itemDAO = new ItemDAO();
	OrderDAO orderDAO = new OrderDAO();
	CustomerDAO custDAO = new CustomerDAO();
	Utils util = new Utils();
	Scanner scanner = new Scanner(System.in);
		
	public OrderController(OrderDAO orderDAO) {
		super();
		this.orderDAO = orderDAO;
	
	}
	
	/**
	 * Creates a orders by taking in user input
	 */
	
	@Override
	public Order create() {
		
		Date utilDate = new java.util.Date();
	    Date order_date = new java.sql.Date(utilDate.getTime());
	    List<Customer> customers = custDAO.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer.toString());
		}
	    custDAO.readAll();
		LOGGER.info("\n Select id of customer you wish make an Order for");
		long cust_id = util.getLong();
		int counter = 0;
		while(counter < 5) {
			if (counter == 0) {
				List<Item> items = itemDAO.readAll();
				for (Item item : items) {
					LOGGER.info(item.toString());
				}
				LOGGER.info("Add to Order \n ");
			}
			
			
			//read all shoes here
			LOGGER.info("Select the id of the shoe from above \n ");
			long item_id = util.getLong();
			LOGGER.info("How many of this pair would you like to purchase?\n ");
			long quantity = util.getLong();
			
			if(counter == 0) {
			orderDAO.create(new Order(order_date, item_id, quantity, cust_id));
			counter++;
			}
			else {
				
				LOGGER.info("List Of Orders: \n");
				orderDAO.readAll();
				
				LOGGER.info("Input Order ID of your order?");
				long order_id =  util.getLong();
				orderDAO.updateAdd(order_id, item_id, quantity);
				counter++;
			}
			
			
		}
		return null;
		
	}
	
	/**
	 * Reads all orders or a customer individual orders 
	 */


	@Override
	public List<Order> readAll() {
		LOGGER.info("View all or View an individual Order data?");
		LOGGER.info("  1) All \n   2) Single \n");
		long selection=  util.getLong();
	
		if (selection == 1) {
				LOGGER.info("List Of Orders: \n");
				List<Order> orders = orderDAO.readAll();
				for (Order order : orders) {
					LOGGER.info(order.toStringTotal()+"\n");
				}
				
				return orders;
		}else {
	
				//obtains an individual item information based on its id
				LOGGER.info("Select Customer ID");
				long id =  util.getLong();
			
				
				LOGGER.info("List Of Orders: \n");
				List<Order> singleOrder= orderDAO.read(id);
				for (Order order : singleOrder) {
					LOGGER.info(order.toStringSingle()+"\n");
				}
				return singleOrder;
			
		}
		
	}


	/**
	 * Updates an existing orders by taking in user input
	 */

	@Override
	public Order update() {
		readAll();
		LOGGER.info("\n Would you like to add to order or edit current order \n");
		LOGGER.info("  1) Add  \n   2) Edit  \n");
		String select=  scanner.nextLine().toLowerCase();
		LOGGER.info("\n State the order ID");
		long o_id=  util.getLong(); 
		LOGGER.info("\n State the item ID");
		long i_id=  util.getLong();
		LOGGER.info("\n State the quantity to be added");
		long quant=  util.getLong();
		
		switch (select) {
			case "add" :
					orderDAO.updateAdd(o_id, i_id, quant);
				break;
			case "edit":
					orderDAO.updateEdit(select,o_id, i_id, quant);
				break;
			case "back":
				break;
		
		}
		return null;
	}
	
	/**
	 * Deletes an existing order by the  id or item name of the order
	 * 
	 * @return
	 */
	

	@Override
	public Order delete() {
		//User can delete a customer order from system through order id or delete customer item from a specific order
		
		LOGGER.info("  1) Delete Customer: CUSTOMER \n  2) Delete Item : ITEM \n");
		
		String selection =  util.getString().toLowerCase();
		
		switch (selection) {
			case "customer":
				LOGGER.info("Select Order ID you wish to delete");
				long id =  util.getLong(); 
				orderDAO.deleteById(id);
				LOGGER.info("This Order has been Deleted");
				orderDAO.readAll();
				break;
			case "item":
				LOGGER.info("Select Order ID \n");
				long order_id =  util.getLong(); 
				LOGGER.info("Select Item ID \n");
				long item_id =  util.getLong();
				orderDAO.deleteItem(order_id, item_id);
				break;
		}
		return null;
	}

}
