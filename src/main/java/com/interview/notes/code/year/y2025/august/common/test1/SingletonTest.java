package com.interview.notes.code.year.y2025.august.common.test1;

class Singleton {
    private static Singleton single_instance = null;  // single instance
    public String s;

    // private constructor
    private Singleton() {
        s = "Hello, I am part of the Singleton class";
    }

    // synchronized method for thread safety
    public static synchronized Singleton getInstance() {
        if (single_instance == null) {
            single_instance = new Singleton();
        }
        return single_instance;
    }
}

public class SingletonTest {
    public static void main(String[] args) {
        // Get the only object available
        Singleton x = Singleton.getInstance();
        Singleton y = Singleton.getInstance();
        Singleton z = Singleton.getInstance();

        // Print strings to verify same instance
        System.out.println("x hashCode = " + x.hashCode());
        System.out.println("y hashCode = " + y.hashCode());
        System.out.println("z hashCode = " + z.hashCode());

        // Change value using one reference
        x.s = x.s.toUpperCase();

        // All references reflect same change
        System.out.println("x.s = " + x.s);
        System.out.println("y.s = " + y.s);
        System.out.println("z.s = " + z.s);
    }
}
