一、ID生成器 简介
1、ID生成器 简介
二、ID生成器 方案
1、ID生成器 方案 有哪些？
方案1：UUID 类（java.util.UUID）
            详见： 

方案2：日期格式化（eg：java.text.SimpleDateFormat）
            详见： 

方案3：日期格式化（eg：java.text.SimpleDateFormat） + UUID 类（java.util.UUID）
            详见： 

方案4：SnowFlake（雪花算法，by Twitter公司）
            详见： 

方案5：Tinyid（by 滴滴）
            详见： 
2、对比
三、设计一套 ID生成器管理
设计思路:
IDManager:设计一个id管理类,该类提供 getNext()方法,用于获取全局唯一id
IDManager类中设计一个具体的ID生成器（组合模式）,用于生成id,采用 策略设计模式,改变此ID生成器即可轻松改变全局id的生成机制
用法示例:IDManager.getInstance().getNext()
#参 考：
1、笔记内链
2、在线资料
3、离线资料
1）