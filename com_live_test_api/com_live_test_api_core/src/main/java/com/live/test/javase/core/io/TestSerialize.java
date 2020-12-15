package com.live.test.javase.core.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;

public class TestSerialize {
	
	/**
	 * 防止反序列化破坏单例模式: 添加readResolve方法，反序列化时，会直接返回该方法指定的对象，不需要创建对象。
	 * 
	 * @return
	 * @throws ObjectStreamException
	 */
//	private Object readResolve() throws ObjectStreamException {
//		return instance;
//	}
	

	public static void main(String[] args) {
		try {
			testSerializable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testSerializable() throws Exception {
		// 通过方法获取两个一样的单例
		String s1 = new String("测试序列化 和 反序列化");

		// 序列化到磁盘（保存对象）
		FileOutputStream fos = new FileOutputStream("/opt/aaa.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(s1);
		oos.close();
		fos.close();

		// 从磁盘发序列化（获取对象）
		FileInputStream fis = new FileInputStream("/opt/aaa.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		String s = (String) ois.readObject();

		System.out.println(s == s1);
		System.out.println(s);
		System.out.println(s1);
	}
}
