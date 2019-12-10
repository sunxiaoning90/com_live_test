package com.live.test.api.core.serverloadbalance.serverdispatcher.impl;

import com.live.test.api.core.serverloadbalance.serverdispatcher.IServerDispatcher;

/**
 * 负载均衡-每次获取上一次的路由:
 * @author live
 * @2019年12月10日 @下午12:22:38
 */
public class KeepLastServerDispatcher implements IServerDispatcher {

	private int max; // max 和 min必须填写
	private int min;
	private int last;
	
	private long count = 0;//自启动后,访问过多少次

	public KeepLastServerDispatcher() {

	}

	public KeepLastServerDispatcher(int min, int max) {
		this();
		this.setMin(min);
		this.setMax(max);
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	@Override
	public void setMin(int min) {
		this.min = min;
	}

	@Override
	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public int getLast() {
		return this.last;
	}

	@Override
	public synchronized int getNext() {
		return this.getLast();
	}

	public void setLast(int last) {
		this.last = last;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	public void incrementCount() {
		this.setCount(this.getCount()+1);
	}

}
