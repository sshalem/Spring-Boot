package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

	@Before(value = "com.aop.aspect.PointcutDeclarations.forDaoPackageNoGetterSetter()")
	public void beforeAdd() {
		System.out.println("Exceuting the @Before Advice - beforeAdd");
	}
}
