package com.qa.ims.connection;


import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class db_Connection {
	//private static final Logger LOGGER = LogManager.getLogger();
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/IMS";
	private String DB_USER; //cannot see as a constructor if it's a 'final'
	private String DB_PASS;
	

	private static db_Connection instance = null;
	
	
	
	public static db_Connection connect(String username, String password) {
		if(instance == null) {
			instance = new db_Connection(username, password);
		}
		return instance;
	}
	
	private db_Connection(String username, String password) {
		this.DB_USER = username;
		this.DB_PASS = password;
		try{
			getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		//init();
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

	
	public void exUpdate(String query) throws SQLException {
		Connection con = getConnection();
		Statement state = con.createStatement();

		state.executeUpdate(query);
	}
	
	public ResultSet exQuery(String query) throws SQLException {
		ResultSet result = null;
		Connection con = getConnection();
		Statement state = con.createStatement();
		con = getConnection();
		result = state.executeQuery(query);
		return result;
	
	}
	
	
	//if instance cannot connect to database it will make use of data from sql-schema.sql
	/**public int init() {
		return this.init("src/main/resources/sql-schema.sql", "src/main/resources/sql-data.sql");
		
	}**/

}
