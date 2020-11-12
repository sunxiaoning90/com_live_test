package com.live.test.javaee.spring.testCycleDepends;

public class TestCycleA {
	private TestCycleB b;

	public TestCycleB getB() {
		return b;
	}

	public void setB(TestCycleB b) {
		this.b = b;
	}
	
	
}
