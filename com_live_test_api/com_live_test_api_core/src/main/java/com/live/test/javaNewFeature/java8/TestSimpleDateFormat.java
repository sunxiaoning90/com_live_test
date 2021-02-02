package com.live.test.javaNewFeature.java8;

/**
 * <pre>
 1、SimpleDateFormat 不是线程安全的，为什么？
SimpleDateFormat不是线程安全的，而且创建一个实例的开销是非常昂贵，解析字符串时间时频繁创建生命周期短暂的实例导致性能低下。

1）阅读JDK 注释
SimpleDateFormat的javadoc中有这么句话：
Synchronization Date formats are not synchronized. 
It is recommended to create separate format instances for each thread. 
If multiple threads access a format concurrently, it must be synchronized externally.


翻译一下：
日期格式是不同步的.
建议为每个线程创建独立的格式实例.
如果多线程并发访问同一个格式，则必须保持外部同步.
 
2）阅读 JDk 源码
2.1)有一个共享变量calendar，而这个共享变量的访问没有做到线程安全
2.2)当使用format方法时，实际是给calent共享变量设置date值，然后调用subFormat将date转化成字符串
 
2、SimpleDateFormat 不是线程安全的，解决方案
方案1、创建一个共享的SimpleDateFormat实例变量，但是在使用的时候，需要对这个变量进行同步。
方案2、需要的时候创建局部变量。
方案3、使用ThreadLocal为每个线程都创建一个线程独享SimpleDateFormat变量。
方案4、选用其它替代品，如 DateTimeFormatter。
 * </pre>
 * 
 * @author live
 */
public class TestSimpleDateFormat {

}
