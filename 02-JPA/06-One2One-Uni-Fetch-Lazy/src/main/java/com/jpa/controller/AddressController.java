package com.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.dao.AddressDaoImpl;

@RestController
@RequestMapping("/address")
@CrossOrigin("*")
public class AddressController {

	@Autowired
	private AddressDaoImpl addressDaoImpl;

	// *********************
	// ***** Get Methods ***
	// *********************
}
