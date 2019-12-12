package com.live.test.api.core.idcreator.ju.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.live.test.api.core.idcreator.ju.IIDCreator;
/**
 * 生成ID,by uuid
 * @author live
 * @2019年12月12日 @下午2:55:24
 */
public class DateStrIDCreator implements IIDCreator{
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
