package com.qa.ims.controller;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.qa.ims.dao.CustomerDAO;
import com.qa.ims.domain.Customer;
import com.qa.ims.util.Utils;


public class CustomerController implements CrudController<Customer>  {
	
	private static Logger LOGGER = LogManager.getLogger();
	CustomerDAO custDAO;
	Utils util = new Utils();
	
	Scanner scanner = new Scanner(System.in);
	
	public CustomerController(CustomerDAO customerDAO) {
		super();
		this.custDAO = customerDAO;
	}
	
	/**
	 * Creates a customer by taking in user input
	 */
	
	@Override
	public Customer create() {
		
		LOGGER.info("First Name?");
		String f_name = util.getString();
		LOGGER.info("Surname Name?");
		String l_name = util.getString();
		LOGGER.info("Email?");
		String email = util.getString();
		LOGGER.info("Phone Number?");
		Long phone = util.getLong();
		Customer customer = custDAO.create(new Customer(f_name, l_name, email, phone));
		LOGGER.info("Customer created");
		 //new customer which was created;
		return customer;
	
	}
	
	
	/**
	 * Reads all customers or an individual customer
	 */
	
	@Override
	public List<Customer> readAll() {
		LOGGER.info("View all or View an individual Customer data?");
		LOGGER.info("  1) All \n  2) Single \n  ");
		String selection = util.getString();
		if(selection == "all") {
			LOGGER.info("List Of Customers: \n");
			List<Customer> customers = custDAO.readAll();
			for (Customer customer : customers) {
				LOGGER.info(customer.toString());
			}
			return customers;	
			}
		
		else {
			//obtains an individual item information based on its id
			LOGGER.info("Select Customer ID: \n");
			long id = scanner.nextInt();
		
			LOGGER.info("Customer Information: \n");
			List<Customer> singleCust= custDAO.read(id);
			for (Customer customer : singleCust) {
				LOGGER.info(customer.toString());
			}
			return singleCust;
		
		}
	}
	
	/**
	 * Updates an existing customer by taking in user input
	 */
	

	@Override
	public Customer update() {
		
		readAll();	
		LOGGER.info("\n");	
		LOGGER.info("State the first name of Customer you wish to update");
		String f_name = util.getString();
		LOGGER.info("State the Last name of Customer you wish to update");
		String l_name = util.getString();
		LOGGER.info("Change customer email?");
		String email = util.getString();
		LOGGER.info("Change customer phone Number?");
		Long phone = util.getLong();
		f_name = f_name.toLowerCase();
		l_name = l_name.toLowerCase();
		custDAO.update(new Customer(f_name, l_name, email, phone));
	
		return null;
	}
	
	/**
	 * Deletes an existing customer by the id or name of the customer
	 * 
	 * @return
	 */
	
	@Override
	public Customer delete() {
		//user can select either to delete a customer from system with either their id or name
				LOGGER.info("Do you watch to delete by Customer ID or Customer Name?");
				LOGGER.info("  1) ID \n  2) Name");
				String option = util.getString().toLowerCase();
				switch (option) {
					case "id":
						LOGGER.info("    Select by ID:");
						long id = util.getLong();
						custDAO.deleteById(id);
						break;
					case "name":
						LOGGER.info("    Select by First name:");
						String f_name = util.getString();
						LOGGER.info("    Select by Last name:");
						String l_name = util.getString();
						custDAO.deleteByName(f_name, l_name);
						break;
				}
		return null;
	}

}
