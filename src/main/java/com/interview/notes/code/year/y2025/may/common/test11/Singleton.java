package com.interview.notes.code.year.y2025.may.common.test11;

public class Singleton {
    // Private constructor to prevent instantiation
    private Singleton() {
        // Constructor code here
    }

    // Public method to get the singleton instance
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // Other methods of the singleton class
    public void doSomething() {
        System.out.println("Singleton is doing something");
    }

    // Static holder class for lazy initialization
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
}
