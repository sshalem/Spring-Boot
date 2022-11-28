package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	/**
	 * @Pointcut - Pointcut declarations , adding the pointcut expression in it
	 * @Before - add the Pointcut declaration , as an expression 'forDaoPackage' in
	 *         the Advice
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	private void forDaoPackage() {

	}

	@After(value = "execution(* com.aop.dao.*.*(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
		System.out.println(" \n afterFinallyFindAccountsAdvice");

		String method = joinPoint.getSignature().toShortString();
		System.out.println("execution method @After (finally) advice : " + method);
	}
	
	@AfterThrowing(pointcut = "forDaoPackage()", throwing = "ex")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable ex) {

		System.out.println(" \nafterThrowingFindAccountsAdvice ");

		String method = joinPoint.getSignature().toShortString();
		System.out.println("execution method @AfterThrowing Advice: " + method);
		System.out.println("the exception is " + ex);

	}
}
