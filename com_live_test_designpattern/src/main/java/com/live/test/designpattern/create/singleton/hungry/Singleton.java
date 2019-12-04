package com.live.test.designpattern.create.singleton.hungry;

/**
 * 单例(饿汉)
 * 
 * @author live
 * @2019年12月3日 @下午5:36:40
 */
public class Singleton {
	private static final Singleton instance = new Singleton();

	private Singleton() {
	}

	public static Singleton getInstance() {
		return instance;
	}
}
