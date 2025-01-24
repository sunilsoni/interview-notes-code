package com.interview.notes.code.year.y2025.jan24.glider.test1;

public class Test {
    public static void main(String[] args) {
        int result = testMethod(10, 0);
        System.out.println("Result " + result);
    }

    public static int testMethod(int a, int b) {
        int c = 0;
        try {
            c = a / b;
            return c;
        } catch (ArithmeticException e) {
            System.out.println("Caught Exception " + e.getMessage());
            return -1;
        } finally {
            System.out.println("Finally block executed");
        }
    }
}
