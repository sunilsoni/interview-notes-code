package com.interview.notes.code.year.y2025.June.common.test7;

public enum SingletonEnum {
    INSTANCE;
    
    // Your singleton state
    private final String data;
    
    // Constructor
    SingletonEnum() {
        this.data = "Singleton Data";
    }
    
    // Your singleton methods
    public String getData() {
        return data;
    }
    
    public void doSomething() {
        System.out.println("Singleton action");
    }
}
