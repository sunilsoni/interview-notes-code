package com.interview.notes.code.year.y2025.jan25.glider.test2;

public class Test {
    private int x;
    private int y;

    public Test() {
        this(10);
        System.out.println("Default Constructor");
    }

    public Test(int x) {
        this(x, 20);
        System.out.println("Single parameterized Constructor");
    }

    public Test(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Two- parameterized Constructor : x = " + x + " y = " + y);
    }

    public static void main(String[] args) {
        Test test = new Test();
    }
}
