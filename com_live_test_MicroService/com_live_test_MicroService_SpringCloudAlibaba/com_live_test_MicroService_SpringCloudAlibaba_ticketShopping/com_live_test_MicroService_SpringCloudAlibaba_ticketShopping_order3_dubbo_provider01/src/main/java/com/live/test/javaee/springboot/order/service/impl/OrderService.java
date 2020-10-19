package com.live.test.javaee.springboot.order.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.live.test.javaee.springboot.app.App;
import com.live.test.javaee.springboot.order.IOrderService;

//@com.alibaba.dubbo.config.annotation.Service
@Service
public class OrderService implements IOrderService {

	public String createOrder(Map<String, String> map) {
		String userId = map.get("userId");
		String ticketId = map.get("ticketId");
		String pcs = map.get("pcs");

		String r = new StringBuffer("响应来自:" + App.APP_NAME_ALIAS + ",购票成功：").append("userId:").append(userId)
				.append(",ticketId:").append(ticketId).append(",pcs:").append(pcs).toString();
		System.out.println(r);
		return r;

		// rpc调用远端服务
	}

}
