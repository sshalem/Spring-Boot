package com.jpa.many2many.bi.lazy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STUDENT_TB")
public class StudentEntity {

	@Id
	@SequenceGenerator(name = "studentseq", initialValue = 20001, allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentseq")
	@Column(name = "student_id")
	private long id;
	private String firstName;
	private String lastName;
	private String studentIdentity;
	private String encryptedPassword;
	private String email;

	@ManyToMany
	@JsonIgnore
	private Set<CourseEntity> courses;

	public StudentEntity() {
		super();
	}

	public StudentEntity(String firstName, String lastName, String studentIdentity, String encryptedPassword,
			String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentIdentity = studentIdentity;
		this.encryptedPassword = encryptedPassword;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStudentIdentity() {
		return studentIdentity;
	}

	public void setStudentIdentity(String studentIdentity) {
		this.studentIdentity = studentIdentity;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<CourseEntity> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseEntity> courses) {
		this.courses = courses;
	}

	/**
	 * Helper Methods for Adding/Removing Course
	 */

	public void addCourse(CourseEntity courseEntity) {
		if (this.courses == null) {
			this.courses = new HashSet<>();
		}
		this.courses.add(courseEntity);
		courseEntity.getStudents().add(this);
	}

	public void removeCourse(CourseEntity courseEntity) {
		this.courses.remove(courseEntity);
		courseEntity.getStudents().remove(this);
	}
}
