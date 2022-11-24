package com.aop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aop.dao.CourseDao;

@RestController
@RequestMapping("/aop")
public class CourseController {

	@Autowired
	private CourseDao courseDao;
	
	@GetMapping
	public String getCourseName() {
		return courseDao.getCourseName(); 
	}
}
