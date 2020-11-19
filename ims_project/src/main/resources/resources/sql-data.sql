INSERT INTO `ims`.`customer` (`first_name`, `last_name`, `email`, `phone_Num`) VALUES (`jack`, `Barns`, `jbarns@qa.com`, 0286647736);
INSERT INTO `ims`.`customer` (`first_name`, `last_name`, `email`, `phone_Num`) VALUES (`Ben`, `Dickson`, `bdickson@qa.com`, 0286643436);

INSERT INTO Item (item_name, size,  price, stock) VALUES ("addias", "10", 15.00, 5);
INSERT INTO Item (item_name, size,  price, stock) VALUES ("call of chips", "11", 30.00, 10);

INSERT INTO Order_ (order_date ,  customer_id) VALUE('2020-05-04', 3) ;
INSERT INTO Order_(order_date ,customer_id) VALUE('2020-04-28', 2);

INSERT INTO item_order(order_id, item_id, quantity) VALUES (1,2,1); 
INSERT INTO item_order(order_id, item_id, quantity) VALUES (2,1,4); 