package com.live.test.javaweb.core.qrcode.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.live.test.javaweb.core.fileupload.util.FileUploadUtil2;
import com.live.test.javaweb.core.util.HttpServletUtil;
import com.live.test.javaweb.core.util.LogUtil;

import net.sf.json.JSONObject;

/**
 * 上传文件,并响应给前端一个可以浏览器访问的url
 * 基于 fileUpload(Apache Commons FileUpload)
 * eg:{"code":0,"data":{"fileUrl":"http://192.168.1.53:8080/core-0.0.1-SNAPSHOT/imUpload/2019/12/17/dfd9570b-92bd-4bf3-833c-6a04bd6745e6_11.PNG"}}
 * @author live
 * @2019年12月17日 @下午2:30:37
 */
@WebServlet(urlPatterns="/qrcode/*")
public class QRCodeController extends HttpServlet{

	private static final long serialVersionUID = -1988213166866263124L;
	private static final Logger log = LoggerFactory.getLogger(QRCodeController.class);
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.execute(req, resp);
	}

	private void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.request = req;
		this.response = resp;
		
		String requestURI = req.getRequestURI();
		log.info("请求开始:" + requestURI);
		HttpServletUtil.printRequestInfo(req, resp);
		
		String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1, requestURI.lastIndexOf("."));
		try {
			Method method = this.getClass().getDeclaredMethod(methodName);
			method.invoke(this);
		} catch(Exception e){
			//FIXME 考虑加入返回错误码
			log.error("请求出现异常：" + requestURI);
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unused" })
	private void createQrcode(){
		log.info("*二维码");
		JSONObject json = new JSONObject();
		try {
			String fileUrl = FileUploadUtil2.fileUpload(this.request);
			System.out.println(fileUrl);
			json.put("code", 0L);
			JSONObject data = new JSONObject();
			data.put("url", fileUrl);
			json.put("data", data);
			log.info("文件url：" + fileUrl);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("文件上传异常", e);
		}finally{
//			PrintWriter writer = response.getWriter();
//			writer.write(fileUrl);
			HttpServletUtil.res(json,request, response);
			log.info("[文件上传]-" + LogUtil.getln());
		}
	}
	
}
