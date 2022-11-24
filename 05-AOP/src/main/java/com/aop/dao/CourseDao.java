package com.aop.dao;

import org.springframework.stereotype.Service;

@Service
public class CourseDao {

	public String getCourseName() {
		System.out.println("In service");
		return "JAVA course";
	}
}
