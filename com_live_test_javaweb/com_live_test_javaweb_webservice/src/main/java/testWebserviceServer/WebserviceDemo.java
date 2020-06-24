package testWebserviceServer;

import javax.jws.WebService;

@WebService
public class WebserviceDemo {
	
	public String hello() {
		return "hello";
	}
	
	public String helloToYou(String you) {
		return "hello" + you;
	}
	
	public static void main(String[] args) {
		String address = "http://localhost:8081/ws/hello";
		Object implementor = new WebserviceDemo();
		
		javax.xml.ws.Endpoint publish = javax.xml.ws.Endpoint.publish(address, implementor);
		System.out.println(publish);
	}
}
