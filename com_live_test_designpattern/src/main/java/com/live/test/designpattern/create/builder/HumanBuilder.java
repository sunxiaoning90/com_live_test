package com.live.test.designpattern.create.builder;

/**
 * 具体的厂商（ConcreteBuilder1、ConcreteBuilder2、…ConcreteBuilderN），实现厂商接口，负责产品零部件的生产工作
 * @ClassName:HumanBuilder.java
 * @Description:
 * @author:Live
 * @date:2016-12-27上午10:05:34
 */
public class HumanBuilder implements PersonBuilder {
	
	//
	private Person person;
	
	public HumanBuilder(){
		this.person = new Person();
	}

	@Override
	public void buildHead() {
		this.person.setHead("人头");

	}

	@Override
	public void buildBody() {
		this.person.setBody("人身体");
	}

	@Override
	public void buildfoot() {
		this.person.setFoot("人脚丫");
	}
	
	@Override
	public Person buildPersion() {
		return this.person;
	}

}
