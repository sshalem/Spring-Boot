package com.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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

	@Around(value = "forDaoPackage()")
	public Object aroundAddAccount(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		String method = proceedingJoinPoint.getSignature().toShortString();

		System.out.println("executing Around Method");
		long begin = System.currentTimeMillis();
		
		// Execute the method we want from (com.aop.dao.*.*(..))
		Object proceed = proceedingJoinPoint.proceed();
		
		long end = System.currentTimeMillis();
		long duration = (end - begin) / 1000; // duration in seconds
		System.out.println("Duration " + duration + "seconds");

		return proceed;

	}

}
