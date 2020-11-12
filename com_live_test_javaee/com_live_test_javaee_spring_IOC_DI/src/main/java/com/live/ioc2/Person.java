package com.live.ioc2;

import com.live.ioc.Car;


public class Person {
	private String name;// String
	private int age;// 基本类型
	private Car car;
	
	//如果没用到无参构造器，可以省去。
	//建议保留
	public Person() {
	}

	public Person(String name, int age, Car car) {
		super();
		this.name = name;
		this.age = age;
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", car=" + car + "]";
	}
}
