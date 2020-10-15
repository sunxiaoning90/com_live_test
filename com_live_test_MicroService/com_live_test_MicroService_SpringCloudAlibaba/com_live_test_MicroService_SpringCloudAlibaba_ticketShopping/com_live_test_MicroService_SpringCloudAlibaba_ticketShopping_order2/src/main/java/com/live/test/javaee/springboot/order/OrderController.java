package com.live.test.javaee.springboot.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.live.test.javaee.springboot.app.App;

@Component
@RestController
@RequestMapping("order")
@EnableDiscoveryClient
public class OrderController {

//	通过 Spring Cloud 原生注解 @EnableDiscoveryClient 开启服务注册发现功能。给 RestTemplate 实例添加 @LoadBalanced 注解，开启 @LoadBalanced 与 Ribbon 的集成：
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	/**
	 * 请求： http://{{host}}:8081/order/createOrder
	 * 
	 * { "userId": 1, "ticketId": 1, "pcs": 2 }
	 * 
	 * @return
	 */
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

		// 调用远端服务
		// http get方式：
//		return restTemplate.getForObject("http://ticketShopping-order2Lb-provider/order/createOrder", String.class, map);

		// http post方式：
		return restTemplate.postForObject("http://ticketShopping-order2Lb-provider/order/createOrder", map,
				String.class, map);
	}

}
