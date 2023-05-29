package com.interview.notes.code.test.test6;

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
    public void display() {
//Write your code here
        DI1.super.display();
        DI2.super.display();
    }

    public static void main(String args[]) {
        DemoClass obj = new DemoClass();
        obj.display();
    }
}
