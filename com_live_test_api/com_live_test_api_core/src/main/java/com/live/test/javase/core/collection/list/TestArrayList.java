package com.live.test.javase.core.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestArrayList {
	public static void main(String[] args) {
		System.out.println(1);
	}

	public void testCRUD() {
		List<String> list = new ArrayList<String>();
		System.out.println(list);
		
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println(list);
		
		String s2 = list.remove(0);
		System.out.println(s2);
		
		list.set(0, "a2");
		
		String s = list.get(0);
		System.out.println(s);
		
	}
	
	/**
	 * 2 打印
	 */
	@Test
	public void testPrint() {
		List<String> list = new ArrayList<String>();
		
		list.add("a");
		list.add("b");
		list.add("c");
		
		// 打印 地址 :打印的是具体元素，list重写 了toString(),ArrayList 继承自 java.util.AbstractCollection，toString() 是由该父类实现的。
		System.out.println(list);// [a, b, c]
		
		// 打印 具体元素
		System.out.println(Arrays.toString(list.toArray()));// [a, b, c]
	}
	
	///list重写 了toString(),ArrayList 继承自 java.util.AbstractCollection，toString() 是由该父类实现的：
	
//	 public String toString() {
//	        Iterator<E> it = iterator();
//	        if (! it.hasNext())
//	            return "[]";
//
//	        StringBuilder sb = new StringBuilder();
//	        sb.append('[');
//	        for (;;) {
//	            E e = it.next();
//	            sb.append(e == this ? "(this Collection)" : e);
//	            if (! it.hasNext())
//	                return sb.append(']').toString();
//	            sb.append(',').append(' ');
//	        }
//	    }
	
	
	/**
	 * <pre>
	 * com.live.test.javase.core.collection.testList.Persion@1794d431
		0
		com.live.test.javase.core.collection.testList.Persion@42e26948
		1
		com.live.test.javase.core.collection.testList.Persion@57baeedf
		2
	 * </pre>
	 */
	public static void testListAdd() {
		List<Persion> list = new ArrayList<Persion>();

		Persion h = null;
		for (int i = 0; i < 3; i++) {
//			if(h==null) {
			h = new Persion();
//			}
			h.setName(i + "");
			list.add(h);
		}

		list.forEach((e) -> {
			System.out.println(e);
			System.out.println(e.getName());
		});
	}

}
