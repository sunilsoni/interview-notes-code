package com.interview.notes.code.year.y2025.august.common.test2;

public class HelloGit {
    public static void main(String[] args) {
        final Object lock = new Object();  // shared lock for coordination

        // Thread that prints "Hello"
        Thread t1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.print("Hello ");
                    lock.notify();            // wake up t2
                    try {
                        lock.wait();
                    }      // wait for t2 to print
                    catch (InterruptedException ignored) {
                    }
                }
            }
        });

        // Thread that prints "Git"
        Thread t2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.println("Git");
                    lock.notify();            // wake up t1
                    try {
                        lock.wait();
                    }      // wait for t1 to print
                    catch (InterruptedException ignored) {
                    }
                }
            }
        });

        t1.start();  // start "Hello" thread first
        t2.start();  // then start "Git" thread
    }
}