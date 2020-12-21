package com.live.test.api.core.random.lang;

/*
 * 测试随机数类 Math
 * 	double r = Math.random();
 */
public class RandomByMath {
	public static void test() {
		double r = Math.random();
		System.out.println(r);
	}

	public static int getNext() {
		double r = Math.random();
		System.out.println(r + " " + (int) (r * 10));

		return (int) (r * 10);
	}

	public static void main(String[] args) {
		int a[] = new int[] { 0, 0 };
		for (int i = 0; i < 100; i++) {
			int r = getNext();
			if (r == 0) {
				a[0] = a[0] + 1;
			} else {
				a[1] = a[1] + 1;
			}
			// System.out.println(r);

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("0出现的次数:" + a[0]);
		System.out.println("1出现的次数:" + a[1]);
		System.out.println("0出现的次数/1出现的次数=" + (float) a[0] / a[1]);
	}
}
