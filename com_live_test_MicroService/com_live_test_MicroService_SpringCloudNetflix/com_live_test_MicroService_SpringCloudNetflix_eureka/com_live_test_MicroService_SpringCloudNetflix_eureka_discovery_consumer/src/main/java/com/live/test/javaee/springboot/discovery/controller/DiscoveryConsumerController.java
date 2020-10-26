package com.live.test.javaee.springboot.discovery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Component
@RestController
@RequestMapping("discovery/consumer")
//@EnableDiscoveryClient
@EnableEurekaClient
public class DiscoveryConsumerController {

	@Bean
//	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();
		System.out.println(rt);
		return rt;
	}

	@Autowired
	public RestTemplate restTemplate;

	@RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
	public String echo(@PathVariable String str) {
		System.out.println(str);

		// RestTemplate 缺点：使用 RestTemplate 进行http调用时，服务地址是固定的，虽然可以指定域名，可以修改ngxin的配置文件来修改真实服务器地址，但是还是很繁琐不方便
		String serverAddress = "http://192.168.1.50:8082";
		String url = serverAddress + "/discovery/provider/echo/" + str;
		return restTemplate.getForObject(url, String.class);
	}

}
