package com.live.aop.hello;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {

	@Before("execution(public void com.live.aop.hello.Hello.sayHello())")
	public void before(JoinPoint joinPoint) {
		System.out.println("前置通知！");
	}
}
