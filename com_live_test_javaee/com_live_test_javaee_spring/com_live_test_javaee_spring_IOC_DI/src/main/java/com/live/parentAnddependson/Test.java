package com.live.parentAnddependson;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans-parentAnddependson.xml");
		Person person = (Person) ac.getBean("person");
		System.out.println("人"+person);
		
		Person chinesePerson1 = (Person) ac.getBean("chinesePerson1");
		System.out.println("中国人1" + chinesePerson1);
		
		Person chinesePerson2 = (Person) ac.getBean("chinesePerson2");
		System.out.println("中国人2"+chinesePerson2);
		
		Person chinesePerson3 = (Person) ac.getBean("chinesePerson3");
		System.out.println("中国人3"+chinesePerson3);
	}
}
