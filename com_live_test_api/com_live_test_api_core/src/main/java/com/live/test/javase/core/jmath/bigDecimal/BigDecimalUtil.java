package com.live.test.javase.core.jmath.bigDecimal;

import java.math.BigDecimal;

public class BigDecimalUtil {

	public static Double add(Double value1, Double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.add(b2).doubleValue();
	}

	public static Double subtract(Double value1, Double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.subtract(b2).doubleValue();
	}

	public static Double multiply(Double value1, Double value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
		return b1.multiply(b2).doubleValue();
	}

	public static Double divide(Double value1, Double value2) {
//		if(value2 == 0) {
//			return 0d;
//		}
		BigDecimal b1 = new BigDecimal(Double.toString(value1));
		BigDecimal b2 = new BigDecimal(Double.toString(value2));
//		return b1.divide(b2).doubleValue();
//		MathContext mc = new MathContext("HALF_UP");
		return b1.divide(b2, 2).doubleValue();
	}
}
