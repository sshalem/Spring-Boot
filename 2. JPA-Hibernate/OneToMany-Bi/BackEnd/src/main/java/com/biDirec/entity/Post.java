package com.biDirec.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "post_entity")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDateTime postDate;
	private String details;

	/*
	 * @Transient private String temp;
	 * 
	 * @Transient - We use this annotaion of a field if we DO NOT wnat it to be part
	 * of table , so ORM ignores it
	 */

	@ManyToOne
	@JoinColumn(name = "user_id")
//	@JsonBackReference
	@JsonIgnore
	private User user;

	public Post() {
		super();
	}

	public Post(LocalDateTime postDate, String details) {
		super();
		this.postDate = postDate;
		this.details = details;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", postDate=" + postDate + ", user=" + user + ", details=" + details + "]";
	}
}
