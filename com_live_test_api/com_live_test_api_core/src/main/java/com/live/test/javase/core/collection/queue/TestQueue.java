package com.live.test.javase.core.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Queue 篇
 * Queue
			|-AbstractQueue //抽象
			|-BlockingQueue //阻塞队列
			|-Deque //双端队列
			
 * @author live
 */
public class TestQueue {

	public static void main(String[] args) {
		
		BlockingQueue<String> q = new ArrayBlockingQueue<String>(2);
		
		BlockingQueue<String> q2 = new LinkedBlockingQueue<String>();
		
		Deque<String> q3 = new ArrayDeque<String>();
	}
	
	
}
