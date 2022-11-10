package com.jpa.many2many.bi.lazy.dto;

import java.time.LocalDate;

public class CourseDto {

	private long id;
	private String courseNumber;
	private String courseName;
	private int learningYear;
	private LocalDate startDate;
	private LocalDate endDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getLearningYear() {
		return learningYear;
	}

	public void setLearningYear(int learningYear) {
		this.learningYear = learningYear;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
