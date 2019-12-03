package com.live.test.designpattern.create.singleton;

/**
 * @author live
 * @2019年12月3日 @上午11:01:56
 * 单例(静态内部类方式)
 * 静态初始化器，由JVM来保证线程安全
 */
public class RouterAccessManager {

	/**
	 * 私有化构造方法
	 */
	private RouterAccessManager() {
	}

	public static RouterAccessManager getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * 类的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例没有绑定关系，只有被调用到才会装载，从而实现了延迟加载
	 */
	private static class SingletonHolder {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static RouterAccessManager instance = new RouterAccessManager();
	}
}
