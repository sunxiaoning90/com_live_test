package com.live.test.arithmetic.dataStructure.linearStructure.stack;
import java.util.Stack;

/**
 * 栈：先进后出
 * 
 * @author live
 */
public class TestStack {

	public static void main(String[] args) {
//		testCRUD();

//		testPop();

		testCapacityIncrement();

	}

	/*
	 * 栈：先进后出
	 */
	@SuppressWarnings("unused")
	private static void testPop() {
		Stack<String> stack = new Stack<String>();

		stack.add("a");
		stack.add("b");
		stack.add("c");

		System.out.println(stack); // [a, b, c]

		String first = stack.get(0);
		System.out.println(first); // c

		String last = stack.get(stack.size() == 0 ? 0 : stack.size() - 1);
		System.out.println(last); // c

		String pop = stack.pop();
		System.out.println(pop); // c
	}

	/**
	 * <pre>
	 * 1、关于扩容后容量的大小：
	   如果扩容值大于零（capacityIncrement），扩容后的大小 = 当前容量 + capacityIncrement;
	   否则扩容后的大小 = 当前容量 × 2。
	   源码：int newCapacity = oldCapacity + ((capacityIncrement > 0) ? capacityIncrement : oldCapacity);
	 * </pre>
	 */
	private static void testCapacityIncrement() {
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < 100; i++) {
			stack.add(String.valueOf(i)); // 10、20、40、80、160
			System.out.println(i + "->" + stack.capacity());
		}
	}

	@SuppressWarnings("unused")
	private static void testCRUD() {
		Stack<String> stack = new Stack<String>();
		System.out.println(stack); // []

		boolean add = stack.add("a");
		System.out.println(add);// true
		stack.add("b");
		stack.add("c");

		String remove = stack.remove(0);
		System.out.println(remove); // a

		String set = stack.set(1, "A");
		System.out.println(set); // c

		String get = stack.get(0);
		System.out.println(get);// b

		System.out.println(stack);// [b, A]
	}

}
