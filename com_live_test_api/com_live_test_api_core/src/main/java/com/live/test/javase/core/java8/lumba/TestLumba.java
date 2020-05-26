package com.live.test.javase.core.java8.lumba;

import org.junit.Test;
/**
 * lumba表达式可以看作是一个有输入参数的代码块，本质上是一个函数式接口的实现，也就说实际上可以认为lumba表达式是一个函数，一般可以作为函数的参数（函数式编程）。关于这个函数到底是什么类型的、实现的方法怎么调用都是编译器可以在具体环境下推断出来的
 * @author live
 * @2019年12月31日 @上午10:18:39
 */
public class TestLumba {
	/**
	 * 普通写法
	 */
	@Test
	public void test1() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("run1...");
			}
		});
		t.start();
	}

	/**
	 * lumba表达式
	 */
	@Test
	public void test2() {
		Thread t = new Thread(() -> {
			System.out.println("run2...");
		});
		t.start();
	}
}

/**
 * 观察Runnable 接口源代码:它是一个函数式接口
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
*/
