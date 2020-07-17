ZooKeeper入门指南
入门：使用ZooKeeper协调分布式应用程序
先决条件
下载
独立运行
管理ZooKeeper存储
连接到ZooKeeper
编程到ZooKeeper
运行复制的ZooKeeper
其他优化

入门：使用ZooKeeper协调分布式应用程序
本文档包含使您快速开始使用ZooKeeper的信息。它主要针对希望尝试的开发人员，并包含单个ZooKeeper服务器的简单安装说明，一些验证其正在运行的命令以及一个简单的编程示例。最后，为方便起见，有几节涉及更复杂的安装，例如，运行复制的部署以及优化事务日志。但是，有关商业部署的完整说明，请参阅《ZooKeeper管理员指南》。


先决条件
请参阅管理指南中的系统要求。


下载
要获取ZooKeeper发行版，请从Apache下载镜像之一下载最新的稳定版本。


独立运行
在独立模式下设置ZooKeeper服务器非常简单。服务器包含在单个JAR文件中，因此安装包括创建配置。

下载稳定的ZooKeeper版本后，将其解压缩并CD到根目录

要启动ZooKeeper，您需要一个配置文件。这是一个示例，在conf / zoo.cfg中创建它：

tickTime=2000
dataDir=/var/lib/zookeeper
clientPort=2181
该文件可以被命名为任何文件，但是为了便于讨论，将其称为conf / zoo.cfg。更改dataDir的值以指定现有（空开头）目录。以下是每个字段的含义：

tickTime：ZooKeeper使用的基本时间单位（毫秒）。它用于做心跳，并且最小会话超时将是tickTime的两倍。

dataDir：存储内存数据库快照的位置，除非另有说明，否则存储数据库更新的事务日志。

clientPort：用于侦听客户端连接的端口

创建配置文件后，就可以启动ZooKeeper：

bin/zkServer.sh start
ZooKeeper使用log4j记录消息- 程序员指南的“ 记录”部分中提供了更多详细信息。您将看到进入控制台的日志消息（默认）和/或一个日志文件，具体取决于log4j配置。

此处概述的步骤以独立模式运行ZooKeeper。没有复制，因此，如果ZooKeeper进程失败，该服务将关闭。这对于大多数开发情况都很好，但是要以复制方式运行ZooKeeper，请参阅“ 运行复制的ZooKeeper”。


管理ZooKeeper存储
对于长时间运行的生产系统，必须从外部管理ZooKeeper存储（dataDir和日志）。有关更多详细信息，请参见维护部分。


连接到ZooKeeper
$ bin/zkCli.sh -server 127.0.0.1:2181
这使您可以执行简单的类似文件的操作。

连接后，您应该会看到类似以下内容的信息：

Connecting to localhost:2181
log4j:WARN No appenders could be found for logger (org.apache.zookeeper.ZooKeeper).
log4j:WARN Please initialize the log4j system properly.
Welcome to ZooKeeper!
JLine support is enabled
[zkshell: 0]
在shell中，键入help以获取可以从客户端执行的命令的列表，如下所示：

[zkshell: 0] help
ZooKeeper -server host:port cmd args
addauth scheme auth
close
config [-c] [-w] [-s]
connect host:port
create [-s] [-e] [-c] [-t ttl] path [data] [acl]
delete [-v version] path
deleteall path
delquota [-n|-b] path
get [-s] [-w] path
getAcl [-s] path
getAllChildrenNumber path
getEphemerals path
history
listquota path
ls [-s] [-w] [-R] path
ls2 path [watch]
printwatches on|off
quit
reconfig [-s] [-v version] [[-file path] | [-members serverID=host:port1:port2;port3[,...]*]] | [-add serverId=host:port1:port2;port3[,...]]* [-remove serverId[,...]*]
redo cmdno
removewatches path [-c|-d|-a] [-l]
rmr path
set [-s] [-v version] path data
setAcl [-s] [-v version] [-R] path acl
setquota -n|-b val path
stat [-w] path
sync path
从这里，您可以尝试一些简单的命令，以了解这种简单的命令行界面。首先，首先发出list命令，如中所示ls，产生：

[zkshell: 8] ls /
[zookeeper]
接下来，通过运行创建一个新的znode create /zk_test my_data。这将创建一个新的znode并将字符串“ my_data”与该节点关联。您应该看到：

[zkshell: 9] create /zk_test my_data
Created /zk_test
发出另一个ls /命令以查看目录的外观：

[zkshell: 11] ls /
[zookeeper, zk_test]
请注意，zk_test目录现已创建。

接下来，通过运行以下get命令验证数据是否与znode关联：

