package com.interview.notes.code.year.y2025.may.common.test10;

public class Singleton {
    // The volatile keyword ensures that multiple threads handle the instance variable correctly
    private static volatile Singleton instance;
    
    // Private constructor to prevent instantiation
    private Singleton() {
    }
    
    public static Singleton getInstance() {
        // First check (no synchronization)
        if (instance == null) {
            // Synchronize only if instance is null
            synchronized (Singleton.class) {
                // Second check (with synchronization)
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
