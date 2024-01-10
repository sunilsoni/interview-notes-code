package com.interview.notes.code.months.year2023.june23.test10;

public class ThreadExample {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            // Task for t1
            System.out.println("Thread t1 is running");
        });

        Thread t2 = new Thread(() -> {
            try {
                // Wait for t1 to complete
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Task for t2
            System.out.println("Thread t2 is running");
        });

        Thread t3 = new Thread(() -> {
            try {
                // Wait for t2 to complete
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Task for t3
            System.out.println("Thread t3 is running");
        });

        // Start the threads
        t1.start();
        t2.start();
        t3.start();
    }
}
