package com.example.demo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test.xml" })
public class MockTest {

//	@Autowired
//	MessageChannel inputChannel;
//	@Autowired
//	SubscribableChannel outputChannel;
	
	@Autowired
	MyGateway myGateway;

	@Test
	public void test1() throws InterruptedException, ExecutionException {

		Future<String> method1 = myGateway.method1("hello2");
		System.out.println(method1.get());
	}

}
