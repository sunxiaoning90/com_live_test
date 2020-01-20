package com.live.test.javaee.spring.service;

import org.junit.Test;

import com.live.test.javaee.mybatis.po.Evaluate;
import com.live.test.javaee.springmvc.service.EvaluateService;

public class EvaluateServiceTest {

	@Test
	public void getEvaluateById() {
		int id = 1;
		Evaluate po = new EvaluateService().getEvaluateById(id);
		System.out.println(po);
	}
	
//	@Test
//	public void getEvaluateAll() {
//		List<Evaluate> list = new EvaluateService().getEvaluateAll();
//		for (Evaluate evaluate : list) {
//			System.out.println(evaluate);
//		}
//	}
//	
//	@Test
//	public void saveEvaluate() {
//		Evaluate po = new Evaluate(101, "满意度-非常满意", "...");
//		boolean flg = new EvaluateService().saveEvaluate(po);
//			System.out.println(flg);
//	}
}
