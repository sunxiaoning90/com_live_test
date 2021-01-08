package com.live.test.api.core.idcreator.ju.idcreator.impl;

import java.util.UUID;

/**
 * ID生成器： UUID </br>
 * eg:fe04d427-b332-4d8f-b924-e67c3fcd44fa
 * 
 * @author live
 * @2019年12月12日 @下午2:55:24
 */
public class UuidIDCreator extends BaseIDCreator {

	@Override
	public String doNext() {
		String id = UUID.randomUUID().toString();
		return id;
	}
}
