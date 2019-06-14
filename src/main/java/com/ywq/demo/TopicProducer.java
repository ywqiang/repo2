package com.ywq.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProducer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.129:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        TextMessage textMessage = session.createTextMessage("欢迎学习消息队列");
        MessageProducer producer = session.createProducer(topic);
        producer.send(textMessage);
        producer.close();
        session.close();
        connection.close();
    }
}
