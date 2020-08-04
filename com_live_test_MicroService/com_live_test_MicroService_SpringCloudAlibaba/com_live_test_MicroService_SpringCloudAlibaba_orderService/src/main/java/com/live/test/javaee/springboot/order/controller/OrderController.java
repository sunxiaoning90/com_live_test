package com.live.test.javaee.springboot.order.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ComponentScan
@Controller
@RequestMapping("order")
public class OrderController {

	@RequestMapping("/createOrder")
	@ResponseBody
	public String createOrder() {
		return "createOrder ok";
	}
}
