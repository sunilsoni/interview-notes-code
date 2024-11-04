package com.interview.notes.code.months.nov24.test3;

public class DeadlockExample {

    // Define two resources
    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {
        // Create two threads
        Thread thread1 = new Thread(new Task1());
        Thread thread2 = new Thread(new Task2());

        // Start both threads
        thread1.start();
        thread2.start();
    }

    // Task that locks resource1 then tries to lock resource2
    static class Task1 implements Runnable {
        @Override
        public void run() {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding lock on Resource 1...");

                // Simulate some work with resource1
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                System.out.println("Thread 1: Waiting for Resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 1: Acquired lock on Resource 2!");
                }
            }
        }
    }

    // Task that locks resource2 then tries to lock resource1
    static class Task2 implements Runnable {
        @Override
        public void run() {
            synchronized (resource2) {
                System.out.println("Thread 2: Holding lock on Resource 2...");

                // Simulate some work with resource2
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                System.out.println("Thread 2: Waiting for Resource 1...");
                synchronized (resource1) {
                    System.out.println("Thread 2: Acquired lock on Resource 1!");
                }
            }
        }
    }
}
