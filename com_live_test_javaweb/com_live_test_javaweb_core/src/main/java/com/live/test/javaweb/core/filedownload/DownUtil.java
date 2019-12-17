package com.live.test.javaweb.core.filedownload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.MarkerManager;

public class DownUtil {

	/**
	 * 文件下载,支持断点续传
	 * @param fileName
	 * @param in
	 * @param nLength
	 * @param start
	 * @param tLength
	 * @param length
	 * @param isRange
	 */
	public void sendAudio(String fileName,FileInputStream in,long nLength,int start,long tLength,long length,boolean isRange) {
//		if (postIntercept()) {
//			isCompleted = true;
//			try {
//				response.addHeader("Content-Disposition",
//						"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//				response.addHeader("Content-Length", nLength + "");
//				response.addHeader("Content-Range", "bytes " + start + "-" + (tLength-1) + "/" + length);
//				response.addHeader("Content-Type", "application/octet-stream;charset=UTF-8");
//				logger.info("HttpServletResponse返回给前台的状态:{}", HttpServletResponse.SC_PARTIAL_CONTENT);
//				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
//				// response.setCharacterEncoding("UTF-8");
//				OutputStream out = null;
//				
//				try {
//					int count = 0;
//					int percent = 163840;
//					int len = 0;
//					
//					byte buf[] = new byte[16384];// 缓存作用
//					out = response.getOutputStream();// 输出流
//					while ((len = in.read(buf)) > 0) {
//						count += len;
//						if(count>=start) {
//							out.write(buf, 0, len);// 向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
//						if(isRange) {
//							if(count >= start+percent)
//								break;
//							}
//						}
//					}
//				} finally {
//					if (in != null) {
//						try {
//							in.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//					if (out != null) {
//						try {
//							out.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			asyncContext.complete();
//		}
//		afterCompletionIntercept();
	}
	
	/**
	 * 文件下载,不支持断点续传
	 * @param fileName
	 * @param in
	 */
	public void sendFile(String fileName, InputStream in) {
//		if (postIntercept()) {
//			isCompleted = true;
//			try {
//				response.addHeader("Content-Disposition",
//						"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//				// response.setCharacterEncoding("UTF-8");
//				OutputStream out = null;
//				try {
//					int len = 0;
//					byte buf[] = new byte[1024];// 缓存作用
//					out = response.getOutputStream();// 输出流
//					while ((len = in.read(buf)) > 0) {
//						out.write(buf, 0, len);// 向客户端输出，实际是把数据存放在response中，然后web服务器再去response中读取
//					}
//				} finally {
//					if (in != null) {
//						try {
//							in.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//
//					if (out != null) {
//						try {
//							out.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			asyncContext.complete();
//		}
//		afterCompletionIntercept();
	}
}
