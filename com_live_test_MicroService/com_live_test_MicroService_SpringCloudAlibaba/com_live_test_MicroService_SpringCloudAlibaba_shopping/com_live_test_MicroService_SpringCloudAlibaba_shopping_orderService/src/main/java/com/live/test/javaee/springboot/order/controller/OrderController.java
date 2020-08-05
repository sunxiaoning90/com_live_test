package com.live.test.javaee.springboot.order.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
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
