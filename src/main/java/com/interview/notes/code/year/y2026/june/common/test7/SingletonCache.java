package com.interview.notes.code.year.y2026.june.common.test7;

public class SingletonCache {
    // Volatile is crucial for memory visibility
    private static volatile SingletonCache instance;

    private SingletonCache() {}

    public static SingletonCache getInstance() {
        if (instance == null) { // First check (no locking)
            synchronized (SingletonCache.class) {
                if (instance == null) { // Second check (locking)
                    instance = new SingletonCache();
                }
            }
        }
        return instance;
    }
}