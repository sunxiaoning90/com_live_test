package com.live.test.javase.core.compare;

public class SwapUtil {

	/**
	 * Swaps x[a] with x[b].
	 */
	private static void swap(Object[] x, int a, int b) {
		Object t = x[a];
		x[a] = x[b];
		x[b] = t;
	}

	public static void main(String[] args) {

	}
}
