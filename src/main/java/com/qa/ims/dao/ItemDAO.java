package com.qa.ims.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.domain.Item;
import com.qa.ims.util.DBUtils;


public class ItemDAO {
	DBUtils DBInstance;
	private static Logger LOGGER = LogManager.getLogger();
	ResultSet res  = null;

	//inputting login details to database
	public ItemDAO(){
	
	}
	
	public Item create(Item Item) {
		String query;
		String name = Item.getItem_name();
		double size = Item.getSize();
		double price = Item.getPrice();
		long stock = Item.getStock();
		query = "INSERT INTO Item (item_name, size, price, stock) VALUES('" + name + "', "+size+", " +price+ "," + stock +");";
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(query);
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	public Item readLatest() {
		String query;
		query = "SELECT * FROM Item ORDER BY item_id DESC LIMIT 1";
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
	

	
	public List<Item> readAll() {
		//selects all customer information from customer table 
		String query = "SELECT* FROM Item";
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);) {
			List<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(convert(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
		
	}
	
	
	
	public Item update(Item item) {
		String query;
		String name = item.getItem_name();
		double size = item.getSize();
		double price = item.getPrice();
		long stock = item.getStock();
		query = "UPDATE Item SET size = " + size + ", price = " + price + ", stock = " + stock +
				" WHERE item_name = '" +name+"' AND size="+size+";" ;
		
		try(Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
				statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOGGER.info("Item Details for:" + name + " "  + size+ "have been Updated!");
		return readLatest();
	}
	
	
	public void deleteById(long id) {
		String query;
		query = "DELETE FROM Item WHERE item_id = " + id;
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
				statement.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteByName(String name, double size) {
		String query;
		query = "DELETE FROM Item WHERE item_name = '" + name + "'AND size = "+ size+";" ;
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
				statement.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public static Item convert(ResultSet result) throws SQLException {
		long id = result.getInt("item_id");
		String name = result.getString("item_name");
		double size = result.getInt("size");
		double price = result.getInt("price");
		long stock = result.getInt("stock");
		
		return new Item(id, name, size, price, stock);

}
}
