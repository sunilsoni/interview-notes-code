package com.interview.notes.code.year.y2026.jan.common.test7;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Singleton {

    private static volatile Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // -------- STRESS TEST --------
    public static void main(String[] args) throws Exception {
        Set<Singleton> set = ConcurrentHashMap.newKeySet();

        Runnable task = () -> set.add(Singleton.getInstance());

        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println(set.size() == 1
                ? "PASS: Only one instance"
                : "FAIL: Instances created = " + set.size());
    }
}
