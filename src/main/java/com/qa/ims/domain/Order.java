package com.qa.ims.domain;
import java.util.Date;

public class Order {
	
	private Long id;
	private Date order_date;
	private Long item_id;
	private Long quantity;
	private double price; 
	private String item_name;
	private double totalprice;
	private Long cust_id;
	
	public Order(Long iD, Date order_date, Long item_id, Long quantity, Long cust_iD) {
		id = iD;
		this.order_date = order_date;
		this.item_id = item_id;
		this.quantity = quantity;
		this.cust_id = cust_iD;
	
	}
	public Order(Date order_date, Long item_id, Long quantity, Long cust_iD ) {
		this.order_date = order_date;
		this.item_id  = item_id;
		this.quantity = quantity;
		this.cust_id = cust_iD;
		
	}
	
	public Order(Long iD, Date order_date, double totalPrice, Long cust_iD ) {
		this.order_date = order_date;
		this.id = iD;
		this.totalprice = totalPrice;
		this.cust_id = cust_iD;
		
	}
	public Order(Long iD, Long cust_iD, String item_name, double price, Long quantity) {
		this.id = iD;
		this.cust_id = cust_iD;
		this.item_name = item_name;
		this.price = price; 
		this.quantity = quantity;
		
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	
	public Long getItem_id() {
		return item_id;
	}
	public void setItem_id(Long item_id) {
		this.item_id = item_id;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	
	public String toStringSingle() {
		return "id:  " + id + " Customer id:  " + cust_id + " item name:  " + item_name + "  Price:  $" +price + " quantity:  " + quantity ;
	}
	
	public String toStringTotal() {
		return "id:  " + id + " Customer id:  " + cust_id + " order date:  " + order_date + " TotalPrice:  $" + totalprice ;
	}

}
