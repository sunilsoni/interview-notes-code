package com.interview.notes.code.year.y2025.october.common.test3;

public class ParallelThreadsDemo {

    public static void main(String[] args) {

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is running");
        };

        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(task, "Thread-" + (i + 1));
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All threads finished execution");
    }
}