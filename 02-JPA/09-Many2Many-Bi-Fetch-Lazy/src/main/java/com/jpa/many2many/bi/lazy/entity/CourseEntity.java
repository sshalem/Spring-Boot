package com.jpa.many2many.bi.lazy.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(name = "course_id")
	private long id;
	private String courseNumber;
	private int year;

	@ManyToMany(mappedBy = "students",
			fetch = FetchType.LAZY, 
			cascade = { CascadeType.PERSIST, 
						CascadeType.DETACH,
						CascadeType.MERGE, 
						CascadeType.REFRESH })
	@JoinTable(name = "course_student", 
				joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "id")}, 
				inverseJoinColumns = {@JoinColumn(name = "student_id", referencedColumnName = "id")})
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
