package com.interview.notes.code.year.y2023.Sep23;

public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        // Access the Singleton instance
        ThreadSafeSingleton singleton1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton singleton2 = ThreadSafeSingleton.getInstance();

        // Check if both references point to the same instance
        if (singleton1 == singleton2) {
            System.out.println("singleton1 and singleton2 are the same instance.");
        } else {
            System.out.println("singleton1 and singleton2 are different instances.");
        }
    }
}
