package com.live2.annotation.hello;

import org.springframework.stereotype.Component;

@Component
public class Hello {

	public Hello() {
		super();
		System.out.println("This is Hello Constructor!");
	}

}
