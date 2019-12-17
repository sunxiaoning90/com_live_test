package com.live.test.javaweb.core.fileupload;

import com.live.test.javaweb.core.fileupload.util.FileUtil;

public class FileManager {
	/**
	 * 文件个数上限
	 */
	public final static int FILE_UPLOAD_COUNT_MAX = 1;
	
	/**
	 * 文件大小上限,2M
	 */
	public final static long FILE_UPLOAD_SIZE_MAX = FileUtil.FILE_UNIT_M * 2;
	/**
	 * 文件基础目录
	 */
	public final static String FILE_UPLOAD_PATH_BASE = "/opt/saasUpload/";

	/**
	 * 头像目录
	 */
	public static final String FILE_UPLOAD_PATH_HEADIMAGE = FILE_UPLOAD_PATH_BASE + "HeadImage/";
	public static final String FILE_TYPE_HEADIMAGE = "HeadImage";

	/**
	 * @param domainIdentification
	 * @return
	 */
	public static String getFileDir(String type, String domainIdentification) {
		switch (type) {
		case FILE_TYPE_HEADIMAGE:
			return FILE_UPLOAD_PATH_HEADIMAGE + domainIdentification + "/";

		default:
			break;
		}
		return null;
	}

	/**
	 * 避免重复
	 * @return
	 */
	public static String wrapFileName(String fileName) {
//		return IDManager.getInstance().getNext() + "-" + fileName;
		return fileName;
	}
}
