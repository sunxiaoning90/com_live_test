package com.live.aop.user.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(0)
@Component
public class PramterCheckAspect {

	/**
	 * 定义一个方法, 用于声明切入点表达式. 一般地, 该方法中再不需要添入其他的代码. 使用 @Pointcut 来声明切入点表达式.
	 * 后面的其他通知直接使用方法名来引用当前的切入点表达式.
	 */
	@Pointcut("execution(public boolean com.live.aop.user.service.UserService.saveUser())")
	public void pointcut1() {
	}

	/**
	 * 在通知的表达式中应用定义的切入点表达式
	 */
	@Before("pointcut1()")
	public void before2(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		System.out.println("前置通知（PramterCheckAspect）。methodName:" + methodName + ",args:" + Arrays.asList(args));
	}
}
