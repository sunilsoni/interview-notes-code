package com.interview.notes.code.year.y2026.feb.common.test7;

public class TestPolymorphism {

    public static void main(String[] args) {

        A a = new B();  // Parent reference, child object
        B b = new B();  // Child reference, child object

        System.out.println(a.i);  // Access variable using parent reference
        System.out.println(b.i);  // Access variable using child reference

        a.method();  // Method call using parent reference
        b.method();  // Method call using child reference
    }

    // Parent class
    static class A {
        int i = 10;  // Variable in class A

        void method() {  // Method in class A
            System.out.println("A");
        }
    }

    // Child class
    static class B extends A {
        int i = 20;  // Variable in class B (hides A.i)

        @Override
        void method() {  // Method overriding
            System.out.println("B");
        }
    }
}
