package com.live.test.javaee.springboot.stock;

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
@RequestMapping("stock")
public class StockController {

	@Autowired
	StockService stockService;

	@PostMapping(value = "/createStock")
	public String createStock(@RequestBody Map<String, String> map) {

		// 直接访问本地Service
		return stockService.createStock(map);
	}

}
