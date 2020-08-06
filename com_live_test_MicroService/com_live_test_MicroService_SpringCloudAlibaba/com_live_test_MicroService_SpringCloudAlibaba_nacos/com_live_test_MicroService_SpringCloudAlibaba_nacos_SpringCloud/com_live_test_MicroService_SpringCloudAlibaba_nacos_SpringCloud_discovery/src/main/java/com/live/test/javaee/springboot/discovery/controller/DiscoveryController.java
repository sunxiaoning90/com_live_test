package com.live.test.javaee.springboot.discovery.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;

@Component
@Controller
@RequestMapping("discovery")
public class DiscoveryController {

	@NacosInjected
	private NamingService namingService;

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public List<Instance> get(@RequestParam String serviceName) throws NacosException {
		System.out.println("<<<serviceName:" + serviceName);

		List<Instance> instances = namingService.getAllInstances(serviceName);
		System.out.println("instance:" + instances); // instance:[{"clusterName":"DEFAULT","enabled":true,"ephemeral":true,"healthy":true,"instanceHeartBeatInterval":5000,"instanceHeartBeatTimeOut":15000,"instanceId":"127.0.0.1#8080#DEFAULT#DEFAULT_GROUP@@example","instanceIdGenerator":"simple","ip":"127.0.0.1","ipDeleteTimeout":30000,"metadata":{},"port":8080,"serviceName":"DEFAULT_GROUP@@example","weight":1.0}]
		return instances;
	}

}
