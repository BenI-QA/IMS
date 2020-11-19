package com.qa.ims.controller;

import java.util.List;


public interface CrudController<T> {
		List<T> readAll();
		
		T create();
		
		T update();

		T delete();

}
