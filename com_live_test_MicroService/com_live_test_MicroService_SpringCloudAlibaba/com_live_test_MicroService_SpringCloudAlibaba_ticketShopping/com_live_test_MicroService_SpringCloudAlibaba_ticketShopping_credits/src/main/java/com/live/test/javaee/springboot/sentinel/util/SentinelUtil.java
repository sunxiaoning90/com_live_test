package com.live.test.javaee.springboot.sentinel.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

public class SentinelUtil {
	/**
	 * 使用代码设置sentinel，也可以在sentinel-Dashboard控制台设置
	 * 
	 * http://xx/
		每秒正常服务1个请求，超出的请求被拒绝了：
		hello world
		blocked!
	 */
	public static void initFlowRules() {
		FlowRule rule = new FlowRule();
		rule.setResource("createCredits");
		rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
		// Set limit QPS to 20.
//		rule.setCount(20);
		rule.setCount(1);
		
		List<FlowRule> rules = new ArrayList<>();
		rules.add(rule);
		
		FlowRuleManager.loadRules(rules);
	}
}
