package com.live.test.api.core.idcreator.ju.impl;

import java.util.UUID;
/**
 * 生成ID,by uuid
 * @author live
 * @2019年12月12日 @下午2:55:24
 */
public class UuidIDCreator extends BaseIDCreator{

	@Override
	public String doNext() {
		String id = UUID.randomUUID().toString();
		return id;
	}
}
