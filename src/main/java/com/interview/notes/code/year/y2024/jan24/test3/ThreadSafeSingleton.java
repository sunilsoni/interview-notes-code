package com.interview.notes.code.year.y2024.jan24.test3;

public class ThreadSafeSingleton {

    // Private static instance to hold the single instance of the class
    private static ThreadSafeSingleton instance;

    // Private constructor to prevent instantiation from other classes
    private ThreadSafeSingleton() {
    }

    // Public method to get the single instance of the class
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    // Other methods and attributes of the Singleton class

    public void doSomething() {
        System.out.println("Singleton is doing something.");
    }
}

