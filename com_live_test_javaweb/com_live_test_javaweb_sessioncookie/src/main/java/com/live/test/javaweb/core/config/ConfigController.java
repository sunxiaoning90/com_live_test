package com.live.test.javaweb.core.config;

/**
 * 用于手动修改配置文件后,浏览器调用刷新配置文件缓存
 */
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.live.test.javaweb.core.config.ConfigUtil;
import com.live.test.javaweb.core.fileupload.util.FileUploadUtil2;
import com.live.test.javaweb.core.util.HttpServletUtil;
import com.live.test.javaweb.core.util.LogUtil;

import net.sf.json.JSONObject;
@WebServlet(urlPatterns="/config/*")
public class ConfigController extends HttpServlet{

	private static final long serialVersionUID = -1988213166866263124L;
	private static final Logger log = LoggerFactory.getLogger(ConfigController.class);
	
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
	private void initConfig(){
	ConfigUtil.init();
	HttpServletUtil.res(true,request, response);
	}
}
