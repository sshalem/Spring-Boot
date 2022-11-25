package com.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutDeclarations {

	/**
	 * Example for Combining Pointcuts
	 */
	@Pointcut(value = "execution(* com.aop.dao.*.*(..))")
	public void forDaoPackage() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.get*(..))")
	public void getter() {
	}

	@Pointcut(value = "execution(* com.aop.dao.*.set*(..))")
	public void setter() {
	}

	/**
	 * Combine the pointcuts : include package ... exclude getter/setter
	 */
	@Pointcut(value = "forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {
	}
}
