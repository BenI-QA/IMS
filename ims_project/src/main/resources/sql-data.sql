INSERT INTO Customer (`first_name`, `last_name`, `email`, `phone_Num`) VALUES ("Jack", "Barns", "jbarns@qa.com", "0286647736");
INSERT INTO Customer (`first_name`, `last_name`, `email`, `phone_Num`) VALUES ("John", "Dickson", "bdickson@qa.com", "0286643436");

INSERT INTO Item (item_name, size, price, stock) VALUES ("addias", "10", 15.00, 5);
INSERT INTO Item (item_name, size, price, stock) VALUES ("addias", "11", 15.00, 5);
INSERT INTO Item (item_name, size, price, stock) VALUES ("nike", 10, 30.00, 10);
INSERT INTO Item (item_name, size, price, stock) VALUES ("nike", "11", 30.00, 10);
INSERT INTO Item (item_name, size, price, stock) VALUES ("sketchers", 10, 45.00, 20);
INSERT INTO Item (item_name, size, price, stock) VALUES ("sketchers", 11, 45.00, 20);

INSERT INTO Order_ (order_date ,  customer_id) VALUE('2020-05-04', 1) ;
INSERT INTO Order_(order_date ,customer_id) VALUE('2020-04-28', 2);

INSERT INTO Item_Order(order_id, item_id, quantity) VALUES (1,2,4); 
INSERT INTO Item_Order(order_id, item_id, quantity) VALUES (1,3,5);

INSERT INTO Item_Order(order_id, item_id, quantity) VALUES (2,1,4);
INSERT INTO Item_Order(order_id, item_id, quantity) VALUES (2,3,4);
INSERT INTO Item_Order(order_id, item_id, quantity) VALUES (2,2,4);