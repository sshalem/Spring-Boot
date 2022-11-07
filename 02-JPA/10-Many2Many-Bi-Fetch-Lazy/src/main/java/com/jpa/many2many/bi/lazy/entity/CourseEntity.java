package com.jpa.many2many.bi.lazy.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "COURSE_TB")
public class CourseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	private String courseNumber;
	private int year;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "course_student", 
				joinColumns = {@JoinColumn(name = "fk_course_id")}, 
				inverseJoinColumns = {@JoinColumn(name = "fk_student_id")})
	@JsonIgnore
	private Set<StudentEntity> students;

	public CourseEntity() {
		super();
	}

	public CourseEntity(String courseNumber, int year) {
		super();
		this.courseNumber = courseNumber;
		this.year = year;
	}

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

}
