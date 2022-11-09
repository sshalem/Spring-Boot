package com.jpa.many2many.bi.eager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.many2many.bi.eager.dao.CourseDaoImpl;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {

	@Autowired
	private CourseDaoImpl courseDaoImpl;
	
	// *******************************
	// POST (Create) methods
	// ********************************
	
	// *******************************
	// GET methods
	// ********************************
	
	
}
