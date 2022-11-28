package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

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

	/**
	 * Combine the pointcuts :
	 * include package ... exclude getter/setter
	 */
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
