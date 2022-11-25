package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

	/**
	 * @Before - Advice with Pointcut expressions
	 */
//	@Before(value = "execution(public void com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(* com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(public void addAccount())")
//	@Before(value = "execution(void addAccount())")
//	@Before(value = "execution(public void add*())")
//	@Before(value = "execution(public * add*())")
//	@Before(value = "execution(* add*())")
//	public void beforeAddAccountAdvice() {
//		System.out.println("Exceuting Aspect : beforeAddAccountAdvice");
//	}

	// ------------------------------------------------------------------
	// ------------------------------------------------------------------
	// ------------------------------------------------------------------

	/**
	 * @Before - Advice with Pointcut expressions and method parameters
	 */
//	@Before(value = "execution(public void com.aop.dao.AccountDao.addBook(com.aop.entity.BookEntity))")
//	@Before(value = "execution(public void com.aop.dao.*.*(*))")
//	@Before(value = "execution(public void com.aop.dao.*.*(..))")
//	@Before(value = "execution(public void addBook(com.aop.entity.BookEntity))")
//	@Before(value = "execution(* addBook(com.aop.entity.BookEntity))")	
//	@Before(value = "execution(* addBook(*))")
//	@Before(value = "execution(* addBook(..))")
//	public void beforeAddBookAdvice() {
//		System.out.println("Exceuting Aspect : beforeAddBookAdvice");
//	}

	// ------------------------------------------------------------------
	// ------------------------------------------------------------------
	// ------------------------------------------------------------------

	/**
	 * @Pointcut - Pointcut declarations , adding the pointcut expression in it
	 * @Before - add the Pointcut declaration , as an expression 'forDaoPackage' in
	 *         the Advice
	 */
//	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
//	private void forDaoPackage() {
//		
//	}
//	
//	@Before(value = "forDaoPackage()")
//	public void beforeAdd() {
//		System.out.println("Exceuting the @Before Advice - beforeAdd");
//	}
//	
//	@Before(value = "forDaoPackage()")
//	public void beforePerformApiAnalytics() {
//		System.out.println("Exceuting the @Before Advice - beforePerformApiAnalytics");
//	}

	// ------------------------------------------------------------------
	// ------------------------------------------------------------------
	// ------------------------------------------------------------------

	/**
	 * Example for Combining Pointcuts
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	private void forDaoPackage() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.get*(..))")
	private void getter() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.set*(..))")
	private void setter() {
	}

	// Combine the pointcuts : include package ... exclude getter/setter
	@Pointcut(value = "forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {
	}

	@Before(value = "forDaoPackageNoGetterSetter()")
	public void beforeAdd() {
		System.out.println("Exceuting the @Before Advice - beforeAdd");
	}

	@Before(value = "forDaoPackageNoGetterSetter()")
	public void beforePerformApiAnalytics() {
		System.out.println("Exceuting the @Before Advice - beforePerformApiAnalytics");
	}

}
