package com.qa.ims.controller;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.dao.CustomerDAO;
import com.qa.ims.domain.Customer;

public class MenuCus implements CrudController<Customer>  {
	
	private static Logger LOGGER = LogManager.getLogger();
	CustomerDAO custDAO = new CustomerDAO();
	Scanner scanner = new Scanner(System.in);
	Scanner scanner2 = new Scanner(System.in);
	
	
	@Override
	public Customer create() {
		
		LOGGER.info("First Name?");
		String f_name = scanner.nextLine();
		LOGGER.info("Surname Name?");
		String l_name = scanner.nextLine();
		LOGGER.info("Email?");
		String email = scanner.nextLine();
		LOGGER.info("Phone Number?");
		int phone = scanner.nextInt();
		custDAO.create(new Customer(f_name, l_name, email, phone));
		LOGGER.info("Customer created");
		return null; //new customer which was created;
	
	}
	@Override
	public Customer read() {
		LOGGER.info("View all or View an individual Customers data?");
		LOGGER.info("  1) All Customers  2) Single Customer");
		int list = scanner.nextInt();
		ResultSet result;
		switch (list) {
			case 1:
				result = custDAO.readAll();
				try {
					while(result.next()) {
						
						LOGGER.info(result.getString("first_name") + " " + result.getString("last_name") + " " + result.getString("email") + " " + result.getInt("phone_num") + "\n");
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				//obtains an individual customers information based on their id
				LOGGER.info("Select Customer ID");
				int id = scanner.nextInt();
				result = custDAO.read(id);
				
				try {
					while(result.next()) {
						
						LOGGER.info(result.getString("first_name") + " " + result.getString("last_name") + " " + result.getString("email") + " " + result.getInt("phone_num") + "\n");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
		}
		return null;
	}

	@Override
	public Customer update() {

		ResultSet read = custDAO.readAll();
		try {
			while(read.next()) {
				LOGGER.info(read.getString("first_name") + " " + read.getString("last_name") + " " + read.getString("email") + " " + read.getInt("phone_num"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		LOGGER.info("State the first name of Customer you wish to update");
		String f_name = scanner.nextLine();
		LOGGER.info("State the Last name of Customer you wish to update");
		String l_name = scanner.nextLine();
		LOGGER.info("Change customer email?");
		String email = scanner.nextLine();
		LOGGER.info("Change customer phone Number?");
		int phone = scanner.nextInt();
		f_name = f_name.toLowerCase();
		l_name = l_name.toLowerCase();
		custDAO.update(new Customer(f_name, l_name, email, phone));
	
		return null;
	}

	@Override
	public Customer delete() {
		//user can select either to delete a customer from system with either their id or name
				LOGGER.info("Do you watch to delete by Customer ID or Customer Name?");
				LOGGER.info("  1) ID \n  2) Customer Name");
				int list = scanner.nextInt();
				switch (list) {
					case 1:
						LOGGER.info("    Select by ID:");
						int id = scanner.nextInt(); 
						custDAO.deleteById(id);
						break;
					case 2:
						LOGGER.info("    Select by First name:");
						String f_name = scanner.nextLine(); 
						LOGGER.info("    Select by Last name:");
						String l_name = scanner2.nextLine(); 
						custDAO.deleteByName(f_name, l_name);
						break;
				}
		return null;//Customer id which was deleted;
	}

}
