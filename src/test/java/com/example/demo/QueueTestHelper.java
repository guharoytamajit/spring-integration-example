package com.example.demo;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.DatatypeConverter;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueueConnectionFactory;

public class QueueTestHelper {
	public int tries = 0;
	boolean discardMessage = true;

	public void sendMessageOnQ(String env, String queueName, String msgText) throws JMSException {
		MQQueueConnectionFactory qconFactory = null;
		QueueConnection qcon = null;
		QueueSession qsession = null;
		QueueSender qsender = null;

		String hostName = null;
		int port = 0;
		String queueManager = null;
		String channel = null;
		switch (env) {
		case "SYS":
			hostName = "192.168.99.100";
			port = 1414;
			queueManager = "QM1";
			channel = "DEV.APP.SVRCONN";
			break;
		}

		try {
			qconFactory = new MQQueueConnectionFactory();
			qconFactory.setChannel(channel);
			qconFactory.setHostName(hostName);
			qconFactory.setPort(port);
			qconFactory.setQueueManager(queueManager);
			// qconFactory.setTransportType(3);
			qconFactory.setTransportType(com.ibm.mq.jms.JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
			System.out.println("attempting to connect with queue manager");
			qcon = qconFactory.createQueueConnection();
			qcon.start();
			System.out.println("Connected  with queue manager");
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			String correlationId = "1234567890";
			TextMessage msg = qsession.createTextMessage();
			msg.setText(msgText);
			msg.setJMSCorrelationIDAsBytes(DatatypeConverter.parseHexBinary(correlationId));
			Queue queue = qsession.createQueue("queue:///" + queueName + "?targetClient=1");
			qsender = qsession.createSender(queue);
			System.out.println("sending message to queue :" + queueName);
			qsender.send(msg);
			System.out.println("finished posting message");

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (qsender != null)
				qsender.close();
			if (qsession != null)
				qsession.close();
		}

	}

	public String readMessageFromQ(String env, String queueName) throws JMSException {
		MQQueueConnectionFactory qconFactory = null;
		QueueConnection qcon = null;
		QueueSession qsession = null;
		QueueSender qsender = null;

		String hostName = null;
		int port = 0;
		String queueManager = null;
		String channel = null;
		switch (env) {
		case "SYS":
			hostName = "192.168.99.100";
			port = 1414;
			queueManager = "QM1";
			channel = "DEV.APP.SVRCONN";
			break;
		}

		try {
			qconFactory = new MQQueueConnectionFactory();
			qconFactory.setChannel(channel);
			qconFactory.setHostName(hostName);
			qconFactory.setPort(port);
			qconFactory.setQueueManager(queueManager);
			// qconFactory.setTransportType(3);
			qconFactory.setTransportType(com.ibm.mq.jms.JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
			System.out.println("attempting to connect with queue manager");
			qcon = qconFactory.createQueueConnection();
			qcon.start();
			System.out.println("Connected  with queue manager");
			qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = qsession.createQueue("queue:///" + queueName + "?targetClient=1");

			MessageConsumer mc = qsession.createConsumer(queue);
			TextMessage receive = (TextMessage) mc.receive(1000);
			return receive.getText();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (qsender != null)
				qsender.close();
			if (qsession != null)
				qsession.close();
		}
		return null;
	}



	public void readMessageFromQ2(String env, String queueName) throws JMSException, InterruptedException {
		MQQueueConnectionFactory qconFactory = null;
		QueueConnection qcon = null;
		QueueSender qsender = null;
		QueueSession qsession=null;

		String hostName = null;
		int port = 0;
		String queueManager = null;
		String channel = null;
		switch (env) {
		case "SYS":
			hostName = "192.168.99.100";
			port = 1414;
			queueManager = "QM1";
			channel = "DEV.APP.SVRCONN";
			break;
		}

		try {
			qconFactory = new MQQueueConnectionFactory();
			qconFactory.setChannel(channel);
			qconFactory.setHostName(hostName);
			qconFactory.setPort(port);
			qconFactory.setQueueManager(queueManager);
			// qconFactory.setTransportType(3);
			qconFactory.setTransportType(com.ibm.mq.jms.JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
			System.out.println("attempting to connect with queue manager");
			qcon = qconFactory.createQueueConnection();

			System.out.println("Connected  with queue manager");
			 qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue = qsession.createQueue("queue:///" + queueName + "?targetClient=1");

			MessageConsumer mc = qsession.createConsumer(queue);
			mc.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message message) {
				
					throw new NullPointerException();
				

				}
			});
			qcon.start();
		
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
//			  if (qsender != null) qsender.close(); 
//			  if (qsession != null) qsession.close();
			 
		}

//		Thread.sleep(2000);

	}

	public void backoutReadTest(String env, String queueName, String backoutQueue, String message) throws Exception {
		// sending message to main queue
		sendMessageOnQ(env, queueName, message);
		//reading message from main queue this will fail
		readMessageFromQ2(env, queueName);
		String backoutQmessage = readMessageFromQ(env, backoutQueue);
		assertEquals(message, backoutQmessage);
//		assert message == backoutQmessage;
		
		System.out.println("received "+backoutQmessage);
	}


}
