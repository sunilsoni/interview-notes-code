package com.interview.notes.code.year.y2025.september.common.test1;

public class SwapWithoutTemp {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        
        System.out.println("Before swapping:");
        System.out.println("a = " + a + ", b = " + b);
        
        a = a + b; // a = 30 (10+20)
        b = a - b; // b = 10 (30-20)
        a = a - b; // a = 20 (30-10)
        
        System.out.println("After swapping:");
        System.out.println("a = " + a + ", b = " + b);
    }
}
