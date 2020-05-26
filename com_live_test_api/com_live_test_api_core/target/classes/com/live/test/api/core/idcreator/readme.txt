idcreator(ID/订单号生成器)
1.使用 java.util
设计思路:
IDManager:设计一个id管理类,该类提供 getNext()方法,用于获取全局唯一id
IDManager类中设计一个具体的ID生成器,用于生成id,采用 策略设计模式,改变此ID生成器即可轻松改变全局id的生成机制

用法示例:IDManager.getInstance().getNext()

1.1 java.util.Random 类
	new Random().nextInt(size)
1.2 java.util.UUID 类
	UUID.randomUUID().toString();
1.3 格式化日期对象date

2.snowflake (雪花算法)