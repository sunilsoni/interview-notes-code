package com.interview.notes.code.year.y2025.may.common.test11;

public class MySingleton {
    // private constructor prevents external instantiation
    private MySingleton() {
    }

    // global access point
    public static MySingleton getInstance() {
        return Holder.INSTANCE;
    }

    // inner helper class holds the singleton instance
    private static class Holder {
        static final MySingleton INSTANCE = new MySingleton();
    }
}