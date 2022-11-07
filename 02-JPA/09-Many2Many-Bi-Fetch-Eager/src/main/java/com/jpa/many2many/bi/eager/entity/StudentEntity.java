package com.jpa.many2many.bi.eager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STUDENT_TB")
public class StudentEntity {

	@Id
//	@SequenceGenerator(name = "studentseq", initialValue = 20001, allocationSize = 50)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentseq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private long id;
	private String firstName;
	private String lastName;
	private long identityNumber;
	private String email;

	@ManyToMany(mappedBy = "students", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH })
	@JsonIgnore
	private Set<CourseEntity> courses;

	public StudentEntity() {
		super();
	}

	public StudentEntity(String firstName, String lastName, long identityNumber, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityNumber = identityNumber;
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

	public long getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(long identityNumber) {
		this.identityNumber = identityNumber;
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

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", identityNumber="
				+ identityNumber + ", email=" + email + "]";
	}

}
