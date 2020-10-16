package com.live.test.javaee.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.live.test.javaee.springboot.stock.StockController;

@SpringBootApplication
@ComponentScan(value = { "com.live.test.javaee.springboot.*" })
public class App {
	public static final String APP_NAME_ALIAS = "库存服务-ticketShopping_stock";

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
		StockController bean = context.getBean(StockController.class);
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