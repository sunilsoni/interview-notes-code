package com.interview.notes.code.months.july24.test3;

public class Test {
    // Constructor with no parameters
    Test() {
        this(5);  // Calls the constructor with one int parameter
        System.out.println("Constructor 1");
    }

    // Constructor with one int parameter
    Test(int x) {
        this(5, 5);  // Calls the constructor with two int parameters
        System.out.println(x);
    }

    // Constructor with two int parameters
    Test(int x, int y) {
        System.out.println(x * y);
    }

    public static void main(String[] args) {
        new Test();  // Instantiates the Test class
    }
}
