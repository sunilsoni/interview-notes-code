package com.interview.notes.code.year.y2023.dec23.test3;

abstract class Test {
    static int i = 102;

    static void TestMethod() {
        System.out.println("hi!! I am good !!");
    }
}

public class TestClass extends Test {
    public static void main(String[] args) {
        Test.TestMethod();
        System.out.println("i = " + Test.i);
    }
}
/**
 * hi!! I am good !!
 * i = 102
 */