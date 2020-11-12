package com.live.test.javaee.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
@Component
public class UserService {

	public boolean delete(String id) {
		System.out.println("目标类方法执行：UserService#delete()");
		return false;
	}
}
