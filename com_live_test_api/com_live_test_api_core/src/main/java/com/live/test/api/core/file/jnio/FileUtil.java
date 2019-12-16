package com.live.test.api.core.file.jnio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 文件工具类,基于nio
 * @author live
 * @2019年12月16日 @下午3:47:40
 */
public class FileUtil {

	/**
	 * 写文件
	 * 使用nio的 FileChannel实现将文件输入流写入磁盘
	 * @param fin
	 * @param target
	 */
	@SuppressWarnings("resource")
	public static void wrriteFile(FileInputStream fin, String target) {
		// TODO null检查
		FileChannel sourceChannel = null;
		FileChannel targetChannel = null;

		try {
			sourceChannel = fin.getChannel();
			targetChannel = new FileOutputStream(target).getChannel();
			targetChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (targetChannel != null) {
				try {
					targetChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (sourceChannel != null) {
				try {
					sourceChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 复制文件(使用Channel)
	 * 
	 * @param src:源文件
	 * @param target:目标文件
	 */
	public static void copyFile(String source, String target) {
		FileInputStream fin = null;
		try {
			fin = new FileInputStream(source);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		wrriteFile(fin, target);
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		FileUtil.copyFile("/opt/saasUpload/a.zip", "/opt/saasUpload/a2.zip");
		System.out.println("copy一个大小为372M的文件耗时:" + (start - System.currentTimeMillis()));
		//copy一个大小为372M的文件耗时:-5099
	}

}
