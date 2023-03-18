/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.test;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer implements Runnable {

    public void run() {
        try { // Create a connection factory.
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            //Create connection.
            Connection connection = factory.createConnection();

            // Start the connection
            connection.start();

            // Create a session which is non transactional
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create Destination queue
            //queues seem to be evenly distributed amoung clients
            //topics, everyone gets a copy
            Destination queue = session.createTopic("topic1");

            // Create a producer
            MessageProducer producer = session.createProducer(queue);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            int counter = 0;
            while (true) {
                String msg = "Hello World " + counter++;

                // insert message
                TextMessage message = session.createTextMessage(msg);
                System.out.println("Producer Sent: " + msg);
                producer.send(message);
                Thread.sleep(1000);
            }
            //session.close();
            //connection.close();
        } catch (Exception ex) {
            System.out.println("Exception Occured");
        }
    }
}
