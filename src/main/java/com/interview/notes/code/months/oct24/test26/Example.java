package com.interview.notes.code.months.oct24.test26;

public class Example {
    static int x = 10; // 1. Static variable initialized first
    static int y;

    static {
        System.out.println("Static block 1"); // 2. Static blocks are executed in order of appearance
        y = x * 2;
    }

    static {
        System.out.println("Static block 2"); // 3. Next static block
    }

    public static void printValues() {
        System.out.println("Static method called"); // 4. Static methods execute when called
        System.out.println("x = " + x + ", y = " + y);
    }

    public static void main(String[] args) {
        Example.printValues(); // Explicit call to the static method
    }
}
