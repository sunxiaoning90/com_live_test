package com.live.test.designpattern.action.adapter.demo2;

public interface IQueue {
	void push(Object item);
	Object putout();
	Object getLastItem();
	Object getFirstItem();
}
