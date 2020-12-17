package live.sort;

import java.util.Arrays;

public class Sort {
	public static void main(String[] args) {
		System.out.println(System.nanoTime()/System.currentTimeMillis());
		System.out.println(System.currentTimeMillis());
		int[] a = new int[] { 1, 3, 2, 5, 0 };
		System.out.println(Arrays.toString(a));

		testMaoPao(a);
//		testXuanZe(a);
		System.out.println(Arrays.toString(a));
	}

	private static void testErFen(int[] a) {

	}

	private static void testXuanZe(int[] a) {
		for (int i = 0; i < a.length; i++) {
			int minIndex = i; // 最小下标

			for (int j = (i + 1); j < a.length; j++) {
				if (a[minIndex] > a[j]) {
					minIndex = j;
				}
			}

			// if minIndex
			if (minIndex != i) {
				int tmp = a[i];
				a[i] = a[minIndex];
				a[minIndex] = tmp;
			}

			System.out.println("\n第" + i + "趟，结果：" + Arrays.toString(a));
		}
	}

	private static void testMaoPao(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < (a.length - 1) - i; j++) {
				if (a[j] > a[j + 1]) {
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					System.out.print("第" + i + "趟，发现较大值：" + a[i]);
				}
			}
			System.out.println("\n第" + i + "趟，结果：" + Arrays.toString(a));
		}
	}

	private static void testMaoPao1(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < a.length - i; j++) {
				if (a[j] > a[j + 1]) {
					int tmp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = tmp;
					System.out.print("第" + i + "趟，发现较大值：" + a[i]);
				}
			}
			System.out.println("\n第" + i + "趟，结果：" + Arrays.toString(a));
		}
	}
}
