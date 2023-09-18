package com.interview.notes.code.Aug23.test3;

public class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Private constructor prevents instantiation from other classes.
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        // Getting the Singleton instance
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();

        // Both instances should be the same
        System.out.println("Is singleton1 the same instance as singleton2? " + (singleton1 == singleton2));

        // Testing the method of the Singleton instance
        singleton1.showMessage();
        singleton2.showMessage();
    }

    public void showMessage() {
        System.out.println("Singleton instance is working.");
    }
}
