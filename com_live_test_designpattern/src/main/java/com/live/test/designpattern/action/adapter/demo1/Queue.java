package com.live.test.designpattern.action.adapter.demo1;

import java.util.ArrayList;

/**
 * 适配器模式实现有两种类型：对象适配器、类适配器。下面的代码是对象适配器方式。也就是适配器（Queue）中是使用被适配（ArrayList）的对象实现。
 * @ClassName:Queue.java
 * @Description:
 * @author:Live
 * @date:2017-1-12上午10:10:42
 */
public class Queue implements IQueue {

	//组合优于继承
	 ArrayList adaptee; //被适配者
	 
	 
	public Queue() {
		super();
		System.out.println("适配器(对象适配器)");
		adaptee = new ArrayList();
	}

	@Override
	public void push(Object item) {
		adaptee.add(item);
	}

	@Override
	public Object putout() {
		Object item = adaptee.get(0);
		adaptee.remove(item);
		return item;
	}

	@Override
	public Object getLastItem() {
		return adaptee.get(adaptee.size()-1);
	}

	@Override
	public Object getFirstItem() {
		return adaptee.get(0);
	}

}
