package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@Before(value = "execution(public void com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(* com.aop.dao.AccountDao.addAccount())")
//	@Before(value = "execution(public void addAccount())")
	public void logAllMethodCallsAdvice() {
		System.out.println("Exceuting Aspect");
	}
}

