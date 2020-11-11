package com.live.test.javaee.spring.testCycleDepends;

public class TestCycleB {
	private TestCycleA a;

	public TestCycleA getA() {
		return a;
	}

	public void setA(TestCycleA a) {
		this.a = a;
	}
}
