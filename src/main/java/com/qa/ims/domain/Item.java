package com.qa.ims.domain;

public class Item {
	
	private int id = 0;
	private String item_name;
	

	private int size;
	private String brand;
	private int price;
	private int stock;
	
	
	public Item(int iD, String item_name, int size, String brand, int price, int stock) {
		id = iD;
		this.item_name = item_name;
		this.size = size;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
	}
	public Item(String item_name, int size, String brand, int price, int stock) {
		this.item_name = item_name;
		this.size = size;
		this.brand = brand;
		this.price = price;
		this.stock = stock;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}



	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


}
