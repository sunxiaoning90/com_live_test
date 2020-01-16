package com.live.test.javaweb.core.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.live.test.javaweb.core.fileupload.util.FileUtil;


public class HttpClient {

	private static final Logger log = LoggerFactory.getLogger(HttpClient.class);
	
	public static String get(String urlStr) {
//		log.info("get请求开始。url：" + urlStr);
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(url== null){
			return null;
		}
		
		InputStream is = null;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setConnectTimeout(5000);
			
			urlConnection.connect();
			
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                line = new String(line.getBytes("UTF-8"));
                sb.append(line);
            }
//            log.info("result：" + sb.toString());
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
		
	}

	public static String post(String urlStr, String queryString) {
		log.info("post请求开始。url：" + urlStr + ",参数：" + queryString);
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(url== null){
			return null;
		}
		
		InputStream is = null;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("POST");
			urlConnection.setConnectTimeout(5000);
			urlConnection.setDoInput(true);
			urlConnection.setDoOutput(true);
			urlConnection.connect();
			OutputStream os = urlConnection.getOutputStream();
			os.write(queryString.getBytes());
			
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                line = new String(line.getBytes("UTF-8"));
                sb.append(line);
            }
            log.info("result：" + sb.toString());
			return sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	public static InputStream get_Input(String urlStr) {
//		log.info("get请求开始。url：" + urlStr);
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(url== null){
			return null;
		}
		
		InputStream is = null;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setConnectTimeout(5000);
			
			urlConnection.connect();
			
			InputStream in = urlConnection.getInputStream();
			return in;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
		
	}
	
	public static void main(String[] args) throws IOException {
		String urlStr = "http://xxx.png";
		InputStream r = HttpClient.get_Input(urlStr );
//		System.out.println(r);
		System.out.println(r.available());
		
		String pathname = "/opt/11.png";
		File file = new File(pathname);
		
		FileUtil.wrriteFile(r, file.getAbsolutePath());
		
		System.out.println(file.length());
		
		InputStream is = new FileInputStream(file);
		byte[] data = new byte[is.available()];
		is.read(data);
		is.close();
		// TODO 使用JDK8 Base64,待验证
		// return encoder.encode(data);
		String s = Base64.getEncoder().encodeToString(data);
		System.out.println(s.length());
		
		String txt = "/opt/base64.txt";
		FileUtil.write(txt, s, false);
		//System.out.println(s);
	}
}
