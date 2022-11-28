package com.aop.dao;

import org.springframework.stereotype.Service;

@Service
public class MembershipDao {

	public void addAccount() {
		System.out.println(getClass());
	}
	
}
