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
	
	public CustomerController(CustomerDAO customerDAO, Utils util) {
		super();
		this.custDAO = customerDAO;
		this.util = util;
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
		LOGGER.info("\n");
		LOGGER.info("Customer created");
		 //new customer which was created;
		LOGGER.info("\n");
		return customer;
		
	}
	
	
	/**
	 * Reads all customers or an individual customer
	 */
	
	@Override
	public List<Customer> readAll() {

		LOGGER.info("List Of Customers: \n");
		List<Customer> customers = custDAO.readAll();
		for (Customer customer : customers) {
				LOGGER.info(customer.toString());

		}
		LOGGER.info("\n");
		return customers;	

		
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
		LOGGER.info("\n");
		return custDAO.update(new Customer(f_name, l_name, email, phone));
		
	}
	
	/**
	 * Deletes an existing customer by the id or name of the customer
	 * 
	 * @return
	 */
	
	@Override
	public Customer delete() {
		//user can select either to delete a customer from system with either their id or name
				readAll();
				
				LOGGER.info("    Select by ID:");
				Long id = util.getLong();
				custDAO.deleteById(id);
			
				LOGGER.info("\n");
		return null;
	}

}
