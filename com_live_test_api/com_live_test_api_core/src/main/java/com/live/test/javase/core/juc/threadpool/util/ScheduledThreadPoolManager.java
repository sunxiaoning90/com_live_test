package com.live.test.javase.core.juc.threadpool.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 单例
 * 具有调度功能的线程池
 * @author live
 * @2019年12月3日 @下午2:55:54
 */
public class ScheduledThreadPoolManager {

	/**
	 * 用于刷新授权
	 */
	public static final String THREADPOOL_KEY_REFRESHAUTHCOUNTALL = "THREADPOOL_KEY_REFRESHAUTHCOUNTALL";

	/**
	 * 线程池s
	 */
	private final Map<String, ScheduledThreadPoolExecutor> threadPoolMap = new ConcurrentHashMap<String, ScheduledThreadPoolExecutor>();

	private ScheduledThreadPoolManager() {
	}

	public static ScheduledThreadPoolManager getInstance() {
		return singletonHolder.instance;
	}

	private static class singletonHolder {
		public static final ScheduledThreadPoolManager instance = new ScheduledThreadPoolManager();
	}

	public boolean existDomain(String id) {
		return this.threadPoolMap.containsKey(id);
	}

	public void put(String id, ScheduledThreadPoolExecutor pool) {
		this.threadPoolMap.put(id, pool);
	}

	/**
	 * 获取线程池时,有则返回,没有则创建 串行保证线程安全
	 * 
	 * @param key
	 * @return
	 */
	public ScheduledThreadPoolExecutor getThreadPool(String key) {
		ScheduledThreadPoolExecutor m = this.threadPoolMap.get(key);
		if (m == null) {
			synchronized (this) {
				if (m == null) {
					m = new ScheduledThreadPoolExecutor(1);
					this.put(key, m);
				}
			}
		}
		return m;
	}

	public void remove(String id) {
		this.threadPoolMap.remove(id);
	}
}
