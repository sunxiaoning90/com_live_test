package com.live.test.designpattern.action.adapter.demo1;

public interface IQueue {
	void push(Object item);
	Object putout();
	Object getLastItem();
	Object getFirstItem();
}
