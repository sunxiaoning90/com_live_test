package com.live.test.javase.core.juc.threadpool.util;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

//import core.spzc.platform.web.helper.Helper;
//import spzc.module.fileImport.importFromMemory.ImportSaveThread;
//import spzc.module.fileImport.importFromMemory.ThreadPoolManager;

public class ThreadPoolManagerTest {

	@Test
	public void t1() {
		ThreadPoolExecutor pool = ThreadPoolManager.getInstance().getThreadPool("import_save");
		System.out.println(pool);
		//threadPool.execute(new ImportSaveThread(this,Helper.getContext()));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor pool = ScheduledThreadPoolManager.getInstance().getThreadPool("a");
		
//		如果业务耗时太长,大过了设定的频率,会等待业务执行完成后再依次执行
//		pool.scheduleAtFixedRate(new Runnable() {
//
//			@Override
//			public void run() {
//				System.out.println(new Date());
//				try {
//					Thread.sleep(6000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}, 1000, 5000, TimeUnit.MILLISECONDS);
		
		System.out.println(new Date());
		
//		上次任務執行完成後,延迟执行,一直循环执行
		pool.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				System.out.println(new Date());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 2000, 1000, TimeUnit.MILLISECONDS);
	}

}
