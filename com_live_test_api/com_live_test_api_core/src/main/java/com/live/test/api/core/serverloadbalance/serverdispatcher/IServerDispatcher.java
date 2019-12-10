package com.live.test.api.core.serverloadbalance.serverdispatcher;

public interface IServerDispatcher {
	
	/**
	 * 初始化最小值
	 * @return
	 */
	void setMin(int min);
	
	/**
	 * 初始化最大值
	 * @return
	 */
	void setMax(int max);
	
	/**
	 * 最后一次取值
	 * @return
	 */
	int getLast();
	
	/**
	 * 下一次取值
	 * @return
	 */
	int getNext();
	
	/**
	 * 自启动后,访问过多少次
	 * @return
	 */
	long getCount();
}
