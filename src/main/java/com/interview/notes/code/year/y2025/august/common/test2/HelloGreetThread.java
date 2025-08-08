package com.interview.notes.code.year.y2025.august.common.test2;

public class HelloGreetThread {
    private static final Object lock = new Object();
    private static volatile boolean isHelloTurn = true;

    public static void main(String[] args) {
        Thread greetThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isHelloTurn) {  // Changed condition
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

        Thread helloThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isHelloTurn) {  // Changed condition
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

        // Start order doesn't matter now
        greetThread.start();
        helloThread.start();
    }
}
