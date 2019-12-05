package com.live.test.api.core.schedule.juc.scheduledthreadpoolexecutor;

import java.util.List;

import org.omg.CORBA.DomainManager;

import com.live.test.api.core.cache.local.ICache;


/**
 * 刷当前容器下全部域用户授权
 * 
 * @author live
 * @2019年12月4日 @下午3:02:49
 */
public class RefreshAuthTask implements Runnable {

	@Override
	public void run() {
		System.out.println("任务开始(刷新全部域的授权数)");
		try {
			this.doRefreshAuthCountAll();
			System.out.println("任务结束(刷新全部域的授权数)");
		} catch (Exception e) {
			System.out.println("警告:任务异常(刷新全部域的授权数)");
			e.printStackTrace();
		}
	}

	/**
	 * 刷新全部域的授权数
	 */
	private void doRefreshAuthCountAll() throws Exception {
	}

	/**
	 * 刷新某个域的授权数,by 域标识
	 */
	private void doRefreshAuthCountByDomainId(String domainId) {
	}
}
