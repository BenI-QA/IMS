package com.qa.ims.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qa.ims.domain.Customer;
import com.qa.ims.domain.Item;
import com.qa.ims.domain.Order;
import com.qa.ims.util.Utils;
import com.qa.ims.util.DBUtils;

public class OrderDAO {
	
	private static Logger LOGGER = LogManager.getLogger();
	DBUtils DBInstance;
	ResultSet res  = null;
	Utils util = new Utils();
	
	//inputting login details to database
	
	public Order create(Order order) {
		String m_query, sec_query;// stock_query;
		Date order_date = order.getOrder_date();
		long item_id = order.getItem_id();
		long quantity = order.getQuantity();
		long cust_id = order.getCust_id();
		
		m_query = "INSERT INTO Order_(order_date, customer_id) VALUES('"+order_date+"',"+cust_id+");";
		sec_query = "INSERT INTO Item_Order(item_id, quantity) VALUES("+item_id+"," +quantity+");";
		
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate(m_query);
			statement.executeUpdate(sec_query);
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
		
	}
	

	public Order readLatest() {
		String query;
		query = "SELECT * FROM order ORDER BY order_ DESC LIMIT 1;";
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
	
	
	//selects all orders of all customers
	public List<Order> readAll() {
	
		String query = "SELECT Order_.order_id, Order_.customer_id, Order_.order_date, SUM(Item.price*Item_Order.quantity) AS Total_Price"
				+ " FROM Item_Order"
				+ " INNER JOIN Item ON Item_Order.item_id=Item.item_id" 
				+ " INNER JOIN Order_ ON Item_Order.order_id = Order_.order_id"
				+ " GROUP BY order_id";
		
			try(Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
				) {
				List<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
				
					orders.add(convert(resultSet));
					
				
				}
				return orders;
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();
			
	}
	
	//use by customer id
		public List<Order> read(long id) {
			//selects a single customers order
			String query = "SELECT Item_Order.order_id, Order_.customer_id,"
					+ " Item.item_name, Item.price, quantity"
					+ " FROM Item_Order"
					+ " INNER JOIN Item ON Item_Order.item_id=Item.item_id" 
					+ " INNER JOIN Order_ ON Item_Order.order_id = Order_.order_id"
					+ " WHERE customer_id = "+ id ;
			
			try(Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query);) {
				List<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(convertid(resultSet));
				}
				return orders;
			} catch (SQLException e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
			}
			return new ArrayList<>();
			
		}

	
	
	public void updateEdit(String select, long o_id, long i_id, long quant) {
		String edit;
		//editing specific order
		
		edit = "UPDATE Item_Order SET quantity = "+quant + " WHERE order_id ="+o_id+" AND item_id = "+ i_id+";";
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
			
				statement.executeUpdate(edit);
				
		}
		catch (SQLException e) {
					e.printStackTrace();
				}
		
		LOGGER.info("Your order has been updated \n");
	}
	
	public List<Order> updateAdd(long o_id, long i_id, long quant) {
		String  addto, readback;
		
		//Adding item to current order
		addto = "INSERT Item_Order(order_id, item_id, quantity) VALUES(" + o_id+"," + i_id + "," +quant+");";
		readback = "SELECT Item_Order.order_id, Item_Order.item_id, Item.item_name"
				+ " FROM Item_Order"
				+ " INNER JOIN Item ON Item_Order.item_id=Item.item_id" 
				+ " WHERE Item_Order.order_id ="+o_id+" AND Item.item_id = "+i_id;
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(readback);){
				//adding a item to a order
				statement.executeUpdate(addto);
			
				List<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(convertid(resultSet));
				}
				return orders;
					
				}
		catch (SQLException e) {
					e.printStackTrace();
				}
		LOGGER.info("Your change has been added to your order \n");
		return new ArrayList<>();
		
		
	}
	
	
	public void deleteById(long id) {
		String query, sec_query;
		//delete complete order
		query = "DELETE FROM Order_ WHERE order_id = " + id +";";
		sec_query = "DELETE FROM Item_Order WHERE order_id = " + id + ";";

		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
				statement.executeUpdate(sec_query);	
				statement.executeUpdate(query);

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public void deleteItem(long order_id, long item_id) {
		String query;
		query = "DELETE FROM Item_Order WHERE order_id = "+ order_id+" AND item_id= " +item_id+ ";";
		
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();){
				statement.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public static Order convert(ResultSet result ) throws SQLException {
		Date utilDate = new java.util.Date();
	    Date order_date = new java.sql.Date(utilDate.getTime());
		
		Long id = result.getLong("order_id");
		Long cust_id =result.getLong("customer_id");
	
		double totalPrice = result.getDouble("Total_Price");
	
		return new Order(id, order_date, totalPrice, cust_id );
	}
	
	public static Order convertid(ResultSet result) throws SQLException {

		long id = result.getInt("order_id");
		long cust_id = result.getInt("customer_id");
		String item_name = result.getString("item_name");
		double price = result.getInt("price");
		long quantity = result.getInt("quantity");
		return new Order(id, cust_id, item_name, price, quantity);
	}	
}

