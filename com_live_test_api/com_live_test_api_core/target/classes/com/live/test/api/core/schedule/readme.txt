一.调度的实现方式
1.基于 java.lang 的 Thread 类
	1.1 封装 java.lang.Thread
	
2.基于 java.util 的 Timer 类
	2.1 封装 java.util.Timer
	
3.使用 java.util.concurrent 的 ScheduledThreadPoolExecutor 类
	2.1 使用 java.util.concurrent.ScheduledThreadPoolExecutor
	
4.使用 quartz 框架


二.主要用线程处理任务

哪些需要处理？
1、删除客户资料后，删除关联的数据：联系记录、跟进记录等
2、公海回收策略
3、打包录音文件
4、上传、下载


调度
任务 trigger

调度管理
instance
put
remove

启动全部
停止全部