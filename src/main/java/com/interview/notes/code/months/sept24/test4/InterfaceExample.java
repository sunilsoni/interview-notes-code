package com.interview.notes.code.months.sept24.test4;

interface MyInterface1 {
    public static final int num = 100;

    default void display() {
        System.out.println("display method of MyInterface1");
    }
}

interface MyInterface2 {
    public static final int num = 1000;

    default void display() {
        System.out.println("display method of MyInterface2");
    }
}

public class InterfaceExample implements MyInterface1, MyInterface2 {
    public static void main(String[] args) {
        InterfaceExample obj = new InterfaceExample();
        obj.display();  // Calls both interface display methods
    }

    @Override
    public void display() {
        MyInterface1.super.display();  // Calls MyInterface1's display method
        MyInterface2.super.display();  // Calls MyInterface2's display method
    }
}
