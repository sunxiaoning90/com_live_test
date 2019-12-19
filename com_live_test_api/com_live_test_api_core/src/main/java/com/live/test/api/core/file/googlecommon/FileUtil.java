package com.live.test.api.core.file.googlecommon;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;
/**
 * File工具类,基于 com.google.common.io.Files
 * @author live
 * @2019年12月19日 @上午11:17:59
 */
public class FileUtil {

	/**
	 * 复制文件
	 * 
	 * @param from:源文件
	 * @param to:目标文件
	 */
	public static void copyFile(String from, String to) {
		File fromFile = new File(from);
		File toFile = new File(to);
		
		try {
			Files.copy(fromFile, toFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
