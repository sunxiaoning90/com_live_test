package com.live.test.api.core.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * IO 工具类
 * 
 * @author live
 * @2020年5月6日 @下午6:50:14
 */
public class IOUtil {

	/**
	 * 转换 InputStrem -> String
	 * 
	 * @param in
	 * @return
	 */
	public static String coverInputStremToString(InputStream in) {
		return coverInputStremToString(in, null);
	}

	/**
	 * 转换 InputStrem -> String ,可指定字符编码：UTF8,gbk
	 * 
	 * @param in
	 * @param encode
	 * @return
	 */
	public static String coverInputStremToString(InputStream in, String encode) {
		if (in == null) {
			return null;
		}

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				if (encode != null && !encode.equals("")) {
					line = new String(line.getBytes(encode));
				}
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}