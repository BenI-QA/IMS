package com.qa.ims.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.qa.ims.domain.Customer;
import com.qa.ims.connection.db_Connection;
import java.util.Scanner;

public class CustomerDAO {
	
	db_Connection DBInstance;
	ResultSet res  = null;
	Scanner scanner = new Scanner(System.in);
	//inputting login details to database
	public CustomerDAO(){
		String username = "root"; //scanner.nextLine();
		String password = "Passwordunknown123!"; //scanner.nextLine();
		DBInstance = db_Connection.connect(username , password); //username, password
		
	}
	
	public void create(Customer cust) {
		String query;
		String f_name = cust.getFirst_name();
		String l_name = cust.getLast_name();
		String email = cust.getEmail();
		int phone = cust.getPhone();
		query = "INSERT INTO Customer (first_name, last_name, email, phone_Num) VALUES('" + f_name + "', '" + l_name +"', '" + email+ "', " + phone +");";
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet read(int id) {
		//selects a single customer based on their id
		String query = "SELECT* FROM Customer WHERE customer_id =" + id;
		try{
			
			res = DBInstance.exQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	public ResultSet readAll() {
		//selects all customer information from customer table 
		String query = "SELECT* FROM Customer";
		
		try{
			
			res = DBInstance.exQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	
	public void update(Customer cust) {
		String query;
		String f_name = cust.getFirst_name();
		String l_name = cust.getLast_name();
		String email = cust.getEmail();
		int phone = cust.getPhone();
		query = "UPDATE Customer SET email = '" + email + "', phone_Num = " + phone +" WHERE first_name = '" + f_name+
				"' AND last_name = '"+l_name+"';";
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Customer Details for:" + f_name + " "  + l_name + "have been Updated!");
	
	}
	
	
	public void deleteById(int id) {
		String query;
		query = "DELETE FROM Customer WHERE customer_id = " + id;
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteByName(String firstname, String lastname) {
		String query;
		query = "DELETE FROM Customer WHERE first_name = " + firstname + " AND last_name = " + lastname;
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

}
