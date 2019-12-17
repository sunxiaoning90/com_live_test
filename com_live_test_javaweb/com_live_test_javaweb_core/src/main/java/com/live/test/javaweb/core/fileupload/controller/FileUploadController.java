package com.live.test.javaweb.core.fileupload.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.live.test.javaweb.core.fileupload.FileManager;
import com.live.test.javaweb.core.fileupload.Result;
import com.live.test.javaweb.core.fileupload.util.FileUploadUtil;


/**
 * 文件上传
 * 基于 原生requset本身的getParts()实现 : Collection<Part> coll = req.getParts();
 * 2) ServletInputStream sin = req.getInputStream();
 * 响应给前端文件的绝对路径
 * @author live
 * @2019年12月12日 @下午12:30:43
 */
//@Controller(path = "/file/upload/")
@WebServlet("/file/upload")
@MultipartConfig
public class FileUploadController extends HttpServlet {

	private static final Logger log = LogManager.getLogger(FileUploadController.class);

	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.uploadHeadImage(request,response);
	}
	
	/**
	 * 上传头像
	 */
	public void uploadHeadImage(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("上传头像...");
		log.info("上传头像...");
		///
//		String targetPath = FileManager.getFileDir(FileManager.FILE_TYPE_HEADIMAGE, Helper.getDomain().getDomainIdentification());
		//保存目录
		String targetPath = FileManager.getFileDir(FileManager.FILE_TYPE_HEADIMAGE, "domain-live001");
		Result r = FileUploadUtil.uploadFile(request,targetPath,FileManager.FILE_UPLOAD_COUNT_MAX,FileManager.FILE_UPLOAD_SIZE_MAX);
		if(r !=null) {
			if(!r.isOk()) {
				outJsonResultMessage(request,response,false, "失败",r.getData(String.class));
			}
			String[] data = r.getData(String[].class);
			outJsonResultMessage(request,response,true, "成功",data[0]);
		}
	}
	
	public void outJsonResultMessage(HttpServletRequest request, HttpServletResponse response,boolean flag, String msg, String data) {
		try {
			//message = ContextParser.parseToHtml(message);
			//text = ContextParser.escape(text);

			JSONObject json = new JSONObject();
			json.put("code", flag ? 0 : -1);
			json.put("msg", msg);
			json.put("data", data);

			//Helper.getContext().sendString(json.toString());
			doResponse(request, response, json.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doResponse(HttpServletRequest request, HttpServletResponse response,String html) {
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			// response.setCharacterEncoding("UTF-8");
			OutputStream out = response.getOutputStream();
			out.write(html.getBytes("UTF-8"));
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
