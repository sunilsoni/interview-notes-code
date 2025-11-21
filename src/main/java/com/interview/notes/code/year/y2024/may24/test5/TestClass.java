package com.interview.notes.code.year.y2024.may24.test5;

public class TestClass {
    int test_a, test_b;

    public TestClass(int a, int b) {
        test_a = a;
        test_b = b;
    }

    public static void main(String[] args) {
        TestClass test = new TestClass(10, 20);
        // TestClass test = new TestClass();
        System.out.println(test.test_a + " " + test.test_b);
    }
}
