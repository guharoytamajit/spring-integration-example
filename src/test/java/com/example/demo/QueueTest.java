package com.example.demo;

import javax.jms.JMSException;

public class QueueTest {

	public void testQ_SYS(){
		
	}
	
	public static void main(String[] args) throws Exception {
QueueTestHelper qth = new QueueTestHelper();
//qth.sendMessageOnQ("SYS", "DEV.QUEUE.2", "hello4");
//qth.readMessageFromQ("SYS", "DEV.QUEUE.2");
qth.backoutReadTest("SYS", "DEV.QUEUE.2", "DEV.QUEUE.1", "hello world");
	}
}
