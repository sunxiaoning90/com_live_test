package com.live.test.javase.core.collection;

import java.util.PriorityQueue;
import java.util.Queue;

public class TestQueue {
	public static void main(String[] args) throws InterruptedException {
		
		
//		BlockingQueue<Integer> queue = new PriorityBlockingQueue<>(2);
//
//		queue.put(9);
//		queue.put(1);
//		queue.put(8);
//		System.out.println("queue.size() = " + queue.size());
//		System.out.println("queue.take() = " + queue.take());
//		System.out.println("queue = " + queue);
		
//		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		Queue<String> queue = new PriorityQueue<String>(10);
		
		queue.add("a");
		queue.add("c");
		queue.add("d");
		
		System.out.println("queue.size() = " + queue.size());
		System.out.println("queue.take() = " + queue.poll());
		System.out.println("queue.take() = " + queue.poll());
		System.out.println("queue.take() = " + queue.poll());
		System.out.println("queue.take() = " + queue.poll());
		System.out.println("queue = " + queue);
		
	}
}
