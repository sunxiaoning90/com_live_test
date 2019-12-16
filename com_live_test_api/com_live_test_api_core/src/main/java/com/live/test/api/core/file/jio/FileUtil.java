package com.live.test.api.core.file.jio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * File工具类
 * @author live
 * @2019年12月12日 @下午4:51:07
 */
public class FileUtil {

	public static void main(String[] args) {
//		new FileUtil().wrriteFile();
	}

	/**
	 * 复制文件(FileOutputStream)
	 * 
	 * @param in:输入流
	 * @param target:目标文件
	 */
//	public static void wrriteFile(FileInputStream in,String target) throws Exception {
	public static void wrriteFile(InputStream in, String target) {
		if (in == null || target == null) {
			return;
		}
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(target);

			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 复制文件
	 * 
	 * @param src:源文件
	 * @param target:目标文件
	 */
	public static void copyFile(String src, String target) {

	}
}
