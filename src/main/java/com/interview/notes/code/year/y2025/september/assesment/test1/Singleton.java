package com.interview.notes.code.year.y2025.september.assesment.test1;

// Singleton using Bill Pugh Inner Static Helper Class
public class Singleton {

    // private constructor prevents instantiation
    private Singleton() {
        System.out.println("Singleton instance created");
    }

    // global access point
    public static Singleton getInstance() {
        return Holder.INSTANCE;
    }

    // test
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        s1.showMessage();
        System.out.println("Both objects are same? " + (s1 == s2)); // true
    }

    // sample method
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }

    // static inner class holds the single instance
    private static class Holder {
        private static final Singleton INSTANCE = new Singleton();
    }
}