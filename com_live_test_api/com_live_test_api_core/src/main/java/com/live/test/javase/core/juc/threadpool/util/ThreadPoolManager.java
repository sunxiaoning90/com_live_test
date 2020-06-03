package com.live.test.javase.core.juc.threadpool.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 单例
 * 管理线程池,每个业务使用自己的线程池.cus使用cus的线程池,order使用order的线程池
 * @author live
 * @2019年12月3日 @下午2:55:54
 */
public class ThreadPoolManager {
	
	/**
	 * 线程池s
	 */
	private final Map<String, ThreadPoolExecutor> threadPoolMap = new ConcurrentHashMap<String, ThreadPoolExecutor>();
	
	private ThreadPoolManager() {}
	
	public static ThreadPoolManager getInstance() {
		return singletonHolder.instance;
	}
	
	private static class singletonHolder{
		public static final ThreadPoolManager instance = new ThreadPoolManager();
	}
	
	public boolean existDomain(String id) {
		return this.threadPoolMap.containsKey(id);
	}
	
	public void put(String id,ThreadPoolExecutor pool) {
		this.threadPoolMap.put(id, pool);
	}
	
	/**
	 * 获取线程池时,有则返回,没有则创建
	 * 串行保证线程安全
	 * @param key
	 * @return
	 */
	public ThreadPoolExecutor getThreadPool(String key) {
		ThreadPoolExecutor m = this.threadPoolMap.get(key);
		if (m == null) {
			synchronized (this) {
				if (m == null) {
//					m = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(), 10, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
					m = new ThreadPoolExecutor(1, 1,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>());
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
