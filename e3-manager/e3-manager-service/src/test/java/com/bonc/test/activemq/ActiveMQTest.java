package com.bonc.test.activemq;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
//import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class ActiveMQTest {
	/**
	 *點到點發送消息
	 * @throws JMSException 
	 */
	@Test
	public void testQueueProducer() throws JMSException {
		//  1.建立一個連接工廠對象  指定服務的ip及端口
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.91.131:61616");
		// 2. 創建連接
		Connection connection = factory.createConnection();
		// 3.開啟連接
		connection.start();
		// 創建session(是否開啟事務,一般不開啟事物，如果開啟，第二個參數無意義 )
		// 第二個參數,應答模式（自動應答，還是手動應答） 一般是自動應答  
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 創建Destination對象  topic queue
		Queue queue = session.createQueue("test-queue");
		// 創建Producer
		MessageProducer producer = session.createProducer(queue);
		// 創建Message
//		TextMessage message = new ActiveMQTextMessage();
//		message.setText("hello activeMq");
		TextMessage message = session.createTextMessage("hello activeMq");
		// 發送消息
		producer.send(message);
		// 關閉資源
		producer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void testQueueConsumer() throws JMSException {
		//  1.建立一個連接工廠對象  指定服務的ip及端口
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.91.131:61616");
		// 2. 創建連接
		Connection connection = factory.createConnection();
		// 3.開啟連接
		connection.start();
		// 創建session(是否開啟事務,一般不開啟事物，如果開啟，第二個參數無意義 )
		// 第二個參數,應答模式（自動應答，還是手動應答） 一般是自動應答  
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// 創建Destination對象  topic queue
		Queue queue = session.createQueue("test-queue");
		// 創建consumer
		MessageConsumer consumer = session.createConsumer(queue);
		
//		Message receive = consumer.receive();
//		String text = ((TextMessage)receive).getText();
//		System.out.println(text);
		
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				String text;
				try {
					text = ((TextMessage)message).getText();
					System.out.println(text);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		try {
			// 等待
			System.in.read();
			// 關閉資源
			consumer.close();
			session.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * 測試  topic  沒有消費者消息會丟失
	 * @throws JMSException
	 */
	
	@Test
	public void testTopicProducer() throws JMSException {
		//  1.建立一個連接工廠對象  指定服務的ip及端口
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.91.131:61616");
		// 2. 創建連接
		Connection connection = factory.createConnection();
		// 3.開啟連接
		connection.start();
		// 創建session(是否開啟事務,一般不開啟事物，如果開啟，第二個參數無意義 )
		// 第二個參數,應答模式（自動應答，還是手動應答） 一般是自動應答  
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 創建Destination對象  topic queue
		 Topic topic = session.createTopic("item-change-topic");
		// 創建Producer
		MessageProducer producer = session.createProducer(topic);
		// 創建Message
//		TextMessage message = new ActiveMQTextMessage();
//		message.setText("hello activeMq");
		TextMessage message = session.createTextMessage("153890068995413");
		// 發送消息
		producer.send(message);
		// 關閉資源
		producer.close();
		session.close();
		connection.close();
	}
	
	@Test
	public void testTopicConsumer() throws JMSException {
		//  1.建立一個連接工廠對象  指定服務的ip及端口
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.91.131:61616");
		// 2. 創建連接
		Connection connection = factory.createConnection();
		// 3.開啟連接
		connection.start();
		// 創建session(是否開啟事務,一般不開啟事物，如果開啟，第二個參數無意義 )
		// 第二個參數,應答模式（自動應答，還是手動應答） 一般是自動應答  
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		// 創建Destination對象  topic queue
		Topic topic = session.createTopic("test-topic");
		// 創建consumer
		MessageConsumer consumer = session.createConsumer(topic);
		
//		Message receive = consumer.receive();
//		String text = ((TextMessage)receive).getText();
//		System.out.println(text);
		
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				String text;
				try {
					text = ((TextMessage)message).getText();
					System.out.println(text);
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
		try {
			// 等待
			System.in.read();
			// 關閉資源
			consumer.close();
			session.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	

}
