package com.interview.notes.code.year.y2025.october.common.test;

public class DeadlockFixedExample {
    private static final Object resource1 = "Resource1";
    private static final Object resource2 = "Resource2";

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1 locked Resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                synchronized (resource2) {
                    System.out.println("Thread 1 locked Resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource1) { // same order as t1
                System.out.println("Thread 2 locked Resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                synchronized (resource2) {
                    System.out.println("Thread 2 locked Resource 2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}