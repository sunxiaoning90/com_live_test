package dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext.xml" });

		System.out.println("ac:" + context);
		context.start();
		
		System.in.read(); // 按任意键退出
		context.stop();
		System.out.println("dubbo stop");
	}
}
