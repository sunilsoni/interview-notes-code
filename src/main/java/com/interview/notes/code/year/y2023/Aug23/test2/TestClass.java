package com.interview.notes.code.year.y2023.Aug23.test2;

abstract class Test {
    static int i = 102;

    static void TestMethodf() {
        System.out.println("hi!! I am good ");
    }
}

public class TestClass extends Test {
    public static void main(String args[]) {
        Test.TestMethodf();
        System.out.println(i + " " + Test.i);
    }
}