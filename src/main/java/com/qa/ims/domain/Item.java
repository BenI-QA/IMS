package com.qa.ims.domain;



public class Item {
	
	private Long id;
	private String item_name;
	private double size;
	private double price;
	private Long stock;
	
	
	public Item(Long iD, String item_name, double size, double price, Long stock) {
		id = iD;
		this.item_name = item_name;
		this.size = size;
		this.price = price;
		this.stock = stock;
	}
	public Item(String item_name, double size, double price, Long stock) {
		this.item_name = item_name;
		this.size = size;
		this.price = price;
		this.stock = stock;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getItem_name() {
		return item_name;
	}


	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}


	public double getSize() {
		return size;
	}
	public void setSize(double size) {
		this.size = size;
	}


	public double getPrice() {
	
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}


	public Long getStock() {
		return stock;
	}


	public void setStock(Long stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "id:" + id + " Shoe Name:" + item_name + " Size:" + size + " Price: $"+price+ " Stock:"+stock +" \n" ;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		if (item_name == null) {
			if (other.item_name != null)
				return false;
		} else if (!item_name.equals(other.item_name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (size != other.size)
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	

}
