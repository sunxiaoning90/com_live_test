package com.live.test.javaweb.core.fileupload.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.live.test.javaweb.core.fileupload.FileManager;
import com.live.test.javaweb.core.fileupload.Result;
/**
 * 文件上传
 * @author live
 * @2019年12月16日 @下午6:23:47
 */
public class FileUploadUtil {
	private static final Logger log = LogManager.getLogger(FileUploadUtil.class);

	public static Result uploadFile(HttpServletRequest req, String targetPath, int count,long size) {
		log.info("文件上传...");

		String contentType = req.getContentType();
		if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
//	   MultipartHttpServ-letRequest multipartRequest =WebUtils.getNativeRequest(req, MultipartHttpServletRequest.class);
//	   MultipartFile file = multipartRequest.getFile("file");   

			try {
				Collection<Part> coll = req.getParts();
				if (coll != null && coll.size() > 0) {
					if (coll.size() > count) {
						return new Result(-1, "文件个数超过限制(个):" + count);
					}
				}

				String[] targetArray = new String[coll.size()];
				int i = 0;
				for (Part part : coll) {
					// 文件大小
					if (part.getSize() > size) {
						return new Result(-1, "文件大小超过限制(byte):" + size);
					}
				}

				for (Part part : coll) {
					targetArray[i++] = writeFile(targetPath, part);
				}
				return new Result(targetArray);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return new Result(-1, "服务器繁忙");
	}

	/**
	 * 将单个多媒体文件写入磁盘
	 * 
	 * @param filePath
	 * @param part
	 * @return
	 * @throws IOException
	 */
	private static String writeFile(String filePath, Part part) throws IOException {
		String target;
		part.getContentType();// image/png
		Collection<String> headerNames = part.getHeaderNames();
		Iterator<String> it = headerNames.iterator();
		while (it.hasNext()) {
			String headerName = it.next();
			String header = part.getHeader(headerName);
			System.out.println("headerName:" + headerName + ",header:" + header);
		}

		InputStream in = part.getInputStream();// java.io.ByteArrayInputStream
		log.info(part.getSubmittedFileName());
		log.info(part.getContentType());

		File basePath = new File(filePath);
		if (!basePath.exists()) {
			basePath.mkdirs();
		}

		String srcfileName = part.getSubmittedFileName();
		target = filePath + FileManager.wrapFileName(srcfileName);
		FileUtil.wrriteFile(in, target);
		return target;
	}
}
