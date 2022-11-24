package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@Before(value = "execution(public void com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(* com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(public void addAccount())")
//	@Before(value = "execution(void addAccount())")
//	@Before(value = "execution(public void add*())")
//	@Before(value = "execution(public * add*())")
//	@Before(value = "execution(* add*())")
	public void beforeAddAccountAdvice() {
		System.out.println("Exceuting Aspect : beforeAddAccountAdvice");
	}

	@Before(value = "execution(public void com.aop.dao.AccountDao.addBook(com.aop.entity.BookEntity))")
//	@Before(value = "execution(public void com.aop.dao.*.*(*))")
//	@Before(value = "execution(public void com.aop.dao.*.*(..))")
//	@Before(value = "execution(public void addBook(com.aop.entity.BookEntity))")
//	@Before(value = "execution(* addBook(com.aop.entity.BookEntity))")	
//	@Before(value = "execution(* addBook(*))")
//	@Before(value = "execution(* addBook(..))")
	public void beforeAddBookAdvice() {
		System.out.println("Exceuting Aspect : beforeAddBookAdvice");
	}
}
