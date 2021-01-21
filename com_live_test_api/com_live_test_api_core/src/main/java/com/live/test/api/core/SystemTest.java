package com.live.test.api.core;

import java.util.concurrent.TimeUnit;

public class SystemTest {
public static void main(String[] args) {
	System.out.println(System.nanoTime());
	System.out.println(System.currentTimeMillis());
	try {
		TimeUnit.MILLISECONDS.sleep(0);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	System.out.println(System.nanoTime());
	System.out.println(System.currentTimeMillis());
}
}
