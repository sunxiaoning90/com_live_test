package com.live.test.mq.rocketmq.example;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 生产者
 * 
 * @author live
 */
public class Producer {

	public static void main(String[] args) throws Exception {

		String namesrv = "192.168.1.52:9876";

		String producerGroup = "groupName_P1";
		String instanceName = "instanceName_P1";

		// 1、创建生产者
		DefaultMQProducer producer = new DefaultMQProducer();
		// 设置NameServ地址、生产者组、实例名
		producer.setNamesrvAddr(namesrv);
		producer.setProducerGroup(producerGroup);
		producer.setInstanceName(instanceName);

		// 2、启动
		producer.start();// 图-生产者
		// producer.createTopic("broker-a", "newTopic2", 1);//图-Topic，测试手动创建一个topic
		System.out.printf("消费者已启动：%s%n", Thread.currentThread().getStackTrace()[1].getClassName());

		// 3、发送消息
		for (int i = 0; i < 10; i++) {
			// Create a message instance, specifying topic, tag and message body.
			Message msg = new Message("RPC_Topic" /* Topic */, // TopicTest
					"TagH" /* Tag */, ("Hello RocketMQ " + i + " 更改broker消息存储时长为1小时,mq已重启")
							.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
			);
			// Call send message to deliver message to one of brokers.
			SendResult sendResult = producer.send(msg);
//			SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
//				
//				@Override
//				public MessageQueue select(List<MessageQueue> arg0, Message arg1, Object arg2) {
//					// TODO Auto-generated method stub
//					return null;
//				}
//			}, "id");
			System.out.printf(">>> 生产者发消息：%s%n", sendResult);
		}

		// 4、关闭生产者
//		producer.shutdown();
//		System.out.printf("生产者已关闭：%s%n", Thread.currentThread().getStackTrace()[1].getClassName());
	}
}
