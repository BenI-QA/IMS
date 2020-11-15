package com.qa.ims.domain;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
	private int ID = 0 ;
	private String first_name;
	private String last_name;
	private String email;
	private int phone;
	
	
	
	
	
	public Customer(int iD, String f_name, String l_name, String email, int phone) {
		ID = iD;
		this.first_name = f_name;
		this.last_name= l_name;
		this.email = email;
		this.phone = phone;
	}
	
	public Customer(String f_name, String l_name, String email, int phone) {
		this.first_name = f_name;
		this.last_name= l_name;
		this.email = email;
		this.phone = phone;
	}
	
	public static Customer convert(ResultSet result) throws SQLException {
		if(result.next()) {
			int localID = result.getInt("customer_id");
			String localFname = result.getString("first_name");
			String localLname = result.getString("last_name");
			String localEmail = result.getString("email");
			int localPhone = result.getInt("phone_num");
			return new Customer(localID, localFname, localLname, localEmail, localPhone);
			}
		else {
			return null;
		}
	
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}

}

