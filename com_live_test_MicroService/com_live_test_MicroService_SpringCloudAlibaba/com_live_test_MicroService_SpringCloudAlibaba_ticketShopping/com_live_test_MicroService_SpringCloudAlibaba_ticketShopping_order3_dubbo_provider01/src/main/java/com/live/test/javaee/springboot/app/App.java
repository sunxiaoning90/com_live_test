package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.live.test.javaee.springboot.order.IOrderService;
import com.live.test.javaee.springboot.order.service.impl.OrderService;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
@EnableDiscoveryClient
public class App {
	public static final String APP_NAME_ALIAS = "订单服务-ticketShopping_order3_dubbo_provider01";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		
		IOrderService bean = context.getBean(OrderService.class);
		System.out.println("尝试获取bean：" + bean);
		
		System.out.println(APP_NAME_ALIAS + "启动成功...");

//		int in = 0;
//		try {
//			in = System.in.read();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println(in);
	}
}