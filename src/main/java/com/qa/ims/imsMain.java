package com.qa.ims;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.controller.MenuCus;

import java.util.Scanner;

public class imsMain {
	
	public static final Logger LOGGER = LogManager.getLogger();
	Scanner scanner = new Scanner(System.in);
	
	public void start() {
		while(true) {
			LOGGER.info(" Select an option below:");
			LOGGER.info("  1) Customer \n  2) Order \n  3) Item \n  4) EXIT");
			int select = scanner.nextInt();
			MenuCus menuCus = new MenuCus();
			//MenuItem menuItem = new MenuItem();
			//MenuOrder menuOrder = new MenuOrder();
			scanner.nextLine();
			
			switch(select) {
			case 1:
				menuCus.menu(select);
				break;
			case 2:
				//menuOrder.menu(select);
				break;
			case 3:
				//menuItem.menu(select);
				break;
			case 4:
				System.exit(0);
			}
			
		}
	}
}
