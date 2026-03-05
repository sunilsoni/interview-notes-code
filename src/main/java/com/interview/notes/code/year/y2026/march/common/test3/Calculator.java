package com.interview.notes.code.year.y2026.march.common.test3;

public class Calculator {

    public static void main(String[] args) {
        Calculator myCalc = new Calculator();

        // The compiler knows exactly which 'add' method to use based on the inputs!
        System.out.println(myCalc.add(5, 10));          // Calls the first method
        System.out.println(myCalc.add(5, 10, 15));      // Calls the second method
        System.out.println(myCalc.add(2.5, 3.5));       // Calls the third method
    }

    // Functionality 1: Add two integers
    public int add(int a, int b) {
        System.out.println("Adding two integers:");
        return a + b;
    }

    // Functionality 2: Add three integers
    public int add(int a, int b, int c) {
        System.out.println("Adding three integers:");
        return a + b + c;
    }

    // Functionality 3: Add two decimals (doubles)
    public double add(double a, double b) {
        System.out.println("Adding two decimals:");
        return a + b;
    }
}