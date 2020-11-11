package com.live.aop.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.aop.user.service.IUserService;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("ac:" + ac);
		
		IUserService bean =  (IUserService)ac.getBean("userService");
		System.out.println("bean:" + bean);
		bean.saveUser();
	}
}
