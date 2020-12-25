package com.live.test.javase.core.compare;

import java.util.Arrays;
import java.util.Comparator;

public class TestCompare {

	public static void main(String[] args) {
		String[] str = new String[] {
				"c",
				"b",
				"a",
		};
		
		System.out.println(Arrays.toString(str));
		
		Arrays.sort(str, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				System.out.println(o1 + " vs " + o2 + " :" + o2.compareTo(o1));
				System.out.println(Arrays.toString(str));
				return o2.compareTo(o1);
			}
		});
		
		System.out.println(Arrays.toString(str));
	}
}
