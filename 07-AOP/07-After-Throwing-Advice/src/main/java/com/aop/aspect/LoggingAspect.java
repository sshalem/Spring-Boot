package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
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

	@AfterThrowing(pointcut = "forDaoPackage()", throwing = "ex")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable ex) {

		System.out.println(" \nafterThrowingFindAccountsAdvice ");

		String method = joinPoint.getSignature().toShortString();
		System.out.println("execution method @After Throwing : " + method);
		System.out.println("the exception is " + ex);

	}
}
