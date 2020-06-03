package com.live.test.javaNewFeature.java7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 实现了 Closeable，可以使用 try(资源){} 来自动close资源：
 * 
 * try (创建流对象语句，如果多个,使用';'隔开) { // 读写数据 } catch (IOException e) {
 * e.printStackTrace(); }
 * 
 * @author live
 *
 */
public class TryWithResourceTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// 原始写法 try{}catch(e){}finally{资源.close()}
		File file = new File("/tmp/a.txt");
		InputStream in = new FileInputStream(file);
		try {
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = in.read(buff)) != -1) {
				System.out.println(buff);
				String r = new String(buff, 0, len);
				System.out.println(r);
			}
		} catch (Exception e) {
		} finally {
			if (in != null) {
				System.out.println("" + in.available());
				in.close();
			}
		}

		// try(资源)写法 try(资源){}catch(e)
		// InputStreams 实现了 Closeable，可以使用 try(r){} ,执行完try{} 会执行try（）方法内资源的
		// close()方法。public abstract class InputStream implements Closeable {}
		try (InputStream in2 = new FileInputStream(file)) {
			byte[] buff = new byte[1024];
			int len;
			while ((len = in2.read(buff)) != -1) {
				System.out.println(buff);
				String r = new String(buff, 0, len);
				System.out.println(r);
			}
		}
	}
}
