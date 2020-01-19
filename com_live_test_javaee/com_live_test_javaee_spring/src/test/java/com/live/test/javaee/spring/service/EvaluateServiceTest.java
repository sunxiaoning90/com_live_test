package com.live.test.javaee.mybatis.service;

import org.junit.Test;

import com.live.test.javaee.mybatis.po.Evaluate;

import junit.framework.TestCase;

public class EvaluateServiceTest extends TestCase {

	@Test
	public void test1() {
		int id = 2;
		Evaluate po = new EvaluateService().getEvaluateById(id);
		System.out.println(po);
	}
}
