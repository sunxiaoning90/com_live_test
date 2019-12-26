package com.live.test.javaweb.core.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class HttpServletUtil {
	private static final Logger log = LoggerFactory.getLogger(HttpServletUtil.class);

	/**
	 * 打印请求信息
	 */
	public static String printRequestInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject client = new JSONObject();
		client.put("RemoteAddr", request.getRemoteAddr());
		client.put("RemotePort", request.getRemotePort());
		client.put("RemoteHost", request.getRemoteHost());
		client.put("RemoteUser", request.getRemoteUser());

		JSONObject server = new JSONObject();
		server.put("LocalAddr", request.getLocalAddr());
		server.put("LocalPort", request.getLocalPort());
		server.put("LocalName", request.getLocalName());

		JSONObject requestInfo = new JSONObject();
		JSONObject attrs = new JSONObject();
		Enumeration<String> attrNames = request.getAttributeNames();
		while (attrNames.hasMoreElements()) {
			String attrName = attrNames.nextElement();
			attrs.put(attrName, request.getParameter(attrName));
		}

		JSONObject requestParams = new JSONObject();
		Iterator<Entry<String, String[]>> it = request.getParameterMap().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> entry = it.next();
			requestParams.put(entry.getKey(), entry.getValue().length == 1 ? entry.getValue()[0] : entry.getValue());
		}

		requestInfo.put("requestURI", request.getRequestURI());
		requestInfo.put("Params", requestParams);
		requestInfo.put("Attributes", attrs);

		JSONObject json = new JSONObject();
		json.put("RequestInfo", requestInfo);
		json.put("Client", client);
		json.put("Server", server);

		return json.toString();
//		log.info("服务端/客户端信息：" + json.toString());
	}

	public static void res(Object data, HttpServletRequest request, HttpServletResponse response) {
		// response.setCharacterEncoding("gbk");
		// 尝试获取 请求域中的“callbackName”参数值
		String callbackName = request.getParameter("callbackName");
		if (callbackName != null && !"".equals(callbackName)) {
			data = callbackName + "(" + data + ")";
		}
		try {
			response.getWriter().print(data);
		} catch (IOException e) {
			log.error("响应异常", e);
		} finally {
//			if(Boolean.valueOf(ConfigUtil.getProperty("global.api.debug"))){
			if(true){
				log.info("响应结束：" + data);
			}
		}

	}

	/**
	 * 判断是否请求的静态资源
	 * TODO 待补充
	 * @param request
	 * @return
	 */
	public static boolean isStaticResource(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return uri.lastIndexOf(".css") > -1 || uri.lastIndexOf(".js")> -1 || uri.lastIndexOf(".png") > -1;
	}
}
