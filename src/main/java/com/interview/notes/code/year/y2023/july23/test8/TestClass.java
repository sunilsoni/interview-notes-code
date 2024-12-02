package com.interview.notes.code.year.y2023.july23.test8;

public class TestClass {
    public static void main(String[] args) {

        /*
        Ambiguous method call. Both
test
(String)
in TestClass and
test
(Integer)
in TestClass match
         */
        // test(null);
    }

    public static void test(String s) {
        System.out.println("String");
    }

    public static void test(Integer s) {
        System.out.println("String");
    }

    public static void test(int s) {
        System.out.println("String");
    }
}
