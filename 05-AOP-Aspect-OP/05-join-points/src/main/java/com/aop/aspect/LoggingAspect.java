package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.aop.entity.BookEntity;

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
		System.out.println("Exceuting the @Before Advice - beforeAddBook");

		/**
		 * Access and Display the method signature
		 */
		System.out.println(" \n Access and Display the method signature ");
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		System.out.println("Method modifiers: " + methodSignature.getModifiers());
		System.out.println("Method name: " + methodSignature.getName());
		System.out.println("Method declaringType: " + methodSignature.getDeclaringType());
		System.out.println("Method method: " + methodSignature.getMethod());
		System.out.println("Method returnType: " + methodSignature.getReturnType());

		/**
		 * Access and Display the method Arguments
		 */
		
		System.out.println(" \n Access and Display the method Arguments ");
		Object[] args = joinPoint.getArgs();
		for (Object obj : args) {
			System.out.println(obj);

			if (obj instanceof BookEntity) {
				BookEntity bookEntity = (BookEntity) obj;
				System.out.println(bookEntity.getName());
				System.out.println(bookEntity.getAuthor());
			}
		}
	}
}
