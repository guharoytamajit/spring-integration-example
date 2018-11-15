package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;

@SpringBootApplication
//@ImportResource("applicationContext.xml")
@EnableIntegration
public class SpringIntegrationExampleApplication implements CommandLineRunner {
	
	private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    
    private static final String ORDER_QUEUE = "order-queue";
//	@Autowired
//	MessageChannel inputChannel;
////	
//	@Autowired
//	SubscribableChannel outputChannel;
	
//	@Autowired
//	MyGateway gateway;

	public static void main(String[] args) {
		SpringApplication.run(SpringIntegrationExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		outputChannel.subscribe(message->System.out.println("received "+message.getPayload()));
//		outputChannel.subscribe(message->System.out.println("received "+message.getPayload()));
//		inputChannel.send(MessageBuilder.withPayload("Hello world").setHeader("replyChannel", "outputChannel").build());
//		System.out.println(gateway.method1("worl"));
//		System.out.println(gateway.method1("world"));
//		System.out.println(gateway.method1("worl"));
//		
//		
//		Thread.sleep(3000);
		
		
		
	}
	


 
    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
//        connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
        return connectionFactory;
    }
     
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
      /*  template.setDefaultDestinationName(ORDER_QUEUE);*/
        return template;
    }
}
