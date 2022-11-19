package com.jpa.many2many.bi.lazy.dto;

public class DtoCourseNameAndCourseNumber {

	private String courseNumber;
	private String courseName;

	public DtoCourseNameAndCourseNumber() {
		super();
	}

	public DtoCourseNameAndCourseNumber(String courseNumber, String courseName) {
		super();
		this.courseNumber = courseNumber;
		this.courseName = courseName;
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

//	public CourseDto(long id, String courseNumber, String courseName, int learningYear, LocalDate startDate,
//			LocalDate endDate) {
//		super();
//		this.id = id;
//		this.courseNumber = courseNumber;
//		this.courseName = courseName;
//		this.learningYear = learningYear;
//		this.startDate = startDate;
//		this.endDate = endDate;
//	}

}
