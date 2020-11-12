一、Servlet 与 Spring IOC

1、尝试 Servlet 与 Spring IOC 结合
1）尝试 web环境中获取WebApplicationContext
Spring提供结合web：WebApplicationContextUtils类

		ServletContext sc = request.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(sc);