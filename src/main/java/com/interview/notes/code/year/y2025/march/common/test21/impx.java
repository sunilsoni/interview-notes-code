package com.interview.notes.code.year.y2025.march.common.test21;

interface x {
    int p = 10; // implicitly public, static, final

    void pr(); // abstract method

    default void show() {
        System.out.println("A default method in interface");
    }
}

public class impx implements x {

    public static void main(String[] args) {
        x ob = new impx(); // using interface reference
        ob.pr();           // prints: Hello World
        ob.show();         // prints: A default method in interface
    }

    // Implementing the abstract method
    public void pr() {
        System.out.println("Hello World");
    }
}
