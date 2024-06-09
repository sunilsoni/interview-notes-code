package com.interview.notes.code.months.june24.test2;

public class SwapNumbers {
    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        // Swap without using a temporary variable
        a = a + b; // a now becomes 15
        b = a - b; // b now becomes 5
        a = a - b; // a now becomes 10

        System.out.println("After swapping: a = " + a + ", b = " + b);

        System.out.println("HELLO  *********");
    }
}
