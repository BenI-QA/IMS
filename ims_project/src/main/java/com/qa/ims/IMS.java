package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.dao.CustomerDAO;
import com.qa.ims.dao.ItemDAO;
import com.qa.ims.dao.OrderDAO;
import com.qa.ims.util.DBUtils;

import java.util.Scanner;

public class IMS {
	
	public static final Logger LOGGER = LogManager.getLogger();
	DBUtils DBInstance;
	Scanner scanner = new Scanner(System.in);
	private final CustomerController customers;
	private final ItemController items;
	private final OrderController orders;

	public IMS() {
		final CustomerDAO cust= new CustomerDAO();
		final ItemDAO item= new ItemDAO();
		final OrderDAO order= new OrderDAO();
		this.customers = new CustomerController(cust);
		this.items = new ItemController(item);
		this.orders = new OrderController(order);
		
	}

	public void systemStart() {
		LOGGER.info("What is your username");
		String username = scanner.nextLine();
		LOGGER.info("What is your password \n");
		String password = scanner.nextLine();

		DBInstance = DBUtils.connect(username, password);
		
		LOGGER.info(" Select an option below:");
		LOGGER.info("  1) Customer \n  2) Order \n  3) Item \n  4) Exit \n   ");
		
		String select = scanner.nextLine().toLowerCase();
			CrudController<?> active = null;
			switch (select) {
			case "customer":
				active = this.customers;
				break;
			case "item":
				active = this.items;
				break;
			case "order":
				active = this.orders;
				break;
			case "exit":
				LOGGER.info("You Have logged out!");
				return;
			default:
				break;
			}
		boolean check=true;
		while(check) {
			LOGGER.info("What would you like to do with " + select.toLowerCase() + ":");
			LOGGER.info("  1) Create \n  2) Read \n  3) Update \n  4) Delete \n  5) Exit \n ");
			
			String option = scanner.nextLine().toLowerCase();
			doAction(active, option, check);
		}
	}

	public void doAction(CrudController<?> crudController, String option, boolean check) {
		switch (option) {
		case "create":
			crudController.create();
			break;
		case "read":
			crudController.readAll();
			break;
		case "update":
			crudController.update();
			break;
		case "delete":
			crudController.delete();
			break;
		case "exit":
			systemStart();
			break;

		}
	}

}
