package com.interview.notes.code.year.y2024.dec24.test4;

public class BooleanTest {
    private boolean flag;  // Using primitive boolean

    public static void main(String[] args) {
        System.out.println(new BooleanTest().getFlag());  // prints: false (default value of boolean)
    }

    private boolean getFlag() {
        return flag;  // returns the primitive boolean
    }
}
