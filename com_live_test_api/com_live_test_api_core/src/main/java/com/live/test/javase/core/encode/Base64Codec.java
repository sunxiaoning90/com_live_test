package com.live.test.javase.core.encode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * @ClassName: Base64Codec
 * @Description: (实现FileCodec接口类,一些二进制数转成普通字符用于网络传输)
 * @author Administrator
 * @date 2015-7-6 下午02:13:37
 */
public class Base64Codec implements FileCodec {

	/*
	 * (non-Javadoc) Title:DecodeStringToBuffer Description: 对字符串数据转换
	 * 
	 * @param content 字符串参数
	 * 
	 * @return
	 * 
	 * @see core.attachment.FileCodec#DecodeStringToBuffer(java.lang.String)
	 */
	public byte[] DecodeStringToBuffer(String content) {
		// TODO 使用JDK8 Base64,待验证
		// return decoder.decodeBuffer(content);
		return Base64.getDecoder().decode(content);
	}

	/*
	 * (non-Javadoc) Title:EncodeFileToString Description: 对文件转换
	 * 
	 * @param file 文件
	 * 
	 * @return
	 * 
	 * @see core.attachment.FileCodec#EncodeFileToString(java.io.File)
	 */
	public String EncodeFileToString(File file) {
		try {
			InputStream is = new FileInputStream(file);
			byte[] data = new byte[is.available()];
			is.read(data);
			is.close();
			// TODO 使用JDK8 Base64,待验证
			// return encoder.encode(data);
			return Base64.getEncoder().encodeToString(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
