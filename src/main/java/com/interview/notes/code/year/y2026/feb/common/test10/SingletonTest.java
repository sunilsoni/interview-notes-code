package com.interview.notes.code.year.y2026.feb.common.test10;

public class SingletonTest {

    public static void main(String[] args) {

        Runnable task = () -> {
            ThreadSafeSingleton obj = ThreadSafeSingleton.getInstance(); // Get instance
            System.out.println(Thread.currentThread().getName() + 
                               " -> " + obj.hashCode()); // Print object identity
        };

        // Creating multiple threads
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}