[zkshell: 12] get /zk_test
my_data
cZxid = 5
ctime = Fri Jun 05 13:57:06 PDT 2009
mZxid = 5
mtime = Fri Jun 05 13:57:06 PDT 2009
pZxid = 5
cversion = 0
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0
dataLength = 7
numChildren = 0
我们可以通过发出set命令来更改与zk_test相关的数据，如下所示：

[zkshell: 14] set /zk_test junk
cZxid = 5
ctime = Fri Jun 05 13:57:06 PDT 2009
mZxid = 6
mtime = Fri Jun 05 14:01:52 PDT 2009
pZxid = 5
cversion = 0
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0
dataLength = 4
numChildren = 0
[zkshell: 15] get /zk_test
junk
cZxid = 5
ctime = Fri Jun 05 13:57:06 PDT 2009
mZxid = 6
mtime = Fri Jun 05 14:01:52 PDT 2009
pZxid = 5
cversion = 0
dataVersion = 1
aclVersion = 0
ephemeralOwner = 0
dataLength = 4
numChildren = 0
（请注意，我们get在设置数据后做了一个，确实确实发生了变化。

最后，delete通过发出以下命令来创建节点：

[zkshell: 16] delete /zk_test
[zkshell: 17] ls /
[zookeeper]
[zkshell: 18]
现在就这样。要了解更多信息，请参见Zookeeper CLI。


编程到ZooKeeper
ZooKeeper具有Java绑定和C绑定。它们在功能上是等效的。C绑定存在两种变体：单线程和多线程。这些区别仅在于消息传递循环的完成方式。有关更多信息，请参见《ZooKeeper程序员指南》中的“编程示例”，以获取使用不同API的示例代码。


运行复制的ZooKeeper
以独立模式运行ZooKeeper便于评估，某些开发和测试。但是在生产中，您应该在复制模式下运行ZooKeeper。同一应用程序中的一组服务器复制称为仲裁，并且在复制模式下，仲裁中的所有服务器都具有相同配置文件的副本。

注意
对于复制模式，至少需要三台服务器，强烈建议您使用奇数个服务器。如果只有两台服务器，那么您将处于一种情况，如果其中一台服务器发生故障，则没有足够的计算机构成多数仲裁。由于存在两个单点故障，因此两个服务器本来就不如单个服务器稳定。

复制模式所需的conf / zoo.cfg文件类似于独立模式下使用的文件，但有一些区别。这是一个例子：

tickTime=2000
dataDir=/var/lib/zookeeper
clientPort=2181
initLimit=5
syncLimit=2
server.1=zoo1:2888:3888
server.2=zoo2:2888:3888
server.3=zoo3:2888:3888
新条目initLimit是超时ZooKeeper用于限制仲裁中的ZooKeeper服务器必须连接到领导者的时间长度。条目syncLimit限制了服务器与领导者之间过时的距离。

对于这两个超时，您都可以使用tickTime指定时间单位。在此示例中，initLimit的超时是5个滴答声，即2000毫秒/滴答声，即10秒。

表格server.X的条目列出了组成ZooKeeper服务的服务器。服务器启动时，通过在数据目录中查找文件myid可以知道它是哪台服务器。该文件包含ASCII的服务器号。

最后，记下每个服务器名称后的两个端口号：“ 2888”和“ 3888”。对等方使用前一个端口连接到其他对等方。这种连接是必需的，以便对等方可以进行通信，例如，以商定更新顺序。更具体地说，ZooKeeper服务器使用此端口将关注者连接到领导者。当出现新的领导者时，跟随者使用此端口打开与领导者的TCP连接。由于默认的领导者选举也使用TCP，因此我们当前需要另一个端口来进行领导者选举。这是服务器条目中的第二个端口。

注意
如果要在单台计算机上测试多个服务器，请为每个服务器指定服务器名称为localhost，并具有唯一的仲裁和领导者选择端口（例如，在上面的示例中为2888：3888、2889：3889、2890：3890）。配置文件。当然，还需要单独的_dataDir_s和不同的_clientPort_s（在上面的复制示例中，在单个localhost上运行，您仍然会有三个配置文件）。

请注意，在一台计算机上设置多个服务器不会产生任何冗余。如果发生某些事情导致机器死机，则所有zookeeper服务器都将处于脱机状态。完全冗余要求每个服务器都有自己的计算机。它必须是完全独立的物理服务器。同一物理主机上的多个虚拟机仍然容易受到该主机完全故障的影响。

如果ZooKeeper机器中有多个网络接口，则还可以指示ZooKeeper绑定所有接口，并在网络出现故障时自动切换到正常接口。有关详细信息，请参阅“ 配置参数”。


其他优化
还有几个其他配置参数可以大大提高性能：

为了获得较低的更新延迟，拥有专用的事务日志目录非常重要。默认情况下，事务日志与数据快照和myid文件放在同一目录中。dataLogDir参数指示用于事务日志的其他目录。