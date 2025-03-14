package com.interview.notes.code.year.y2025.march.common.test17;

public class Test {

    Test() {
        System.out.println("Constructor");
    }

    void method10() {
        System.out.println("Method");
    }

    {
        System.out.println("Instance block");
    }

    static {
        System.out.println("static block");
    }

    public static void main(String arg[]) {
        new Test().method10();
    }
}