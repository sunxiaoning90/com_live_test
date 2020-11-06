package com.live.test.arithmetic.leetcode.cn.problems_002;

public class Solution {
	public static int[] twoSum(int[] z, int target) {
		int[] a = new int[2];
		for (int n = 0; n < z.length; n++) {
			int x = z[n];
			for (int i = n + 1; i < z.length; i++) {
				int h = x + z[i];
				if (target == h) {
					a[0] = n;
					a[1] = i;
				}
			}
		}
		return a;
	}

	public static void main(String[] args) {
		int[] z = new int[] { 1, 2, 8, 9 };
		int target = 9;
		int[] r = twoSum(z, target);

		System.out.println(r[0] + " " + r[1]);
	}
}
