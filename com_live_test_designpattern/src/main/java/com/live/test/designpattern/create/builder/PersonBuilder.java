package com.live.test.designpattern.create.builder;

/**
 * 厂商的接口（Builder）
 * @ClassName:PersonBuilder.java
 * @Description:
 * @author:Live
 * @date:2016-12-27上午10:04:59
 */
public interface PersonBuilder {
	//
	void buildHead();
	void buildBody();
	void buildfoot();
	
	Person buildPersion();
}
