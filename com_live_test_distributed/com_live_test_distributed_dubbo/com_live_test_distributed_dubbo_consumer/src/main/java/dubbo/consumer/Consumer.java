package dubbo.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dubbo.provider.service.DemoService;

public class Consumer {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				new String[] { "http://10.20.160.198/wiki/display/dubbo/consumer.xml" });
				new String[] { "spring/applicationContext.xml" });
		System.out.println("ac:" + context);
		context.start();

		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		System.out.println("demoService:" + demoService); // demoService:org.apache.dubbo.common.bytecode.proxy0@26a529dc
		String hello = demoService.sayHello("world"); // 执行远程方法
		System.out.println("RPC结果："+hello); // 调用结果

		context.close();
	}
}
