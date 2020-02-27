package com.live.test.mq.rocketmq.example2;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

public class Consumer2 {

	public static void main(String[] args) throws InterruptedException, MQClientException {

		// Instantiate with specified consumer group name.
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("groupName_C1");

		// Specify name server addresses.
		consumer.setNamesrvAddr("192.168.10.29:9876");
//		consumer.setInstanceName("instanceName_C2");
		// Subscribe one more more topics to consume.
		consumer.subscribe("TopicTest", "TagB");

		consumer.setMessageModel(MessageModel.CLUSTERING);

		// Register callback to execute on arrival of messages fetched from brokers.
		consumer.registerMessageListener(new MessageListenerConcurrently() {

			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.printf("\t%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);

				MessageExt messageExt = msgs.get(0);
				String topic = messageExt.getTopic();
				String tags = messageExt.getTags();
				String string = new String(messageExt.getBody(), Charset.defaultCharset());

				System.out.println("<<<<<消费者2： topic: " + topic + "  tags: " + tags + "  message: " + string);

				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		// Launch the consumer instance.
		consumer.start();
		System.out.printf("消费者已启动：%s%n",Thread.currentThread().getStackTrace()[1].getClassName());
		
		Thread.sleep(10000);
		
		consumer.shutdown();
		System.out.printf("消费者已关闭：%s%n",Thread.currentThread().getStackTrace()[1].getClassName());
	}
}
