/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.test;

import java.util.UUID;

/**
 *
 * @author Dad
 */
public class Activemqtest {

    public static void main(String[] args) {
        Producer producer = new Producer();

        for (int i = 0; i < 4; i++) {
            Consumer consumer = new Consumer(UUID.randomUUID().toString());
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
        }
        Thread producerThread = new Thread(producer);
        producerThread.start();

    }
}
