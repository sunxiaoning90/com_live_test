package com.live.test.javase.core.jmath;

import java.math.BigDecimal;

public class TestFloat {
	public static void main(String[] args) {
		float f1 = 0.55f; // 精确
		float f2 = 0.1f; // 非精确
		System.out.println(f1);
		System.out.println(f2);

		System.out.println(f2 - f1);
		System.out.println(f2 - f1 == 0.45f);

//	0.55
//	0.1
//	-0.45000002
//	false

		BigDecimal bd1 = new BigDecimal("0.55");
		BigDecimal bd2 = new BigDecimal("0.1");
		BigDecimal r = bd1.subtract(bd2);
		System.out.println(r); // 0.45

	}
}
