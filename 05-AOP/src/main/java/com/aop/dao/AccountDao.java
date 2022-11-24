package com.aop.dao;

import org.springframework.stereotype.Service;

@Service
public class AccountDao {

	public void addAccount() {
		System.out.println(getClass());
	}
}
