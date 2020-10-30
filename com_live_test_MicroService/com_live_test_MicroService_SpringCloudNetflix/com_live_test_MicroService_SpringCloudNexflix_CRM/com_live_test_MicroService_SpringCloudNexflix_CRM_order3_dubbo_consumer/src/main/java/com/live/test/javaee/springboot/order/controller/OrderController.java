package com.live.test.javaee.springboot.order.controller;

import java.util.HashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.live.test.javaee.springboot.app.App;
import com.live.test.javaee.springboot.order.IOrderService;

@Component
@RestController
@RequestMapping("crmOrder")
//@EnableDiscoveryClient
public class OrderController {
	
	//dubbo远端服务
		@org.apache.dubbo.config.annotation.Reference(version="2020.10")
		IOrderService orderService;
		
	@RequestMapping(value = "/createOrder/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		String r = "Dubbo--"+App.APP_NAME_ALIAS + str;
		System.out.println(r);
		return orderService.createOrder(new HashMap<String, String>());
	}
}
