package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BackoutQueueTest {
	private String env;
	private String queueName;
	private String backoutQueue;
	private String message;
	public BackoutQueueTest(String env, String queueName, String backoutQueue, String message) {
		super();
		this.env = env;
		this.queueName = queueName;
		this.backoutQueue = backoutQueue;
		this.message = message;
	}

	@Parameterized.Parameters
	public static Collection primeNumbers() {
		List list = new ArrayList();
		try {
			ClassLoader classLoader = BackoutQueueTest.class.getClassLoader();
			BufferedReader bufferedReader = new BufferedReader(
					new FileReader(new File(classLoader.getResource("input.csv").getFile())));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				list.add(line.split(","));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Test
	public void testPrimeNumberChecker() throws Exception {
		System.out.println(env + "  " + queueName + "  " + backoutQueue + "  " + message);
		QueueTestHelper qth = new QueueTestHelper();
	//	qth.backoutReadTest("SYS", "DEV.QUEUE.2", "DEV.QUEUE.1", "hello world");
	}
}
