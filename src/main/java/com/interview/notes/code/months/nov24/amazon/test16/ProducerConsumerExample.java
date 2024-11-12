package com.interview.notes.code.months.nov24.amazon.test16;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {
    private static final int CAPACITY = 1;
    private static final Queue<Integer> queue = new LinkedList<>();
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Runnable producer = () -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock) {
                        while (queue.size() == CAPACITY) {
                            lock.wait();
                        }
                        queue.offer(i);
                        System.out.println("Produced: " + i);
                        lock.notifyAll();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 5; i++) {
                    int value;
                    synchronized (lock) {
                        while (queue.isEmpty()) {
                            lock.wait();
                        }
                        value = queue.poll();
                        System.out.println("Consumed: " + value);
                        lock.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
