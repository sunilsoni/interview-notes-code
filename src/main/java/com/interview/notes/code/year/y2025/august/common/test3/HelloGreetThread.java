package com.interview.notes.code.year.y2025.august.common.test3;

public class HelloGreetThread {
    private static final Object lock = new Object();
    private static volatile boolean isHelloTurn = true;

    public static void main(String[] args) {
        // Hello Thread
        Thread helloThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isHelloTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print("Hello ");
                    isHelloTurn = false;
                    lock.notify();
                }
            }
        });

        // Greet Thread
        Thread greetThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isHelloTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.println("Greet");
                    isHelloTurn = true;
                    lock.notify();
                }
            }
        });

        helloThread.start();
        greetThread.start();
    }
}
