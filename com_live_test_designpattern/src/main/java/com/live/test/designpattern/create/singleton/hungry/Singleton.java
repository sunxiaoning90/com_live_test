package com.live.test.designpattern.create.singleton.hungry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;

/**
 * 单例(饿汉)
 * 
 * @author live
 * @2019年12月3日 @下午5:36:40
 */
public class Singleton implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3576270684111905727L;
	private static final Singleton instance = new Singleton();

	private Singleton() {
		if (instance != null) { // 防止反射破坏单例模式
			throw new RuntimeException("This class is singleton.");
		}
	}

	public static Singleton getInstance() {
		return instance;
	}

	/**
	 * 防止反序列化破坏单例模式: 添加readResolve方法，反序列化时，会直接返回该方法指定的对象，不需要创建对象。
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
	private Object readResolve() throws ObjectStreamException {
		return instance;
	}

	public static void main(String[] args) throws Exception {
		TestRelect();

		testSerializable();

	}

	private static void TestRelect() throws Exception {
		Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		Singleton s1 = constructor.newInstance();
		Singleton s2 = constructor.newInstance();
		System.out.println(s1);
		System.out.println(s2);
	}

	@SuppressWarnings("resource")
	private static void testSerializable() throws Exception {
		// 通过方法获取两个一样的单例
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();

		// 序列化到磁盘（保存对象）
		FileOutputStream fos = new FileOutputStream("/opt/aaa.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s1);
		oos.close();
		fos.close();

		// 从磁盘发序列化（获取对象）
		FileInputStream fis = new FileInputStream("/opt/aaa.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Singleton s = (Singleton) ois.readObject();

		System.out.println(s);
		System.out.println(s1);
		System.out.println(s2);
	}

}
