package com.live.myTry.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@WebListener
public class MyContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("========destroyed...");
	}

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("========init...");
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("Spring_ac", ac);
	}

}
