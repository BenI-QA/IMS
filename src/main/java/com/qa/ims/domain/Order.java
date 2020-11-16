package com.qa.ims.domain;
import java.util.Date;

public class Order {
	
	private int id = 0;
	private Date order_date;
	private int item_id;
	private int quantity;
	private int cust_id = 0;
	
	public Order(int iD, Date order_date, int item_id, int quantity, int cust_iD) {
		id = iD;
		this.order_date = order_date;
		this.item_id = item_id;
		this.quantity = quantity;
		this.cust_id = cust_iD;
	
	}
	public Order(Date order_date, int item_id, int quantity, int cust_iD ) {
		this.order_date = order_date;
		this.item_id  = item_id;
		this.quantity = quantity;
		this.cust_id = cust_iD;
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	
	public int getItem_id() {
		return item_id;
	}
	public void setItem_name(int item_id) {
		this.item_id = item_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

}
