package com.live.test.javaee.springboot.credits;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.live.test.javaee.springboot.sentinel.util.SentinelUtil;

@Component
@RestController
@RequestMapping("credits")
public class CreditsController {

	@PostConstruct
	private void postC() {
		SentinelUtil.initFlowRules(); // 临时初始化sentinel配置
	}

	@Autowired
	CreditsService orderCredits;

	@SentinelResource("createCredits")
	@PostMapping(value = "/createCredits")
	public String createOrder(@RequestBody Map<String, String> map) {
		// 直接访问本地Service
		return orderCredits.createCredits(map);
	}

}
