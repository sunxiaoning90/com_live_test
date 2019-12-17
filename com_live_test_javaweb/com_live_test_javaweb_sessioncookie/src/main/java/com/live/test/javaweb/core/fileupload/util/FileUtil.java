package com.live.test.javaweb.core.fileupload.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author live
 * @2019年12月16日 @下午4:16:17
 */
public class FileUtil {

	/**
	 * 单位:byte
	 */
	public final static long FILE_UNIT_BYTE = 1;
	
	/**
	 * 单位:KB
	 */
	public final static long FILE_UNIT_KB = FILE_UNIT_BYTE * 1024;
	
	/**
	 * 单位:M
	 */
	public final static long FILE_UNIT_M = FILE_UNIT_KB * 1024;

	/**
	 * 单位:G
	 */
	public final static long FILE_UNIT_G = FILE_UNIT_M * 1024;
	
	/**
	 * 复制文件
	 * 
	 * @param in:输入流
	 * @param target:目标文件
	 */
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
		FileInputStream fin;
		try {
			fin = new FileInputStream(src);
			wrriteFile(fin, target);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
