package com.live.test.api.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.live.test.api.core.config.ConfigUtil;

/**
 * Http
 * @author live
 */
public class HttpUtil implements java.io.Serializable {

	private static final long serialVersionUID = 6642294013532952509L;
	
	private static final int OK = 200; 
    private static final int ConnectionTimeout = ConfigUtil.getConnectionTimeout(1000);
    private static final int ReadTimeout = ConfigUtil.getReadTimeout();
    private static final String CHARSET_UTF_8 = "UTF-8";
    private static final String CHARSET_ISO_8859_1 = "iso-8859-1";
    private static final String _GET = "GET";
    private static final String _POST = "POST";

    /**
     * Get 请求
     *
     * @param url 请求地址
     * @return 输出流对象
     * @throws WeixinException
     */
	public String get(String url, String postData) {
		return httpRequest(url, _GET, null);
	}
    
  /**
   * post请求
   * @param url
   * @return
   */
	public static String post(String url, String param) {
		return httpRequest(url, _POST, param);
	}
	public static String post(String url, Map<String, String> params) {
		String param = "";
		Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			if (!param.equals(""))
				param = param.concat("&");
			param = param.concat(entry.getKey()).concat("=")
					.concat(entry != null ? entry.getValue() : "");
		}
		
		return httpRequest(url, _POST, param);
	}

    /**
     * 获取http请求连接
     *
     * @param url 连接地址
     * @return http连接对象
     * @throws IOException
     */
    private static HttpURLConnection getHttpURLConnection(String url) throws IOException {
        URL urlGet = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlGet.openConnection();
        return con;
    }

    /**
     * 通过http协议请求url
     *
     * @param url 提交地址
     * @param method 提交方式
     * @param param 提交数据
     * @return 响应流
     * @throws WeixinException
     */
    private static String httpRequest(String url, String method, String param){
    	if(ConfigUtil.isDebug()){
	    	System.out.println("\nurl:" + url);
	    	System.out.println("method:" + method);
	    	System.out.println("param:" + param);
    	}
    	String result = "";
        OutputStream out = null;
        HttpURLConnection http;
		try {
            //创建http请求连接
            http = getHttpURLConnection(url);
            if (http != null) {
                //设置通用性的Header信息
                setHttpHeader(http, method);
                //判断是否需要提交数据
                if (method.equals(_POST) && null != param) {
                    //将参数转换为字节提交
                    byte[] bytes = param.getBytes(CHARSET_UTF_8);
                    //设置其它Header信息
                    http.setRequestProperty("Content-Length", Integer.toString(bytes.length));
                  //开始连接
                    http.connect();
                    //输出参数
                    out = http.getOutputStream();
                    out.write(bytes);
                    out.flush();
                    out.close();
                } else {
                	//开始连接
                	http.connect();
                }
                
                //获取响应代码
                if (http.getResponseCode() == OK ) {
                	 InputStream in = http.getInputStream();
                     int i = in.read();
                     while (i != -1) {
                    	 result += (char) i;
                         i = in.read();
                     }
      
                     result = (new String(result.getBytes(CHARSET_ISO_8859_1), CHARSET_UTF_8));
                     in.close();
				} else {
					System.out.println("响应码：" + http.getResponseCode());
				}
            }
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
		if(ConfigUtil.isDebug()){
			System.out.println("result:" + result + "\n");
		}
        return result;
    }

    private static void setHttpHeader(HttpURLConnection httpUrlConnection, String method)
            throws IOException {
        //设置header信息
       //httpUrlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        //设置User-Agent信息
//        httpUrlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
    	//设置请求方式
    	httpUrlConnection.setRequestMethod(method);
    	//设置是否向httpUrlConnection输出, 默认情况下是false;   
        httpUrlConnection.setDoOutput(true);
        //设置是否从httpUrlConnection读入，默认情况下是true; 
        httpUrlConnection.setDoInput(true);
        //设置连接超时时间
        if (ConnectionTimeout > 0) {
            httpUrlConnection.setConnectTimeout(ConnectionTimeout);
        } else {
            //默认10秒超时
            httpUrlConnection.setConnectTimeout(10000);
        }
        //设置请求超时
        if (ReadTimeout > 0) {
            httpUrlConnection.setReadTimeout(ReadTimeout);
        } else {
            //默认10秒超时
            httpUrlConnection.setReadTimeout(10000);
        }
        //设置编码
        httpUrlConnection.setRequestProperty("Charsert", CHARSET_UTF_8);
    }
}
