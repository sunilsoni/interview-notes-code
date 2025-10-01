package com.interview.notes.code.year.y2025.september.Hackerank.test4;

public class SwapNumbers {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;

        System.out.println("Before Swap: a=" + a + ", b=" + b);

        a = a + b; // a = 10 + 5 = 15
        b = a - b; // b = 15 - 5 = 10
        a = a - b; // a = 15 - 10 = 5

        System.out.println("After Swap: a=" + a + ", b=" + b);
    }
}