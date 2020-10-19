package com.live.test.javaee.springboot.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@RequestMapping("order")
@EnableDiscoveryClient
public class OrderController {

	//本地服务
	//@Autowired
	//OrderService orderService;
	
	//dubbo远端服务
//	@Autowired
	IOrderService orderService;

	/**
	 * 请求： http://{{host}}:8081/order/createOrder
	 * 
	 * { "userId": 1, "ticketId": 1, "pcs": 2 }
	 * 
	 * @return
	 */
//	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	@PostMapping(value = "/createOrder")
	public String createOrder(@RequestBody Map<String, String> map) {

//		String userId = map.get("userId");
//		String ticketId = map.get("ticketId");
//		String pcs = map.get("pcs");
//
//		String r = new StringBuffer("响应来自:" + App.APP_NAME_ALIAS + ",购票成功：").append("userId:").append(userId)
//				.append(",ticketId:").append(ticketId).append(",pcs:").append(pcs).toString();
//		System.out.println(r);
//		return r;

		return orderService.createOrder(map);
	}

}
