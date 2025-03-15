package com.interview.notes.code.year.y2025.march.common.test17;

public class Test {

    static {
        System.out.println("static block");
    }

    {
        System.out.println("Instance block");
    }

    Test() {
        System.out.println("Constructor");
    }

    public static void main(String arg[]) {
        new Test().method10();
    }

    void method10() {
        System.out.println("Method");
    }
}