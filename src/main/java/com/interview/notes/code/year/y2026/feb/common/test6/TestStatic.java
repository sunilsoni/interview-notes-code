package com.interview.notes.code.year.y2026.feb.common.test6;

public class TestStatic {

    public static void main(String[] args) {

        A a = new B();
        B b = new B();

        System.out.println(A.i);
        System.out.println(B.i);

        A.method();
        B.method();
    }

    static class A {
        static int i = 10;   // Static variable

        static void method() {  // Static method
            System.out.println("A");
        }
    }

    static class B extends A {
        static int i = 20;   // Static variable (hides A.i)

        static void method() {  // Static method (hides A.method)
            System.out.println("B");
        }
    }
}
