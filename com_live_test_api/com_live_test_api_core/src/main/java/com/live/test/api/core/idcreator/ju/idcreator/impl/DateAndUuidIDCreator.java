package com.live.test.api.core.idcreator.ju.idcreator.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * ID生成器： 日期-uuid <br>
 * eg:2019_12_16_15_06_58_058-fe04d427-b332-4d8f-b924-e67c3fcd44fa
 * 
 * @author live
 * @2019年12月12日 @下午2:55:24
 */
public class DateAndUuidIDCreator extends BaseIDCreator {

	@Override
	public String doNext() {
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_sss").format(new Date()) + "-" + UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(new DateAndUuidIDCreator().doNext());
		}
	}
}
