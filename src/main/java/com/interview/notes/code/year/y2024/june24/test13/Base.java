package com.interview.notes.code.year.y2024.june24.test13;

public class Base {
    public void methodOne() {
        System.out.print("A");
        methodTwo();
    }

    public void methodTwo() {
        System.out.print("B");
    }
}


class Main {
    public static void main(String[] args) {
        Base b = new Derived();
        b.methodOne();
    }
}
