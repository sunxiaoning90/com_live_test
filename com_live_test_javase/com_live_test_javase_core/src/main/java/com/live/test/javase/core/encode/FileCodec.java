package com.live.test.javase.core.encode;

import java.io.File;

/**
 * @author fantiyu
 *
 */
/** 
 * @ClassName: FileCodec 
 * @Description: 定义文件接口及抽象方法
 * @author Administrator
 * @date 2015-7-6 下午01:57:29  
 */ 
public interface FileCodec {
	public String EncodeFileToString(File file);
	public byte[] DecodeStringToBuffer(String content);
}
