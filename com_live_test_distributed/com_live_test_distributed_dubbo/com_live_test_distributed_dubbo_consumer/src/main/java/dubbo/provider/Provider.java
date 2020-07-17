package dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
	public static void main(String[] args) throws Exception {
//     ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"http://10.20.160.198/wiki/display/dubbo/provider.xml"});
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring/applicationContext.xml" });

		System.out.println("ac:" + context); // ac:org.springframework.context.support.ClassPathXmlApplicationContext@148080bb:
												// startup date [Fri Jul 17 15:22:51 CST 2020]; root of context
												// hierarchy
		context.start();
		System.in.read(); // 按任意键退出
		context.stop();
		System.out.println("dubbo stop");
	}
}
