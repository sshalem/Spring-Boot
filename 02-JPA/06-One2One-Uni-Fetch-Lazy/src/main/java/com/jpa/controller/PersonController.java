package com.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.PersonDaoImpl;

@RestController
@RequestMapping("/person")
@CrossOrigin("*")
public class PersonController {

	@Autowired
	private PersonDaoImpl userDaoImpl;

	// *********************
	// ***** Get Methods ***
	// *********************

	// **************************************
	// ***** Post Methods ***
	// **************************************

	// **************************************
	// ***** Put Methods ***
	// **************************************

	// **************************************
	// ***** Delete Methods ***
	// **************************************

}
