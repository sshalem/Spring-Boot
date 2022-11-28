package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class ApiAnalyticsAspect {

	@Before(value = "com.aop.aspect.PointcutDeclarations.forDaoPackageNoGetterSetter()")
	public void beforeApiAnalytics() {
		System.out.println("Exceuting the @Before Advice - ApiAnalytics");
	}
}
