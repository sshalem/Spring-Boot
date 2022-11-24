package com.aop.dao;

import org.springframework.stereotype.Service;

import com.aop.entity.BookEntity;

@Service
public class AccountDao {

	public void addAccount() {		
		System.out.println(getClass());
	}
	
	public void addBook(BookEntity bookEntity) {
		System.out.println("added");
	}
}
