package com.aop.dao;

import org.springframework.stereotype.Service;

import com.aop.entity.BookEntity;

@Service
public class AccountDao {

	private String name;
	private String serviceCode;

	public void addAccount() {
		System.out.println(getClass() + " add account");
	}

	public void addBook(BookEntity bookEntity) {
		System.out.println(getClass() + " add Book");
	}

	public String getName() {
		System.out.println(getClass() + " getName()");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass() + " setName()");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass() + " getServiceCode()");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass() + " setServiceCode()");
		this.serviceCode = serviceCode;
	}
}
