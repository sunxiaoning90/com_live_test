package test;
import testWebserviceServer.WebserviceDemo;

public class ClientTest {
	public static void main(String[] args) {
		//1、根据webservice服务端的wsdl生成java代码
//		wsimport -s . -p client -keep http://localhost:8081/ws/hello?wsdl
//		正在解析 WSDL...
//		正在生成代码...
//		正在编译代码...

		//2、调用
		String r = new WebserviceDemo().helloToYou("1");
		System.out.println(r);
	}
}
