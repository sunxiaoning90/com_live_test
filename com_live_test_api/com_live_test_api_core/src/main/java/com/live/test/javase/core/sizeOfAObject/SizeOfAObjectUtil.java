package com.live.test.javase.core.sizeOfAObject;

import org.openjdk.jol.info.ClassLayout;

/**
 * https://blog.csdn.net/Sunxn1991/article/details/109046685
 * 三、Object o = new Object()在内存中占了多少字节
markword 8字节，因为java默认使用了calssPointer压缩，classpointer 4字节，padding 4字节 因此是16字节
如果没开启classpointer默认压缩，markword 8字节，classpointer 8字节，padding 0字节 也是16字节

四、User (int id,String name) User u = new User(1,‘张三’)；占用多少字节
markword 8字节，开启classPointer压缩 ，classpointer 4字节，instance data int 4字节，开启普通对象指针压缩 String 4字节 padding 4 一共24字节
 * @author live
 */
public class SizeOfAObjectUtil {

	public static void main(String[] args) {
		Object o = new Object();
		System.out.println(ClassLayout.parseInstance(o).toPrintable());
	}
}
