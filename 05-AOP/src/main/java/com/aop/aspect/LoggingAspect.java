package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@Before(value = "execution(* com.aop.dao.CourseDao.getCourseName())")
//	@Before(value = "execution(public void getCourseName())")
	public void logAllMethodCallsAdvice() {
		System.out.println("In Aspect");
	}
}
