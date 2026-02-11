package com.interview.notes.code.year.y2026.feb.common.test3;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionDemo {

    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementAndGet();   // thread-safe
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
        System.out.println("Actual  : " + counter.get());
    }
}
