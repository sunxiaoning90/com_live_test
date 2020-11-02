package com.live.test.javase.core.juc.testFutureAndFutureTask;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class AsynToSynUtil {

	public static void main(String[] args) {
		System.out.println(new Date());
		getImManagerSyn();
		System.out.println(new Date());
	}

	public static void getImManagerSyn() {

		Callable<String> c = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(4);
//				Thread.sleep(6);
				System.out.println("ok" + new Date());
				return null;
			}
		};
		FutureTask<String> f = new FutureTask<String>(c);

//		ThreadPoolExecutor p = new Task

		long t1 = System.nanoTime();
		System.out.println(t1);

		f.run();
		
		try {
			f.get();
//			f.get(6, TimeUnit.SECONDS);
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}
}
