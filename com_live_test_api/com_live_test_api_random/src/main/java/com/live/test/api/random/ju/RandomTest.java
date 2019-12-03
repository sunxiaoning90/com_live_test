package com.live.test.api.random.ju;

import java.util.Random;
/*
 * 测试随机数
 * Random rand = new Random();rand.nextInt(size);
 */
public class RandomTest {
	public static void main(String[] args) {
		int a[] = new int[] {0,0};
		for (int i = 0; i < 100; i++) {
			Random rand = new Random();
			int size = 2;//super.routeNodeList.size();//获取 当前路由的节点总数量。
			int r = rand.nextInt(size);
			if(r == 0) {
				a[0] = a[0] + 1;
			}else {
				a[1] = a[1] + 1;
			}
			//System.out.println(r);
			
			try {
				Thread.sleep(333);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("0出现的次数:" + a[0]);
		System.out.println("1出现的次数:" + a[1]);
		System.out.println("0出现的次数/1出现的次数="+(float)a[0]/a[1]);
	}
}
