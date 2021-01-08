package com.live.test.javase.core.juc.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * forkjoin
 * 
 * @author live
 * @2020年1月9日 @下午4:18:31
 */
public class FkSum {

	private static class SumTask extends RecursiveTask<Long> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8839473985048208971L;
		
		private int startId;
		private int endId;

		public SumTask(int startId, int endId) {
			this.startId = startId;
			this.endId = endId;
		}

		// 使用ForkJoinPool调用invoke()时,底层核心调用compute()方法
		@Override
		protected Long compute() {
			// 如果满足条数小于 5000
			if (endId - startId < 10) {
				// 执行最小粒度任务
				return sumRecorders(startId, endId);
			} else {
				// fromIndex....mid.....toIndex
				int mid = (startId + endId) / 2;// 折半,将数据分成左右两部分
				SumTask left = new SumTask(startId, mid);// 左边数据
				SumTask right = new SumTask(mid + 1, endId);// 右边数据
				invokeAll(left, right);// 递归调用compute(),是否满足if条件,满足计算,不满足继续拆
				long leftResult = left.join();
				long rightResult = right.join();
				return leftResult + rightResult;
			}
		}

		private Long sumRecorders(int start, int end) {
			int sum = 0;
			for (int i = start; i <= end; i++) {
				sum += i;
				System.out.println(sum);
			}
			return (long) sum;
		}
	}

	public void sum() {
		// 核心类
//    ForkJoinPool pool = new ForkJoinPool(64);
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);
		System.out.println("getPoolSize:" + pool.getParallelism());
		
		long start = System.currentTimeMillis();
		int recordMin = 1;// 从数据库获取最小ID号
		int recordMax = 100;// 从数据库获取最大ID号
		SumTask sumTask = new SumTask(recordMin, recordMax);// 初始化总任务:

		pool.invoke(sumTask);// 拆分 计算 调用invoke, 实际是执行compute()
		System.out.println("Task is Running.....");

		System.out
				.println("The sum is " + sumTask.join() + " spend time:" + (System.currentTimeMillis() - start) + "ms");
	}

	public static void main(String[] args) {
		new FkSum().sum();
	}
}
