package com.live.test.designpattern.create.builder;
/**
 * 具体的厂商（ConcreteBuilder1、ConcreteBuilder2、…ConcreteBuilderN），实现厂商接口，负责产品零部件的生产工作
 * @ClassName:HumanBuilder.java
 * @Description:
 * @author:Live
 * @date:2016-12-27上午10:05:34
 */
public class TransformerBuilder implements PersonBuilder {
	private Person person;
		
	public TransformerBuilder(){
		this.person = new Person();
	}
	
	@Override
	public void buildHead() {
		this.person.setHead("变形金刚的头");
	}

	@Override
	public void buildBody() {
		this.person.setBody("变形金刚的身体");
	}

	@Override
	public void buildfoot() {
		this.person.setFoot("变形金刚的脚丫");
	}

	@Override
	public Person buildPersion() {
		return this.person;
	}

}
