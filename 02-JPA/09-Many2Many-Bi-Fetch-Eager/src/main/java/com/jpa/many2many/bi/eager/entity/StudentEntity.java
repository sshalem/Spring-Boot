package com.jpa.many2many.bi.eager.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	private int identityNumber;
	private String email;

	@ManyToMany(mappedBy = "students",
			fetch = FetchType.EAGER, 
			cascade = { 
//						CascadeType.PERSIST,
//						CascadeType.MERGE,	
//						CascadeType.DETACH,
//						CascadeType.REFRESH
						}
				)	
	private Set<CourseEntity> courses;

	public StudentEntity() {
		super();
	}

	public StudentEntity(String firstName, String lastName, int identityNumber, String email) {
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

	public int getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(int identityNumber) {
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
		Set<StudentEntity> students = courseEntity.getStudents();
		students.add(this);
	}

	public void removeCourse(CourseEntity courseEntity) {		
		courseEntity.getStudents().remove(this);
		this.courses.remove(courseEntity);
	}

	public void clearCourse(CourseEntity courseEntity) {
		courseEntity.getStudents().remove(this);		
		/**
		 * I cannot use this line since it will create a "java.util.ConcurrentModificationException exception"
		 * this.courses.remove(courseEntity);
		 */
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + identityNumber;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentEntity other = (StudentEntity) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (identityNumber != other.identityNumber)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", identityNumber="
				+ identityNumber + ", email=" + email + "]";
	}

}
