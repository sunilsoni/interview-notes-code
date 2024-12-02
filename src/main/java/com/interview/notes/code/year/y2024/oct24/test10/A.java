package com.interview.notes.code.year.y2024.oct24.test10;

public class A {
    public static void display() {
        System.out.println("A");
    }

    public static void main(String[] args) {
        A obj = new B();
        obj.display();
    }

    public static class B extends A {
        public static void display() {
            System.out.println("B");
        }
    }


}
