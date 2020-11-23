package com.live.test.javase.core.reflect;

/**
 * instanceof和isInstance这两个方法基本上是等价的，都可以判定类或者子类的实例是否是属于某个类，偏向于向上比较(父类)；而isAssignableFrom则偏向于向下比较（子类），而且isAssignableFrom比较的不是实例，而是类本身的层次结构，倾向于类本身，而不是实例;如：Object.class.isAssignableFrom(File.class)//true；另外2个方法则倾向于比较实例
 * 
 * 1）instanceof 判断 是否是某个实例或其的子类
 * 2）isAssignableFrom
 * Class类的isAssignableFrom(Class cls)方法，如果调用这个方法的class或接口 与
 * 参数cls表示的类或接口相同，或者是参数cls表示的类或接口的父类，则返回true。
 * 形象地：自身类.class.isAssignableFrom(自身类或子类.class) 返回true
 * 
 * 
 * 
 * @author live
 */
public class Test_onstanceof_isAssignableFrom {

	public static void main(String[] args) {
		boolean flg1 = new String() instanceof Object;
		boolean flg2 = Object.class.isAssignableFrom(String.class);
		boolean flg3 = String.class.isAssignableFrom(Object.class);
		System.out.println(flg1);
		System.out.println(flg2);
		System.out.println(flg3);
	}
}
