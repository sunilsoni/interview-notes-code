package com.interview.notes.code.year.y2024.nov24.test12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        // Creating shared BlockingQueue
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

        // Create producer and consumer threads
        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        // Start both threads
        producerThread.start();
        consumerThread.start();
    }
}

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Producing: " + i);
                queue.put(i);
                Thread.sleep(1000); // Simulate some work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer value = queue.take();
                System.out.println("Consuming: " + value);
                Thread.sleep(2000); // Simulate some work
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
