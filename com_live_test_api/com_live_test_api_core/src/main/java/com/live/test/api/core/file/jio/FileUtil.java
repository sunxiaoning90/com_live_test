package com.live.test.api.core.file.jio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * File工具类,基于 java.io
 * 
 * @author live
 * @2019年12月12日 @下午4:51:07
 */
public class FileUtil {

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
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		FileUtil.copyFile("/opt/saasUpload/a.zip", "/opt/saasUpload/a2.zip");
		System.out.println("copy一个大小为372M的文件耗时:" + (start - System.currentTimeMillis()));
		//copy一个大小为372M的文件耗时:-5443
	}
}
