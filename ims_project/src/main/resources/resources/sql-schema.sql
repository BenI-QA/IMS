drop schema ims;
CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims` ;
CREATE TABLE IF NOT EXISTS `ims`.`customer` (`customer_id` int NOT NULL AUTO_INCREMENT, `first_name` varchar(20), 
`last_name` varchar(20), `email` varchar(20), `phone_num` int(12), CONSTRAINT PK_Customer PRIMARY KEY (`customer_id`));

CREATE TABLE Item (item_id int NOT NULL AUTO_INCREMENT, item_name varchar(20), size int,
 price decimal(2), stock int, CONSTRAINT PK_ITEM PRIMARY KEY (item_id));

CREATE TABLE Order_ (order_id int NOT NULL AUTO_INCREMENT, order_date date, customer_id int NOT NULL,
CONSTRAINT PK_Order PRIMARY KEY (order_id), 
CONSTRAINT FK_customer_id FOREIGN KEY (customer_id) REFERENCES Customer(customer_id));

CREATE TABLE Item_Order (order_id int NOT NULL auto_increment, item_id int NOT NULL, quantity int,
CONSTRAINT PK_ItemOrder PRIMARY KEY (order_id, item_id),
CONSTRAINT FK_ItemOrder_Item FOREIGN KEY (item_id) REFERENCES Item(item_id),
CONSTRAINT FK_ItemOrder_order FOREIGN KEY (order_id) REFERENCES Order_(order_id));

