package com.live.test.javaee.springboot.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@Controller
@RequestMapping("order")
public class OrderController {

//	@Autowired
//	RestTemplate restTemplate;
//
//	@RequestMapping("/createOrder")
//	@ResponseBody
//	public String createOrder() {
////		return "createOrder ok";
//		String url = "";
//		String r = restTemplate.getForObject(url, String.class);
//		return r;
//	}
}
