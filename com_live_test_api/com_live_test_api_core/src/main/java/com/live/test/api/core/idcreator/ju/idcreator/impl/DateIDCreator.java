package com.live.test.api.core.idcreator.ju.idcreator.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.live.test.api.core.idcreator.IIDCreator;

/**
 * ID生成器： 格式化日期(并发下，不保证全局唯一，可以追加更精确的后缀进行区分，比如追加UUID) </br>
 * eg:2019_12_16_15_06_58_058
 * 
 * @author live
 * @2019年12月12日 @下午2:55:24
 */
public class DateIDCreator implements IIDCreator {
	private String last;

	@Override
	public String getNext() {
		String id = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_sss").format(new Date());
		this.setLast(id);
		return id;
	}

	@Override
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
