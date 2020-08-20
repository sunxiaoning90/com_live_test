package com.live.test.javaee.springboot.sentinel.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

@Component
@RestController
@RequestMapping("sentinelTest")
public class SentinelTestController {

	@PostConstruct
	private void postC() {
		initFlowRules(); // 临时初始化sentinel配置
	}

	/**
	 * helloWorld() 方法就成了我们的一个资源。注意注解支持模块需要配合 Spring AOP 或者 AspectJ 一起使用。
	 */
//	@SentinelResource("HelloWorld")
//	@RequestMapping("/helloWorld")
//	public String helloWorld() {
//		// 资源中的逻辑
//		System.out.println("hello world");
//		return "hello world";
//	}

	@SentinelResource("HelloWorld")
	@RequestMapping("/helloWorld")
	public String helloWorld() {

		// 直接利用 try-with-resources 特性，自动 释放 entry 资源
		try (Entry entry = SphU.entry("HelloWorld")) {
			// 正常业务逻辑
			System.out.println("hello world");
			return "hello world";
		} catch (BlockException ex) {
			// 处理被流控的逻辑
			System.out.println("blocked!");
		}

		return "blocked";
	}

	/**
	 * 使用代码设置sentinel，也可以在sentinel-Dashboard控制台设置
	 */
	// @RequestMapping("/initFlowRules")
	private void initFlowRules() {
		FlowRule rule = new FlowRule();
		rule.setResource("HelloWorld");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 20.
//		rule.setCount(20);
		rule.setCount(1);
		
		List<FlowRule> rules = new ArrayList<>();
		rules.add(rule);
		
		FlowRuleManager.loadRules(rules);
	}
	
	// Spring WebFlux
//	public Mono<String> mono(){
//		return Mono.just("test");
//	}
}
