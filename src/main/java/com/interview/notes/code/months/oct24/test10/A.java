package com.interview.notes.code.months.oct24.test10;

public class A {
    public static void display() {
        System.out.println("A");
    }

    public static class B extends A {
        public static void display() {
            System.out.println("B");
        }
    }

    public static void main(String[] args) {
        A obj = new B();
        obj.display();
    }


}
