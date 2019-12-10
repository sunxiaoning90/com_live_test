package com.live.test.api.core.serverloadbalance.serverdispatcher.impl;

import com.live.test.api.core.serverloadbalance.serverdispatcher.IServerDispatcher;

/**
 * 负载均衡-轮询获取路由:1,2,3,1,2,3...
 * @author live
 * @2019年12月10日 @下午12:22:38
 */
public class PollServerDispatcher implements IServerDispatcher {

	private int max; // max 和 min必须填写
	private int min;
	private int last;
	
	private long count = 0;//自启动后,访问过多少次

	private PollServerDispatcher() {

	}

	public PollServerDispatcher(int min, int max) {
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
		int next ;// 取值
		if(this.getCount() == 0) {// 第一次取值取最小值
			next = this.getMin();
		}else {
			next = this.getLast() + 1;
		}

		if (next > this.getMax()) {// 若取值小于最小值,取最小值
			next = this.getMin();
		} else if (next < this.getMin()) {// 若取值小于最小值,取最小值
			next = this.getMin();
		}

		System.out.println("上一次概况:count:" + this.getCount() + ",last:" + this.getLast()+",next:" + next);
		this.setLast(next);// 刷新 last
		this.incrementCount();//刷新总数
		return next;
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
