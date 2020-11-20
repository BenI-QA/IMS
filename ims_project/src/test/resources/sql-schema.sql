drop schema IMS;
CREATE SCHEMA IF NOT EXISTS IMS;
use IMS;
CREATE TABLE Customer 
	(customer_id int NOT NULL AUTO_INCREMENT, 
	first_name varchar(20), 
	last_name varchar(20), 
	email varchar(20), 
	phone_num int, 
	CONSTRAINT PK_Customer PRIMARY KEY (customer_id));

CREATE TABLE IF NOT EXISTS Item 
	(item_id int NOT NULL AUTO_INCREMENT, item_name varchar(20),
	 size int, price int, stock int, 
	 CONSTRAINT PK_ITEM PRIMARY KEY (item_id));

CREATE TABLE IF NOT EXISTS Order_ 
	(order_id int NOT NULL AUTO_INCREMENT, order_date date, customer_id int NOT NULL,
	CONSTRAINT PK_Order PRIMARY KEY (order_id), 
	CONSTRAINT FK_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id));

CREATE TABLE IF NOT EXISTS Item_Order (order_id int NOT NULL , 
	item_id int NOT NULL, quantity int,
	CONSTRAINT PK_ItemOrder PRIMARY KEY (order_id, item_id),
	CONSTRAINT FK_ItemOrder_Item FOREIGN KEY (item_id) REFERENCES Item(item_id),
	CONSTRAINT FK_ItemOrder_order FOREIGN KEY (order_id) REFERENCES Order_(order_id));
