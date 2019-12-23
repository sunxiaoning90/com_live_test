package com.live.test.javaweb.core.fileupload.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.live.test.javaweb.core.config.ConfigUtil;
import com.live.test.javaweb.core.qrcode.QRCodeUtil;

/**
 * 基于 fileUpload(Apache Commons FileUpload)
 * @author live
 * @2019年12月17日 @下午12:28:30
 */
public class FileUploadUtil2 {

	private static final Logger log = LoggerFactory.getLogger(FileUploadUtil2.class);
	final static DateFormat DF = new SimpleDateFormat("yyyy/MM/dd");
	private static final String 	DIR_BASE = "imUpload";

	/**
	 * 上传文件（暂时只支持单个文件）
	 * @return 
	 * @return void
	 */
	public static String fileUpload(HttpServletRequest request) throws FileUploadException, Exception {
		// 检测是否为多媒体内容
		if (!ServletFileUpload.isMultipartContent(request)) {
			log.error("不是多媒体上传");
			return null;
		}

		FileItemFactory fileItemFactory = new DiskFileItemFactory();

		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
		fileUpload.setHeaderEncoding("UTF-8");//字符编码
		fileUpload.setFileSizeMax(ConfigUtil.getFileuploadFilemax());
		fileUpload.setSizeMax(ConfigUtil.getFileuploadFilesmax());
		
		//获取FileIteam list
		List<FileItem> fileItemList = fileUpload.parseRequest(request);
		//遍历处理
		String rootDir = request.getSession().getServletContext().getRealPath(DIR_BASE );
		for (FileItem fileItem : fileItemList) {
			if (!fileItem.isFormField()) {
				String fileName = fileItem.getName();
				log.info("当前处理文件:" + fileItem);
				
				//准备一个空的磁盘文件
				File savedFile = getSavedFile(rootDir, fileName);
				//写入
				fileItem.write(savedFile);
				
				///
				String from = savedFile.getAbsolutePath();
				System.out.println("from:" + from);
				String to = "/opt/qrcode/to/qrcode-" + fileName;
				to = savedFile.getParent() + "/qrcode-" + savedFile.getName();
				System.out.println("to:" + to);
				
				// 生成二维码
				QRCodeUtil.encode("hello", from, to, true);
				savedFile = new File(to); 
				///
				
				//获取文件的url
				return getSavedFileUrl(request,savedFile);
			} else {
				log.info("暂不做处理，该字段为表单字段");
			}
		}
		return null;
	}

	/**
	 * 获取欲保存的文件
	 * @return File
	 */
	private static File getSavedFile(String rootDir, String fileName) {
		//fileName = fileName.endsWith(".amr")?fileName.substring(0,fileName.lastIndexOf(".amr")) + ".mp3" : fileName;
		//生成硬盘目录
		String dateStr = DF.format(new Date());
		String dirStr = String.format("%s/%s", rootDir, dateStr);
		File dir = new File(dirStr);
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		//读写权限
		dir.setReadable(true, false);
		dir.setWritable(true, false);
		
		//生成硬盘文件
		String uuid = UUID.randomUUID().toString();
		String fileStr = String.format("%s/%s_%s", dir, uuid, fileName);
		File file = new File(fileStr);
		file.setReadable(true, false);
		file.setWritable(true, false);
		return file;
	}
	
	/**
	 * 获取已经保存的文件的url
	 * @return File
	 * @throws UnsupportedEncodingException 
	 */
	private static String getSavedFileUrl(HttpServletRequest request, File file){
		log.debug("文件绝对路径：" + file.getAbsolutePath());

		String absolutePath = file.getAbsolutePath();
		String filePath = absolutePath.substring(absolutePath.indexOf(DIR_BASE));
		String url = String.format("%s://%s:%s%s/%s", 
				request.getScheme(),
				request.getServerName(), request.getServerPort(),
				request.getContextPath(),
//				 URLEncoder.encode(filePath)
				filePath
				);
		return url;
	}
	
	/**
	 * 下载
	 * @param filePath
	 * @param response
	 * @param isOnLine//是否在线打开
	 * @return void
	 * @throws
	 * @eg:
	 */
	public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());// 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0){
            out.write(buf, 0, len);
        }
        //资源应该在finally中关闭。
        	br.close();
        	out.close();
    }
	
	public static void downLoadFromUrl(String urlStr, HttpServletResponse response, boolean isOnLine) {

		response.reset(); // 非常重要
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + urlStr);

		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}

		try {
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			ServletOutputStream fs = response.getOutputStream();

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
