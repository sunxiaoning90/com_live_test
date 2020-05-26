package com.live.test.javase.core.collectionOfSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class TestSafeCollection {

//	ArrayList、HashSet、HashMap 是线程安全的吗？
//	ArrayList、HashSet、HashMap 非线程安全。
	private void createUnSafeCollection() {
		List list = new ArrayList();
		Set set = new HashSet<String>();
		HashMap map = new HashMap();
	}

	/**
	 * 如何创建线程安全的集合，juc并发包 或 Collections
	 */
	private void createSafeCollectionByJUC() {
		// JUC并发包中，CopyOnWriteArrayList
		List safeList = new CopyOnWriteArrayList();

		// JUC并发包中，CopyOnWriteArraySet
		Set safeSet1 = new CopyOnWriteArraySet();
		// JUC并发包中，ConcurrentSkipListSet
		Set safeSet2 = new ConcurrentSkipListSet();

		// JUC并发包中，ConcurrentHashMap
		Map safeMap1 = new ConcurrentHashMap();
		// JUC并发包中，ConcurrentSkipListMap
		Map safeMap2 = new ConcurrentSkipListMap();
		// JUC并发包中，ConcurrentNavigableMap() //注意：是一个接口
	}

	/**
	 * 怎样将已有的集合list、set、map转为 线程安全的集合？<br>
	 * 方式1：使用 java.util.Collections工具类进行转换<br>
	 * 方式2：使用 java.util.Collections包下安全集合类进行转换
	 */
	private void changeUnSafeCollectionToSafe() {
		List list = new ArrayList();
		Set set = new HashSet<String>();
		HashMap map = new HashMap();

//		方式1：使用 java.util.Collections工具类进行转换
		List safeList1 = Collections.synchronizedList(list);
		Set safeSet1 = Collections.synchronizedSet(set);
		Map safeMap1 = Collections.synchronizedMap(map);

//		方式2：使用 java.util.Collections包下安全集合类进行转换
		List safeList2 = new CopyOnWriteArrayList(list);
		Set safeSet2 = new CopyOnWriteArraySet(set);
	}

	/**
	 * 非线程安全的容器，获取 Iterator 后，边遍历边修改原始容器，会发生异常吗？ <br>
	 * 会异常：java.util.ConcurrentModificationException
	 * 
	 * 运行结果：<br>
				 遍历修改前，list大小：2 <br>
				 a Exception in thread "main" <br>
				 java.util.ConcurrentModificationException <br>
	 */
	private void iteratorAndAddOfUnsafeList() {
		List list = new ArrayList();
		list.add("a");
		list.add("b");

		System.out.println("遍历修改前，list大小：" + list.size());

		Iterator it = list.iterator();
		for (Object obj : list) {
			System.out.println(obj);
			list.add("c");
		}

		System.out.println("遍历修改后，list大小：" + list.size());
	}

	/**
	 * 线程安全的容器，获取 Iterator 后，边遍历边修改原始容器，会发生异常吗？ <br>
	 * 不会异常
	 * 
	 * 运行结果：<br>
				遍历修改前，list大小：2 <br>
				a <br>
				b <br>
				遍历修改后，list大小：4
	 */
	private void iteratorAndAddOfSafeList() {
		List safeList = new CopyOnWriteArrayList();
		safeList.add("a");
		safeList.add("b");

		System.out.println("遍历修改前，list大小：" + safeList.size());

		Iterator it = safeList.iterator();
		for (Object obj : safeList) {
			System.out.println(obj);
			safeList.add("c");
		}

		System.out.println("遍历修改后，list大小：" + safeList.size());
	}

	public static void main(String[] args) {
		TestSafeCollection c = new TestSafeCollection();
		c.iteratorAndAddOfSafeList();
		c.iteratorAndAddOfUnsafeList();
	}

}
