package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.live.test.javaee.springboot.config.controller.ConfigController;

@SpringBootApplication 
@ComponentScan(value = {"com.live.test.javaee.springboot.*" })
//@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class App {

	public static void main(String[] args) {
//		SpringApplication.run(HelloSpringBoot.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		ConfigController bean = context.getBean(ConfigController.class);
		System.out.println("尝试获取bean：" + bean);
		
//		int in = 0;
//		try {
//			in = System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(in);
	}
}