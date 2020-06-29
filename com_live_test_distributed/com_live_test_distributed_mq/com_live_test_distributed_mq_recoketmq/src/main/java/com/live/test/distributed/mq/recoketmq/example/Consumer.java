package com.live.test.distributed.mq.recoketmq.example;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * 消费者
 * 
 * @author live
 */
public class Consumer {

	public static void main(String[] args) throws InterruptedException, MQClientException {

//		String namesrv = "192.168.10.27:9876";
		String namesrv = "192.168.1.52:9876";

		String topic = "RPC_Topic";
		String tag = "TagH";

		// 1、创建消费者
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
		consumer.setConsumerGroup("groupName_CH");
		// 设置Nameserv地址、订阅的Topic和Tag、消息类型（集群、广播两种）、实例名
		consumer.setNamesrvAddr(namesrv);
		consumer.subscribe(topic, tag);// TopicTest
		consumer.setMessageModel(MessageModel.CLUSTERING);
//		consumer.setInstanceName("instanceName_C1");

		// 2、设置监听事件（消费到消息时触发）
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.printf("\t%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);

				MessageExt messageExt = msgs.get(0);
				String topic = messageExt.getTopic();
				String tags = messageExt.getTags();
				String string = new String(messageExt.getBody(), Charset.defaultCharset());

				System.out.println("<<<<<消费者： topic: " + topic + "  tags: " + tags + "  message: " + string);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		// 3、 Launch the consumer instance.
		consumer.start();
		System.out.printf("消费者已启动：%s%n", Thread.currentThread().getStackTrace()[1].getClassName());

		Thread.sleep(10000);

		// consumer.shutdown();
		// System.out.printf("消费者已关闭：%s%n",
		// Thread.currentThread().getStackTrace()[1].getClassName());
	}
}
