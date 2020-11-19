package com.qa.ims.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qa.ims.domain.Customer;
import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CustomerDAO {
	
	private static Logger LOGGER = LogManager.getLogger();
	DBUtils DBInstance;
	ResultSet res  = null;
	Scanner scanner = new Scanner(System.in);
	Utils util = new Utils();
	
	public Customer create(Customer cust) {
		String query;
		String f_name = cust.getFirst_name();
		String l_name = cust.getLast_name();
		String email = cust.getEmail();
		Long phone = cust.getPhone();
		
		query = "INSERT INTO Customer (first_name, last_name, email, phone_Num) VALUES('" + f_name + "', '" + l_name +"', '" + email+ "', " + phone +");";
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(query);
			
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return readLatest();
	
	}
	
	public Customer readLatest() {
		String query;
		query = "SELECT * FROM Customer ORDER BY customer_id DESC LIMIT 1";
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);){
				
			resultSet.next();
				
			return convert(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	public List<Customer> readAll() {
		//selects all customer information from customer table 
		String query = "SELECT* FROM Customer";
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			List<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(convert(resultSet));
			}
			return customers;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
		
	}


	public Customer update(Customer cust) {
		String query;
		String f_name = cust.getFirst_name();
		String l_name = cust.getLast_name();
		String email = cust.getEmail();
		Long phone = cust.getPhone();
		query = "UPDATE Customer SET email = '" + email + "', phone_Num = " + phone +" WHERE first_name = '" + f_name+
				"' AND last_name = '"+l_name+"';";
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				) {
				statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("Customer Details for:" + f_name + " "  + l_name + "have been Updated!");
		return readLatest();
	}
	
	
	public Customer deleteById(long id) {
		String query;
		query = "DELETE FROM Customer WHERE customer_id = " + id;
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
				statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Customer convert(ResultSet result) throws SQLException {
		Long id = result.getLong("customer_id");
		String localFname = result.getString("first_name");
		String localLname = result.getString("last_name");
		String localEmail = result.getString("email");
		Long localPhone = result.getLong("phone_num");
		return new Customer(id,localFname, localLname, localEmail, localPhone);


	}
	
	

}
