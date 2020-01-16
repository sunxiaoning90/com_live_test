package com.live.test.designpattern.action.adapter.demo3;

/**
 * Adapter模式的实现方法有很多，说到这我在举一个例子，我现在有这样一个场景。
 * 我有一辆JL车子和BMW的Engine和Wheel，我现在想改装这辆BORA使其拥有BMW的Engine和Wheel，我如何做呢？
 * @ClassName:Client.java
 * @Description:
 * @author:Live
 * @date:2017-1-12上午10:39:08
 */
public class Client {
		public static void main(String[] args) {
			MyJL jl = new MyJL();
			jl.Process(new Adapter());
		}
}
