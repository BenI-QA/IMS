package com.qa.ims.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Date;

import com.qa.ims.connection.db_Connection;
import com.qa.ims.domain.Order;

public class OrderDAO {
	db_Connection DBInstance;
	ResultSet res  = null;
	Scanner scanner = new Scanner(System.in);
	//inputting login details to database
	public OrderDAO(){
		String username = "root"; //scanner.nextLine();
		String password = "Passwordunknown123!"; //scanner.nextLine();
		DBInstance = db_Connection.connect(username , password); //username, password
		
	}
	
	public void create(Order order) {
		String m_query, sec_query, stock_query;
		Date order_date = order.getOrder_date();
		int item_id = order.getItem_id();
		int quantity = order.getQuantity();
		int cust_id = order.getCust_id();
	
		sec_query = "INSERT INTO Order_(order_date, customer_id) VALUES('"+order_date+"',"+cust_id+");";
		m_query = "INSERT INTO item_order(item_id, quantity) VALUES("+item_id+"," +quantity+");";
		stock_query = "UPDATE item set stock = stock -"+ quantity+";";
		
		try {
			DBInstance.exUpdate(sec_query);
			DBInstance.exUpdate(m_query);
			//DBInstance.exUpdate(stock_query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//sec_query = "UPDATE Item SET Stock ="+ stock - quantity WHERE item_id ="+item_id+"`	
				//add item to order
		
	}
	
	
	//use by customer id
	public ResultSet read(int id) {
		//selects a single customers order
		String query = "SELECT item_order.order_id, order_.customer_id,order_.order_date,"
				+ "item.shoe_name, item.price, quantity"
				+ "FROM item_order"
				+ "INNER JOIN Item ON item_order.item_id=Item.item_id" 
				+ "INNER JOIN Order_ ON item_order.order_id = Order_.order_id;"
				+ "GROUP BY order_id";
		try{
			
			res = DBInstance.exQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	public ResultSet readAll() {
		//selects all orders of all customers
		String query = "SELECT item_order.order_id, order_.customer_id, order_.order_date, (Item.price*item_order.quantity) AS Total_Price"
				+ " FROM item_order"
				+ " INNER JOIN Item ON item_order.item_id=Item.item_id" 
				+ " INNER JOIN Order_ ON item_order.order_id = Order_.order_id;";
		
		try{
			
			res = DBInstance.exQuery(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
		
	}
	
	
	public void update(int o_id, int i_id, int quant) {
		String query;
		//Adding item to order table
		query = "INSERT item_order(order_id, item_id, quantity) VALUES(" + o_id+"," + i_id + "," +quant+");";
		
		//deleting item from order_table
		//dquery = "DELETE FROM item_order WHERE order_id ="+o_id+" AND item_id="+ i_id+";";
		try {
			DBInstance.exUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//adding a item to a order
		//change this to customer name when finding customer order
	//	System.out.println("Order Details for:" + cust_id + "have been Updated!");
	}
	//remove item from order
	
	
	public void deleteById(int id) {
		String query, sec_query;
		//delete complete order
		query = "DELETE FROM Order_ WHERE order_id = " + id;
		sec_query = "DELETE FROM item_order WHERE order_id = " + id;

		try {
			DBInstance.exUpdate(query);
			DBInstance.exUpdate(sec_query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteItem(int order_id, int item_id) {
		String remov_item;
		remov_item = "DELETE FROM item_order WHERE order_id = " + order_id+ "AND item_id=" + item_id +";";
		
		try {
			DBInstance.exUpdate(remov_item);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	
	
}

