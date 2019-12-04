package com.live.test.api.core.orderNumber.ju;

import java.util.UUID;
/**
 * uuid
 * 字符串
 * UUID.randomUUID().toString();
 * @author live
 * @2019年12月3日 @下午4:54:44
 */
public class UuidOrderNumber {
	
	public static String getNext() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		UuidOrderNumber.getNext();
	}
}
