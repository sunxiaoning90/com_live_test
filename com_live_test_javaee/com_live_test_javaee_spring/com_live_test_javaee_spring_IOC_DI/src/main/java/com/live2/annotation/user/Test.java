package com.live2.annotation.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live2.annotation.user.controller.UserController;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-annotation.xml");
		System.out.println("ac:" + ac);
		
		UserController bean = (UserController) ac.getBean("userController");
		System.out.println("bean:" + bean);
		bean.saveUser();
	}
}
