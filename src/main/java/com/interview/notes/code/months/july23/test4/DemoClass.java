package com.interview.notes.code.months.july23.test4;

interface DI1 {
    public default void display() {
        System.out.println("DI1");
    }

}

interface DI2 {
    public default void display() {

        System.out.println("DI2");
    }

}

public class DemoClass implements DI1, DI2 {
    public static void main(String args[]) {

        DemoClass obj = new DemoClass();

        obj.display();

    }

    public void display() {
        // Call the display() method from DI1 interface
        DI1.super.display();
    }

}

