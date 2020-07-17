Dubbo开发笔记：consumer

*我的github源代码地址： 
    https://github.com/sunxiaoning90/com_live_test/

*我的csdn地址：
    https://blog.csdn.net/Sunxn1991/article/details/105811427
    
    
 一、demo
 1、配置Spring.xml
 1)在dtd beans标签添加dubbo支持： 
 
 xmlns:dubbo="http://dubbo.apache.org/schema/dubbo"
 
 xsi:schemaLocation=
 http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
		http://dubbo.apache.org/schema/dubbo
		http://dubbo.apache.org/schema/dubbo/dubbo.xsd">