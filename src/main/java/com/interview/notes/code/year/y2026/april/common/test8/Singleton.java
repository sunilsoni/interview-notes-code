package com.interview.notes.code.year.y2026.april.common.test8;

class Singleton {

    private static volatile Singleton instance; // ensures visibility across threads

    private Singleton() {} // private constructor prevents object creation

    public static Singleton getInstance() { // global access method
        if (instance == null) { // first check
            synchronized (Singleton.class) { // lock
                if (instance == null) { // second check
                    instance = new Singleton(); // create object once
                }
            }
        }
        return instance; // return single instance
    }
}