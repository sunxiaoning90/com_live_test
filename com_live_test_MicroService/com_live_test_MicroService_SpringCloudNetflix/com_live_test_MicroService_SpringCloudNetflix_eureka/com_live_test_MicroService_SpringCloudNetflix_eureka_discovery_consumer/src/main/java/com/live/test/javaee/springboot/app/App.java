package com.live.test.javaee.springboot.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
public class App {

	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		ConfigurableApplicationContext context  = new SpringApplicationBuilder(App.class).web(true).run(args);
		System.out.println("尝试获取context：" + context);
	}
}