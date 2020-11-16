package com.live.spring.tx.XML.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.live.spring.tx.XML.hello.service.AccountService;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-tx-XML.xml");
		System.out.println("ac:" + ac);

		DriverManagerDataSource dataSource = (DriverManagerDataSource) ac.getBean("dataSource");
		System.out.println("dataSource:" + dataSource);

		AccountService accountService = (AccountService) ac.getBean("accountService");
		System.out.println("accountService:" + accountService);
		
		//
		boolean flg = accountService.transferMomeny("A", "B", 100);
		System.out.println("转账结果：" + flg);
	}
}
