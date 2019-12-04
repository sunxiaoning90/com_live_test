package com.live.test.api.core.orderNumber.ju;

import java.util.Random;
/*
 * 随机数
 * int\long
 * new Random().nextInt(size);
 */
public class RandomOrderNumber {
	
	
	public static int getNext(int max) {
		return new Random().nextInt();
	}
	
	public static long getNext(long max) {
		return new Random().nextLong();
	}
	
	public static void getNext() {
		int a[] = new int[] {0,0};
		int size = 2;//super.routeNodeList.size();//获取 当前路由的节点总数量。
		for (int i = 0; i < 100; i++) {
			Random rand = new Random();
			int r = rand.nextInt(size);
			if(r == 0) {
				a[0] = a[0] + 1;
			}else {
				a[1] = a[1] + 1;
			}
			//System.out.println(r);
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("0出现的次数:" + a[0]);
		System.out.println("1出现的次数:" + a[1]);
		System.out.println("0出现的次数/1出现的次数="+(float)a[0]/a[1]);
	}
	
	public static void main(String[] args) {
		
	}
}
