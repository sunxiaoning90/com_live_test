package com.live.propertyPlaceholder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans-propertyPlaceholder.xml");

		JdbcTemplate jt = (JdbcTemplate) ac.getBean("jdbcTemplate");
		System.out.println("jt：（属性值使用的是jdbc.properties文件中的值）" + jt);
	}
}
