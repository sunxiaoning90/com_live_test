package com.live.spring.tx.annotation.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.live.spring.tx.annotation.hello.service.AccountService;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("ac:" + ac);

		DriverManagerDataSource dataSource = (DriverManagerDataSource) ac.getBean("dataSource");
		System.out.println("dataSource:" + dataSource);

		AccountService accountService = ac.getBean(AccountService.class);
		System.out.println("accountService:" + accountService);
		
		//
		boolean flg = accountService.transferMomeny("A", "B", 100);
		System.out.println("转账结果：" + flg);
	}
}
