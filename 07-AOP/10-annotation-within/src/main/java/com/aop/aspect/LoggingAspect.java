package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
	@Pointcut("@annotation(com.aop.annotation.Log)")
	public void logPointcut() {
	}

	@Before("logPointcut()")
	public void logAllMethodCallsAdvice() {
		System.out.println("@Log annotation In Aspect");
	}

}
