package com.qa.ims.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.qa.ims.connection.db_Connection;
import com.qa.ims.domain.Item;


public class ItemDAO {
	db_Connection DBInstance;
	ResultSet res  = null;
	Scanner scanner = new Scanner(System.in);
	//inputting login details to database
	public ItemDAO(){
		
	}
	
	public void create(Item Item) {
		String query;
		String name = Item.getItem_name();
		int size = Item.getSize();
		String brand = Item.getBrand();
		int price = Item.getPrice();
		int stock = Item.getStock();
		query = "INSERT INTO Item (shoe_name, size, brand, price, stock) VALUES('" + name + "', "+size+", '" + brand +"', '" + price + "', " + stock +");";
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet read(int id) {
		//selects a single item based on its id
		String query = "SELECT* FROM Item WHERE item_id =" + id;
		try{
			
			res = DBInstance.exQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	public ResultSet readAll() {
		//selects all item information from customer table 
		String query = "SELECT* FROM Item";
		
		try{
			
			res = DBInstance.exQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	
	public void update(Item item) {
		String query;
		String name = item.getItem_name();
		int size = item.getSize();
		int price = item.getPrice();
		int stock = item.getStock();
		query = "UPDATE Item SET size = " + size + ", price = '" + price + "', stock = " + stock +" WHERE item_id = " +name;
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Item Details for:" + name + "have been Updated!");
	}
	
	
	public void deleteById(int id) {
		String query;
		query = "DELETE FROM Item WHERE item_id = " + id;
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteByName(String name) {
		String query;
		query = "DELETE FROM Item WHERE item_name = " + name;
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
