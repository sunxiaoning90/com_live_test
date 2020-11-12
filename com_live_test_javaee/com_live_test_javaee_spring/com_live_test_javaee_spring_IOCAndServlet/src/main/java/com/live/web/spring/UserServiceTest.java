package com.live.web.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.live.web.spring.service.UserService;

public class UserServiceTest {

	@Test
	public void testLogin() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserService userService = ac.getBean(UserService.class);
		userService.login("a","123456");
	}

}
