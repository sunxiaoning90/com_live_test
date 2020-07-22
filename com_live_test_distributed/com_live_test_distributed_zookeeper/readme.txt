Dubbo开发笔记：provider,服务提供者

*我的github源代码地址： 

*我的csdn地址：
一、zk简介
1、一句话总结ZK是什么？
简单来说zookeeper=文件系统+监听通知机制。
ZooKeeper是分布式协调服务，注册服务和发现，树形结构，监听机制，过半机制。
ZooKeeper是源代码开放的分布式协调服务，由雅虎公司创建，是Google Chubby（主要解决分布式锁相关问题）的开源实现。ZooKeeper是一个高性能的分布式一致性解决方案，它将那些复杂的、容易出错的分布式一致性服务封装起来，构成一个高效可靠的原语集，并提供一系列简单易用的接口给用户使用。
2、文件系统
Zookeeper维护一个类似文件系统的数据结构：
每个子目录项都被称作为 znode(目录节点)，和文件系统一样，我们能够自由的增加、删除znode，在一个znode下增加、删除子znode，唯一的不同在于znode是可以存储数据的。

有四种类型的znode：

1）PERSISTENT-持久化目录节点
客户端与zookeeper断开连接后，该节点依旧存在

2）PERSISTENT_SEQUENTIAL-持久化顺序编号目录节点
客户端与zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号
3）EPHEMERAL-临时目录节点
客户端与zookeeper断开连接后，该节点被删除
4）EPHEMERAL_SEQUENTIAL-临时顺序编号目录节点
客户端与zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号

2、 监听通知机制
客户端注册监听它关心的目录节点，当目录节点发生变化（数据改变、被删除、子目录节点增加删除）时，zookeeper会通知客户端。
就这么简单.

3、Zookeeper能做什么
zookeeper功能非常强大，可以实现诸如分布式应用配置管理、统一命名服务、状态同步服务、集群管理等功能，我们这里拿比较简单的分布式应用配置管理为例来说明。

假设我们的程序是分布式部署在多台机器上，如果我们要改变程序的配置文件，需要逐台机器去修改，非常麻烦，现在把这些配置全部放到zookeeper上去，保存在 zookeeper 的某个目录节点中，然后所有相关应用程序对这个目录节点进行监听，一旦配置信息发生变化，每个应用程序就会收到 zookeeper 的通知，然后从 zookeeper 获取新的配置信息应用到系统中。



来源： https://blog.csdn.net/java_66666/article/details/81015302


二、zk安装
《Zookeeper单机模式安装》
Step1：配置JAVA环境，检验环境：java -version
Step2：下载并解压zookeeper
# cd /usr/local
# wget http://mirror.bit.edu.cn/apache/zookeeper/stable/zookeeper-3.6.1.tar.gz
# tar -zxvf zookeeper-3.6.1.tar.gz
# cd zookeeper-3.6.1

Step3：重命名配置文件zoo_sample.cfg
# cp conf/zoo_sample.cfg conf/zoo.cfg

Step4：启动zookeeper
# bin/zkServer.sh start

Step5：检测是否成功启动，用zookeeper客户端连接下服务端
# bin/zkCli.sh

《ZooKeeper server端常见命令》
1、启动zk
[root@localhost apache-zookeeper-3.6.1-bin]# sh bin/zkServer.sh start
ZooKeeper JMX enabled by default
Using config: /opt/zk/apache-zookeeper-3.6.1-bin/bin/../conf/zoo.cfg
Starting zookeeper ... STARTED
2、查看zk状态（查看单机ZooKeeper是leader还是follower)
[root@localhost apache-zookeeper-3.6.1-bin]# sh bin/zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /opt/zk/apache-zookeeper-3.6.1-bin/bin/../conf/zoo.cfg
Client port found: 2181. Client address: localhost.
Mode: standalone
3、停止zk
[root@localhost apache-zookeeper-3.6.1-bin]# bin/zkServer.sh stop   
ZooKeeper JMX enabled by default
Using config: /opt/zk/apache-zookeeper-3.6.1-bin/bin/../conf/zoo.cfg
Stopping zookeeper ... STOPPED

《ZooKeeper Client常用命令》
1、创建节点：create 
[zk: localhost:2181(CONNECTED) 81] create /zk_test my_data
Created /zk_test
2、查看：ls
[zk: localhost:2181(CONNECTED) 83] ls /
[zk_test, zookeeper]
3、get
[zk: localhost:2181(CONNECTED) 84] get /zk_test
my_data
4、set
[zk: localhost:2181(CONNECTED) 85] set /zk_test junk
5、delete 删除节点
[zk: localhost:2181(CONNECTED) 6] delete /test1
6、 deleteall 删除非空节点
三、使用Java API操作zookeeper
zookeeper 实现分布式配置中心：