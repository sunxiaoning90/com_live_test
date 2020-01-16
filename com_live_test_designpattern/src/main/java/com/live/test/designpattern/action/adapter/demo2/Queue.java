package com.live.test.designpattern.action.adapter.demo2;

import java.util.ArrayList;

public class Queue extends ArrayList implements IQueue {
	public Queue(){
		System.out.println("适配器(类适配器)");
	}
	
	@Override
	public void push(Object item) {
		this.add(item);
	}

	@Override
	public Object putout() {
		Object item = this.get(0);
		this.remove(item);
		return item;
	}

	@Override
	public Object getLastItem() {
		return this.get(this.size()-1);
	}

	@Override
	public Object getFirstItem() {
		return this.get(0);
	}

}
