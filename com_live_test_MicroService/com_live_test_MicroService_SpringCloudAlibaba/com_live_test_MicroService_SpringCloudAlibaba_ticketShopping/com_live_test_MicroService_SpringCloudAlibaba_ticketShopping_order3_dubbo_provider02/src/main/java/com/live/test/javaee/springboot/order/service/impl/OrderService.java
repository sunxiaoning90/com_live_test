package com.live.test.javaee.springboot.order.service.impl;

import java.io.Serializable;
import java.util.Map;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;

import com.live.test.javaee.springboot.app.App;
import com.live.test.javaee.springboot.order.IOrderService;

//@Service
@com.alibaba.dubbo.config.annotation.Service(version="2020.10",interfaceClass=IOrderService.class)
@DubboComponentScan(basePackages="com.live.test.javaee.springboot.order.service.impl")
public class OrderService implements IOrderService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1234555811399902161L;
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
