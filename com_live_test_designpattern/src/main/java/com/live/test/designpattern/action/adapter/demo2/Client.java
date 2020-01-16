package com.live.test.designpattern.action.adapter.demo2;
/**
 * 先举个简单的代码例子，我现在要做一个队列的类，实现先进先出的功能。利用ArrayList对象。
 * 首先,先定义一些队列的接口，接口中定义队列的方法;
 * 然后,我们再来利用ArrayList对象实现一个队列.
 * @ClassName:Client.java
 * @Description:
 * @author:Live
 * @date:2017-1-12上午10:11:29
 */
public class Client {
	public static void main(String[] args) {
		Queue queue = new Queue();
		
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.push(5);
		
		System.out.println("firstItem:"+queue.getFirstItem());
		System.out.println("lastItem:"+queue.getLastItem());
		
		System.out.println("output:"+queue.putout());
		
		queue.push(11);
		
		System.out.println("firstItem:"+queue.getFirstItem());
		System.out.println("lastItem:"+queue.getLastItem());
		/**
		 *打印结果: firstItem:1 lastItem:5 output:1 firstItem:2 lastItem:11
		 */
	}
}
