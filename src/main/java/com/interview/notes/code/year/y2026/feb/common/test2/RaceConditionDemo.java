package com.interview.notes.code.year.y2026.feb.common.test2;

public class RaceConditionDemo {

    static int counter = 0;

    public static void main(String[] args) throws Exception {

        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter++;  // Not thread-safe
            }
        };

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Expected: 1000000");
        System.out.println("Actual  : " + counter);
    }
}
