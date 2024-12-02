package com.interview.notes.code.year.y2024.nov24.amazon.test16;

public class WaitNotifyExample {
    private static final Object lock = new Object();
    private static boolean condition = false;

    public static void main(String[] args) {
        Runnable waiter = () -> {
            synchronized (lock) {
                while (!condition) {
                    try {
                        System.out.println("Waiter is waiting...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.println("Waiter is done waiting.");
            }
        };

        Runnable notifier = () -> {
            try {
                Thread.sleep(2000); // Simulate some work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (lock) {
                condition = true;
                System.out.println("Notifier is notifying...");
                lock.notify();
            }
        };

        new Thread(waiter, "Waiter").start();
        new Thread(notifier, "Notifier").start();
    }
}
