package com.live.test.javaNewFeature.java8;

import java.util.HashMap;

public class TestLumbda {

	/**
	 * lumbda
	 */
	public void test1() {
		Thread t = new Thread(() -> {
			System.out.println("run...");
		});

		t.start();
	}

	/**
	 * lumbda ::
	 */
	public void test2() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.forEach(System.out::printf);
	}
}
