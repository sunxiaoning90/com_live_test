package com.live.test.designpattern.create.builder;

/**
 * 指导者（Director），组装产品，负责整个产品的构建算法
 * @ClassName:PersonDirector.java
 * @Description:
 * @author:Live
 * @date:2016-12-27上午10:06:03
 */
public class PersonDirector {
	
	public Person ConstructPerson(PersonBuilder pb){
		
		pb.buildHead();
		pb.buildBody();
		pb.buildfoot();
		
		return pb.buildPersion();
	}
	
}
