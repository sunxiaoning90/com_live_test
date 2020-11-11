package com.live.aop.hello;

import org.springframework.stereotype.Component;

@Component
public class Hello {
	public void sayHello() {
		System.out.println("This is sayHello()!");
	}
}
