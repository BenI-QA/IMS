package com.qa.ims.controller;

public interface CrudController<T> {

		T create();
		
		T read();
		
		T update();

		T delete();

}
