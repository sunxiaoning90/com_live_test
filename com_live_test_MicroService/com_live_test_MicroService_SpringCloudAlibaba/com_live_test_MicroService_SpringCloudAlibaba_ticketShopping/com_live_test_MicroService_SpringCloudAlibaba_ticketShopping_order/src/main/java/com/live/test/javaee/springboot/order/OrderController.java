package com.live.test.javaee.springboot.order;

import java.util.Map;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.live.test.javaee.springboot.app.App;

@Component
@RestController
@RequestMapping("order")
@EnableDiscoveryClient
public class OrderController {

//	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
//	public String echo(@PathVariable String str) {
//		System.out.println("*provider1:" + str);
//		return "hello" + str;
//	}

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

		String userId = map.get("userId");
		String ticketId = map.get("ticketId");
		String pcs = map.get("pcs");

		String r = new StringBuffer("响应来自:" + App.APP_NAME_ALIAS + ",购票成功：").append("userId:").append(userId)
				.append(",ticketId:").append(ticketId).append(",pcs:").append(pcs).toString();
		System.out.println(r);
		return r;
	}

}
