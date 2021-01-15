package com.live.test.javase.core.jmath.bigDecimal;

import java.math.BigDecimal;

/**
 * double\float会损失精度,高精度运算使用BigDecimal
 * 
 * @author live
 * @2019年12月11日 @上午9:52:10
 */
public class TestBigDecimal {

	public static void main(String[] args) {
		System.out.println(0.1 + 0.2);

		BigDecimal b1 = new BigDecimal("0.1");
		BigDecimal b2 = new BigDecimal("0.2");
		System.out.println(b1.add(b2));

		new TestBigDecimal().a();

		double lv = 2.5470;
		double oneDaylx = 100;// 每日利息100,需要本金多少?
		new TestBigDecimal().xinJinBao(lv, oneDaylx);// 需要:1413428
	}

	/**
	 * 结果超出预期,原因是double\float会损失精度,高精度运算使用BigDecimal: 0.7075 7.075E-5
	 */
	private void a() {
		double bEveryYearEvery1W = (10000 * (2.5470 / 100)) / 360;
		System.out.println(bEveryYearEvery1W);

		double bEveryDayEvery1 = bEveryYearEvery1W / 10000;
		System.out.println(bEveryDayEvery1);
	}

	/**
	 * 测试薪金宝
	 * 
	 * @param dlvOfYear
	 * @param oneDaylx
	 */
	private void xinJinBao(double dlvOfYear, double oneDaylx) {
//		double lv7 = 2.5470;v
		BigDecimal blx = new BigDecimal(dlvOfYear);
		BigDecimal b100 = new BigDecimal(100);
		BigDecimal blxOfYear = blx.divide(b100, 4);

		BigDecimal b1W = new BigDecimal(10000);
		BigDecimal b360 = new BigDecimal(360);

		BigDecimal bEveryYearEvery1W = b1W.multiply(blxOfYear);
		BigDecimal bEveryDayEvery1W = bEveryYearEvery1W.divide(b360, 4);
		System.out.println(bEveryYearEvery1W);
		System.out.println(bEveryDayEvery1W);

		BigDecimal bEveryDayEvery1 = bEveryDayEvery1W.divide(b1W);
		System.out.println(bEveryDayEvery1);

//		oneDayEvery1 * x = 0.02;
//		double oneDaylx = 0.02;
		BigDecimal b002 = new BigDecimal(oneDaylx);
		BigDecimal x = b002.divide(bEveryDayEvery1, 4);
		System.out.println("需要:" + x);
	}

}
