package com.live.test.api.core.serialize.jdkSerialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <pre>
 * JDK原生序列化工具:ObjectOutputStream 和 ObjectInputStream
Java本身提供的序列化工具基本上能胜任大多数场景下的序列化任务，关于其序列化机制，这篇文章很细致的解释了(https://blog.csdn.net/zhaozheng7758/article/details/7820018)，值得一读。Java自带的序列化工具在序列化过程中需要不仅需要将对象的完整的class name记录下来，还需要把该类的定义也都记录下，包括所有其他引用的类，这会是一笔很大的开销，尤其是仅仅序列化单个对象的时候。正因为java序列化机制会把所有meta-data记录下来，因此当修改了类的所在的包名后，反序列化则会报错。Java自带序列化工具的性能问题总结如下：
一个single object的序列化会 递归地，连同所有成员变量（instsnce variables）一起序列化了，这种默认机制很容易造成不必要的序列化开销。
序列化和反序列化过程需要上面的这种机制去递归并用反射机制去寻找所有成员变量的信息，另外如果没定义自己serialVersionUID的话，那么对象及其他变量都必须自己产生一个。上述过程开销很大。
使用默认序列化机制，所有序列化类定义完整信息都会被记录下来，包括所有包名、父类信息、以及成员变量
 * </pre>
 * 
 * @author live
 */
public class SerializeUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3674919495386768378L;

	public static void main(String[] args) {
		SerializeUtil s = new SerializeUtil();

		byte[] bytes = serialize(s);
		System.out.println(bytes);

		SerializeUtil s2 = (SerializeUtil) deserialize(bytes);
		System.out.println(s2);
	}

	public static byte[] serialize(Object obj) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] bs = baos.toByteArray();
			baos.close();
			oos.close();

			return bs;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object deserialize(byte[] bits) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bits);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object obj = ois.readObject();

			bais.close();
			ois.close();
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
