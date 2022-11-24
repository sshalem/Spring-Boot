package com.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aop.dao.AccountDao;
import com.aop.dao.MembershipDao;

@SpringBootTest
class ApplicationTests {

	@Autowired
	AccountDao accountDao;

	@Autowired
	MembershipDao membershipDao;

	@Test
	void contextLoads() {
	}

	@Test
	void addAccount() {
		accountDao.addAccount();
		membershipDao.addAccount();
	}
}
