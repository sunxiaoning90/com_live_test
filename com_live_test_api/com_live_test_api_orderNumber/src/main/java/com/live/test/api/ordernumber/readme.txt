订单号生成
1.使用 java.util 
1.1 java.util.Random 类
	new Random().nextInt(size)
1.2 java.util.UUID 类
	UUID.randomUUID().toString();

2.snowflake (雪花算法)