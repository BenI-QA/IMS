package com.qa.ims;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.connection.db_Connection;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.MenuCus;
import com.qa.ims.controller.MenuItem;
import com.qa.ims.controller.MenuOrder;
import java.util.Scanner;

public class IMS {
	
	public static final Logger LOGGER = LogManager.getLogger();
	db_Connection DBInstance;
	Scanner scanner = new Scanner(System.in);
	private final MenuCus customers;
	private final MenuItem items;
	private final MenuOrder orders;

	public IMS() {
		
		this.customers = new MenuCus();
		this.items = new MenuItem();
		this.orders new MenuOrder();
		
	}

	public void menu() {
		LOGGER.info("What is your username");
		String username = scanner.nextLine();
		LOGGER.info("What is your password");
		String password = scanner.nextLine();

		DBInstance = db_Connection.connect(username, password);
		
		LOGGER.info(" Select an option below:");
		LOGGER.info("  1) Customer \n  2) Order \n  3) Item \n  4) EXIT");
		
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
				return;
			default:
				break;
			}

		LOGGER.info("What would you like to do with " + select.toLowerCase() + ":");
		LOGGER.info("  1) Create  2) Read  3) Update 4) Delete  5) Exit");
		String option = scanner.nextLine().toLowerCase();
		doAction(active, option);
			
	}

	public void doAction(CrudController<?> crudController, String option) {
		switch (option) {
		case "create":
			crudController.create();
			break;
		case "read":
			crudController.read();
			break;
		case "update":
			crudController.update();
			break;
		case "delete":
			crudController.delete();
			break;
		case "exit":
			break;
		default:
			break;
		}
	}

}
