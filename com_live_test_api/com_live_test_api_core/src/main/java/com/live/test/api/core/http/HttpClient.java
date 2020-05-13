package com.live.test.api.core.http;

import static com.live.test.api.core.io.IOUtil.coverInputStremToString;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/**
 * http工具类
 * @author live
 */
public class HttpClient {
//	private static final Logger log = LoggerFactory.getLogger(HttpClient.class);

	/**
	 * get请求
	 * 
	 * @param urlStr
	 * @return
	 */
	public static String get(String urlStr) {
		System.out.println("请求方式：get,	url:" + urlStr);

		InputStream in = getToInputStream(urlStr);
		if (in == null) {
			return null;
		}

		String r = coverInputStremToString(in);
		System.out.println("result:" + r);
		return r;
	}

	/**
	 * post请求
	 * 
	 * @param urlStr
	 * @return
	 */
	public static String post(String urlStr) {
		return coverInputStremToString(postForInputs(urlStr, null));
	}

	/**
	 * post请求，可携带参数
	 * 
	 * @param urlStr
	 * @param queryString
	 * @return
	 */
	public static String post(String urlStr, String queryString) {
		return coverInputStremToString(postForInputs(urlStr, queryString));
	}

	/**
	 * post 请求
	 * 
	 * @param urlStr
	 * @param queryString
	 * @return
	 */
	private static InputStream postForInputs(String urlStr, String queryString) {
		System.out.println("请求方式：post,	url:" + urlStr + ",	参数：" + queryString);
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (url == null) {
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
			if (queryString != null) {
				os.write(queryString.getBytes());
			}

			return urlConnection.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	/**
	 * get 请求
	 * 
	 * @param urlStr
	 * @return
	 */
	public static InputStream getToInputStream(String urlStr) {
//		log.info("get请求开始。url：" + urlStr);
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (url == null) {
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
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;

	}

//	public static void main(String[] args) throws IOException {
//		String urlStr = "http://xxx.png";
//		InputStream r = HttpClient.getInputStream(urlStr);
////		System.out.println(r);
//		System.out.println(r.available());
//
//		String pathname = "/opt/11.png";
//		File file = new File(pathname);
//
//		FileUtil.wrriteFile(r, file.getAbsolutePath());
//
//		System.out.println(file.length());
//
//		InputStream is = new FileInputStream(file);
//		byte[] data = new byte[is.available()];
//		is.read(data);
//		is.close();
//		// TODO 使用JDK8 Base64,待验证
//		// return encoder.encode(data);
//		String s = Base64.getEncoder().encodeToString(data);
//		System.out.println(s.length());
//
//		String txt = "/opt/base64.txt";
//		FileUtil.write(txt, s, false);
//		// System.out.println(s);
//	}

	public static void main(String[] args) {
		String urlStr = "https://www.google.com";
		String r = HttpClient.get(urlStr);
		System.out.println(r);
		r = HttpClient.post(urlStr);
		System.out.println(r);
	}
}
