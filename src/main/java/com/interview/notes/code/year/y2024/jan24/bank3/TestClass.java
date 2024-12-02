package com.interview.notes.code.year.y2024.jan24.bank3;

abstract class Test {
    static int i = 102;

    static void TestMethod() {
        System.out.println("hi!! I am good !!"); // Fixed println statement
    }
}

public class TestClass extends Test {
    public static void main(String args[]) {
        Test.TestMethod();
        System.out.println("i = " + Test.i);
    }
}
