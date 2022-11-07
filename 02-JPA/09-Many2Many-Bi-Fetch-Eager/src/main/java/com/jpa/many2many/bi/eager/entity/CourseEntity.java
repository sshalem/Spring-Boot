package com.jpa.many2many.bi.eager.entity;

import java.time.LocalDate;
import java.util.Set;

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
	private String courseName;
	private int learningYear;
	private LocalDate startDate;
	private LocalDate endDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "course_student", 
			joinColumns = { @JoinColumn(name = "fk_course_id") }, 
			inverseJoinColumns = { @JoinColumn(name = "fk_student_id") })
	@JsonIgnore
	private Set<StudentEntity> students;

	public CourseEntity() {
		super();
	}

	public CourseEntity(String courseNumber, String courseName, int learningYear) {
		super();
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.learningYear = learningYear;
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

	public Set<StudentEntity> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentEntity> students) {
		this.students = students;
	}

}
