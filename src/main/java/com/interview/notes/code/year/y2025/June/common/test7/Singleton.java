package com.interview.notes.code.year.y2025.June.common.test7;

public class Singleton {
    // Private constructor
    private Singleton() {
        // Protection against reflection
        if (SingletonHolder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }
    
    // Static holder class - thread safe by JVM guarantee
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    // Prevent cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning not allowed");
    }
    
    // Prevent serialization
    private Object readResolve() {
        return SingletonHolder.INSTANCE;
    }
}
