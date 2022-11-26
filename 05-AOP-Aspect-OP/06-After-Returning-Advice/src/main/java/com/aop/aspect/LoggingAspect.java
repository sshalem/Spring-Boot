package com.aop.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.aop.entity.AccountEntity;

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

	@Before(value = "forDaoPackage()")
	public void beforeAddBook(JoinPoint joinPoint) {
		System.out.println("Exceuting the @Before Advice - beforeAddAccount");
	}

	@AfterReturning(pointcut = "forDaoPackage()", returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<AccountEntity> result) {

		System.out.println(" \nafterReturningFindAccountsAdvice ");

		String method = joinPoint.getSignature().toShortString();
		System.out.println(method);

		/**
		 * Post Processing data
		 * We cannot Add or remove form the List , but we can modify the data in it
		 */

		result.forEach(res -> res.setLevel("UnKnown"));

	}

}
