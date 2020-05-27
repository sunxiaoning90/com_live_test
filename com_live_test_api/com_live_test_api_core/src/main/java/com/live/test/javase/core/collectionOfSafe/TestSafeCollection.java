package com.live.test.javase.core.collectionOfSafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestSafeCollection {
	/**
	 * 一、List
	 */

	/**
	 * 如何创建线程安全的 list ?<br>
	 * 方式一、Collections 工具类的 synchronizedList <br>
	 * 方式二、new CopyOnWriteArrayList() <br>
	 */
	private void createSafeList() {
		// 方式一、Collections 工具类的 synchronizedList
		List<String> list1 = Collections.synchronizedList(new ArrayList<String>());

		// 方式二、new CopyOnWriteArrayList()
		List<String> list2 = new CopyOnWriteArrayList<String>();
		List<String> list3 = new CopyOnWriteArrayList<String>(new ArrayList<String>());
	}

	/**
	 * Vector、Collections.synchronizedList、juc包的CopyOnWriteArrayList区别？<br>
	 * 1)原理 <br>
	 * Vector通过在方法级别上加入了synchronized关键字实现线程安全性。<br>
	 * 
	 * Collections.synchronizedList生成了特定同步的SynchronizedCollection，生成的集合每个同步操作都是持有mutex这个锁
	 * (Object mutex = new Object())，所以再进行操作时就是线程安全的集合了<br>
	 * 
	 * CopyOnWriteArrayList是java.util.concurrent包下面的一个实现线程安全的List,顾名思义，
	 * Copy~On~Write~ArrayList在进行写操作(add,remove,set等)时会进行Copy操作，可以推测出在进行写操作时CopyOnWriteArrayList性能应该不会很高。<br>
	 * 
	 * 2)性能 <br>
	 * Vector对所有操作进行了synchronized关键字修饰，性能应该比较差 <br>
	 * 
	 * CopyOnWriteArrayList在写操作时需要进行 ‘数组copy’操作，读性能较好，写性能较差 <br>
	 * 
	 * Collections.synchronizedList性能较均衡，但是迭代操作并未加锁，所以需要时需要额外注意 <br>
	 * 附测试结果：
	 */
	private void testListAll() {
		ArrayList<String> list1 = new ArrayList<String>();
		Vector<String> list2 = new Vector<String>();
		List<String> list4 = Collections.synchronizedList(list1);
		List<String> list3 = new CopyOnWriteArrayList<String>();
	}

	/**
	 * Arrays.asList(list)返回的list对象 是线程安全的吗？<br>
	 * java.util.Arrays 注意，Arrays.asList(list)返回的list对象是Arrays类的一个内部类：
	 * java.util.Arrays$ArrayList，并不是支持了ArrayList类的所有方法
	 */
	private void testArrays() {
		String[] a = new String[] { "a", "b", "c" };
		List<String> list = Arrays.asList(a);
//		Arrays.asList("a","b","c");

		System.out.println(list.getClass());// 注意，Arrays.asList(a)返回的list对象是Arrays类的一个内部类
											// java.util.Arrays$ArrayList，并不是支持了ArrayList类的所有方法
//		list.remove(0); //Exception in thread "main" java.lang.UnsupportedOperationException
		list.set(0, "A");
		for (String s : list) {
			System.out.println(s);
		}
	}

	/**
	 * 二、Set
	 */

	/**
	 * 如何创建线程安全的 set?<br>
	 * 方式一、Collections 工具类（java.util.Collections）的 synchronizedSet <br>
	 * 方式二、juc包：new CopyOnWriteArraySet() <br>
	 * 方式三、juc包：new ConcurrentSkipListSet() <br>
	 */
	private void createSafeSet() {
		// 方式一、Collections 工具类（java.util.Collections）的 synchronizedSet
		Set<String> set1 = Collections.synchronizedSet(new HashSet<String>());

		// 方式二、juc包：new CopyOnWriteArraySet()
		Set<String> set2 = new CopyOnWriteArraySet();
		Set<String> set3 = new CopyOnWriteArraySet(new HashSet<String>());

		// 方式三、juc包：new ConcurrentSkipListSet()
		Set<String> set4 = new ConcurrentSkipListSet();
		Set<String> set5 = new ConcurrentSkipListSet(new HashSet<String>());
	}

	/**
	 * juc中提供了 concurrenthashset吗，如果没提供，如何手动实现？<br>
	 * 如何自己实现一个 ConcurrentHashSet，类似 ConcurrentHashMap 借助
	 * ConcurrentHashMap来实现具体功能，伪代码
	 */
	private static abstract class ConcurrentHashSet<E> implements Set<E> {

		private final Object OBJECT = new Object();

		private final ConcurrentHashMap<E, Object> map = new ConcurrentHashMap<>();

		private Set<E> keySet() {
			return map.keySet();
		}

		@Override
		public boolean add(E e) {
			return map.put(e, OBJECT) == null;// 实际是借助 ConcurrentHashMap 来实现具体功能
		}

		@Override
		public boolean remove(Object o) {
			return map.remove(o) != null;// 实际是借助 ConcurrentHashMap 来实现具体功能
		}

	}

	/**
	 * 三、Map
	 */

	/**
	 * 如何创建线程安全的 map ?<br>
	 * 方式一、使用 线程安全的 Hashtable <br>
	 * 方式二、Collections 工具类（java.util.Collections）的 synchronizedMap <br>
	 * 方式三、juc包：new ConcurrentHashMap() <br>
	 * 方式四、juc包：new ConcurrentSkipListMap() <br>
	 */
	private void createSafeMap() {
		// 方式一、使用 线程安全的 Hashtable
		Map<String, String> map1 = new Hashtable<String, String>();

		// 方式二、Collections 工具类（java.util.Collections）的 synchronizedMap
		Map<String, String> map2 = Collections.synchronizedMap(new HashMap<String, String>());

		// 方式三、juc包：new ConcurrentHashMap()
		Map<String, String> map4 = new ConcurrentHashMap<String, String>();
		Map<String, String> map5 = new ConcurrentHashMap<String, String>(new HashMap<String, String>());

		// 方式四、juc包：new ConcurrentSkipListMap()
		Map<String, String> map6 = new ConcurrentSkipListMap<String, String>();
		Map<String, String> map7 = new ConcurrentSkipListMap<String, String>(new HashMap<String, String>());
	}

//	HashMap 和 Hashtable 区别？
	
//	ConcurrentHashMap 和 ConcurrentSkipListMap 各自的优势？
	
	/**
	 * 四、Quque
	 */

	/**
	 * add() 和 offer() <br>
	 * add() : 添加元素，如果添加成功则返回true，如果队列是满的，则抛出异常; <br>
	 * offer() : 添加元素，如果添加成功则返回true，如果队列是满的，则返回false; <br>
	 * 区别：对于一些有容量限制的队列，当队列满的时候，用add()方法添加元素，则会抛出异常，用offer()添加元素，则返回false. <br>
	 * 
	 * remove() 和 poll() <br>
	 * remove() : 移除队列头的元素并且返回，如果队列为空则抛出异常; <br>
	 * poll() : 移除队列头的元素并且返回，如果队列为空则返回null; <br>
	 * 区别：在移除队列头元素时，当队列为空的时候，用remove()方法会抛出异常，用poll()方法则会返回null. <br>
	 * 
	 * element() 和 peek() <br>
	 * element() ：返回队列头元素但不移除，如果队列为空，则抛出异常; <br>
	 * peek() ：返回队列头元素但不移除，如果队列为空，则返回null; <br>
	 * 区别 ：在取出队列头元素时，如果队列为空，用element()方法则会抛出异常，用peek()方法则会返回null.
	 */
	private static void testOffer() throws Exception {
		offer(new ArrayBlockingQueue<>(2));
		offer(new LinkedBlockingQueue<>(2));
		offer(new PriorityBlockingQueue<>(2));
		offer(new SynchronousQueue<>());
	}

	private static void offer(BlockingQueue<Integer> queue) throws Exception {
		System.out.println("queue.getClass() = " + queue.getClass().getName());
		System.out.println("queue.offer(1) = " + queue.offer(1));
		System.out.println("queue.offer(2) = " + queue.offer(2));
		System.out.println("queue.offer(3) = " + queue.offer(3));
		System.out.println("queue.size() = " + queue.size());
		System.out.println("queue.take() = " + queue.take());
	}

	/**
	 * SynchronousQueue 1. SynchronousQueue 是无空间，offer 永远返回 false <br>
	 * 2. SynchronousQueue take() 方法会被阻塞，必须被其他线程显示地调用 put(Object);
	 */
	private void testSynchronousQueue() {
		BlockingQueue<Integer> queue = new SynchronousQueue<>();
		System.out.println("queue.offer(1) = " + queue.offer(1));
		System.out.println("queue.offer(2) = " + queue.offer(2));
		System.out.println("queue.offer(3) = " + queue.offer(3));
		try {
			System.out.println("queue.take() = " + queue.take());
		} catch (InterruptedException ignore) {
		}
		System.out.println("queue.size = " + queue.size());
	}

	/**
	 * PriorityBlockingQueue <br>
	 * 1. PriorityBlockingQueue put(Object) 方法不阻塞 <br>
	 * 2. PriorityBlockingQueue offer(Object) 方法不限制 <br>
	 * 3. PriorityBlockingQueue 插入对象会做排序，默认参照元素 Comparable 实现，或者显示地传递 Comparator
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void testPriorityBlockingQueue() throws Exception {
		BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(2);

		queue.put(9);
		queue.put(1);
		queue.put(8);
		System.out.println("queue.size() = " + queue.size());
		System.out.println("queue.take() = " + queue.take());
		System.out.println("queue = " + queue);
	}

//	ArrayBlockingQueue 和 LinkedBlockingQueue的区别
	
//	LinkedTransferQueue和SynchronousQueue区别
	
	/**
	 * 无界队列 与 有界队列
	 */

	/**
	 * 线程池使用的那种队列？<br>
	 * Executors.newFixedThreadPool(2);<br>
	 * Excutors工具类在newFixedThreadPool()时，使用的是 LinkedBlockingQueue，源码如下：
	 * 
	 * public static ExecutorService newFixedThreadPool(int nThreads) { <br>
	 * return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
	 * new LinkedBlockingQueue<Runnable>()); }
	 */

	/**
	 * 五、List、Set、Map 汇总
	 */

	/**
	 * ArrayList、HashSet、HashMap 是否线程安全？<br>
	 * ArrayList、HashSet、HashMap 非线程安全。
	 */
	private void createUnSafeCollection() {
		List list = new ArrayList();
		Set set = new HashSet<String>();
		HashMap map = new HashMap();
	}

	/**
	 * 如何创建线程安全的集合?<br>
	 * juc并发包 或 Collections
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
	 * 方式1：方式1：使用 java.util.Collections工具类的synchronizedList等进行转换，Wrapper
	 * 设计模式（所有的方法都被 synchronized 同步或互斥）。<br>
	 * 方式2：使用 java.util.Collections包下安全集合类进行转换。
	 */
	private void changeUnSafeCollectionToSafe() {
		List list = new ArrayList();
		Set set = new HashSet<String>();
		HashMap map = new HashMap();

//		方式1：使用 java.util.Collections工具类的synchronizedList等进行转换，Wrapper 设计模式（所有的方法都被 synchronized 同步或互斥）
		List safeList1 = Collections.synchronizedList(list);
		Set safeSet1 = Collections.synchronizedSet(set);
		Map safeMap1 = Collections.synchronizedMap(map);

//		方式2：使用 java.util.Collections包下安全集合类进行转换
		List safeList2 = new CopyOnWriteArrayList(list);
		Set safeSet2 = new CopyOnWriteArraySet(set);
	}

	/**
	 * 遍历非线程安全的集合，获取 Iterator 后，边遍历边修改原始集合，会发生异常吗？ <br>
	 * 会异常：java.util.ConcurrentModificationException
	 * 
	 * 运行结果：<br>
	 * 遍历修改前，list大小：2 <br>
	 * a Exception in thread "main" <br>
	 * java.util.ConcurrentModificationException <br>
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
	 * 遍历线程安全的集合，获取 Iterator 后，边遍历边修改原始集合，会发生异常吗？ <br>
	 * 不会异常
	 * 
	 * 运行结果：<br>
	 * 遍历修改前，list大小：2 <br>
	 * a <br>
	 * b <br>
	 * 遍历修改后，list大小：4
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
