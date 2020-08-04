package com.live.test.api.core.https;

	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



	public class HttpsClient {



		/**

		 * HttpClient的Post请求

		 */

		public static String post(String httpUrl, String param) {



			URL url = null;

			try {

				url = new URL(httpUrl);

			} catch (MalformedURLException e1) {

				e1.printStackTrace();

			}



			// ssl - 解决https证书问题

			System.out.println("--" + url.getProtocol());

			if ("https".equalsIgnoreCase(url.getProtocol())) {

				try {

					SslUtils.ignoreSsl();

				} catch (Exception e) {

					e.printStackTrace();

				}

			}



			HttpURLConnection urlCon = null;

			String result = "";

			try {

				urlCon = (HttpURLConnection) url.openConnection();

				urlCon.setDoInput(true);

				urlCon.setDoOutput(true);

				urlCon.setRequestMethod("POST");

				urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

				// 设置为utf8可以解决服务器接收时读取的数据中文乱码问题

				OutputStreamWriter osw = new OutputStreamWriter(urlCon.getOutputStream(), "UTF-8");

				osw.write(param);

				osw.flush();

				osw.close();

				BufferedReader in = new BufferedReader(new InputStreamReader(

						urlCon.getInputStream(), "UTF-8"));

				String line;

				while ((line = in.readLine()) != null) {

					result += line;

				}

				in.close();

			} catch (IOException e) {

				System.out.println("HttpClient的Post请求失败：" + e.getMessage());

			}

			return result;

		}

		
		 public static void main(String[] args) {
//		    	sendCustomMsg("35_S1UV8Sjopj-T26S_KJSWcgpW7Kjh8WX439f1jz9ilLBlEDa4-omEF5LbtuZJsgfu6PvtGyZup0E6vR5pJix16iQqAift4jT-3EDpzJlK8mEFnlsxv1cwZbCma5xXeukxsUd9Oud6uy7fOd6_DMKjAHAZIG", mpid, msg)
		    	
		    	String tk = "35_z_Pf6RDVAekXFYkIXRu6UlglflnxmmSBbeqWMebCK0bkcu2jTggom-oLzMBgZ7pJ2DOZfByjdQ22aRt3lehyZFyMMFUMtFlm2kNFN5HbYzrlKSU1zafpGv5B0RZVKxwonYzDkOmIkVIYrsSXWAUhAEAPTN";
				String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+tk;
		    	String m = "{\"touser\":\"o2GRC0r7hnTKI8h5fRu5ATT8Hts0\",\"msgtype\":\"text\",\"text\":{\"content\":\"我是000323号客服代表 小王 ,很高兴为您服务！ ^_^\"}}";
		    	 String result = HttpsClient.post(url, m);
		    	 System.out.println(result);
			}
	}


