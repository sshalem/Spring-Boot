package com.aop.entity;

public class BookEntity {

	private String name;
	private String author;

	public BookEntity() {
		super();
	}

	public BookEntity(String name, String author) {
		super();
		this.name = name;
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "BookEntity [name=" + name + ", author=" + author + "]";
	}

}
