package com.bezkoder.spring.hibernate.manytomany.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tags")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tag_id")
	private long id;

	@Column(name = "name")
	private String name;

	@ManyToMany(fetch = FetchType.EAGER
//				cascade = { 
//							CascadeType.PERSIST, 
//							CascadeType.MERGE,
//							CascadeType.DETACH,
//							CascadeType.REFRESH
//							}
			)
	@JoinTable(name = "tags_tutorial", 
			joinColumns = { @JoinColumn(name = "tag_id" , referencedColumnName = "tag_id") } , 
			inverseJoinColumns = { @JoinColumn(name = "tutorial_id" , referencedColumnName = "tutorial_id") })
	@JsonIgnore
	private Set<Tutorial> tutorials = new HashSet<>();

	public Tag() {

	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Tutorial> getTutorials() {
		return tutorials;
	}

	public void setTutorials(Set<Tutorial> tutorials) {
		this.tutorials = tutorials;
	}

}
