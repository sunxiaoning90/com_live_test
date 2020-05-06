package com.live.test.javaee.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication // SpringBoot 的核心注解，主要目的是开启自动配置
@Controller
public class HelloSpringBoot {

	@RequestMapping("/test")
	@ResponseBody
	public String hello() {
		return "test";
	}

	public static void main(String[] args) {
//		SpringApplication.run(HelloSpringBoot.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(HelloSpringBoot.class, args);
		HelloSpringBoot bean = context.getBean(HelloSpringBoot.class);
		System.out.println(bean);
	}
}
