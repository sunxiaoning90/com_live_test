package com.live.test.javaee.springboot.order.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.live.test.javaee.springboot.app.App;

@Component
@RestController
@RequestMapping("crmOrder")
//@EnableDiscoveryClient
public class OrderController {
	
	@RequestMapping(value = "/createOrder/{str}", method = RequestMethod.GET)
	public String echoByFeign(@PathVariable String str) {
		String r = App.APP_NAME_ALIAS + str;
		System.out.println(r);
		return r;
	}
}
