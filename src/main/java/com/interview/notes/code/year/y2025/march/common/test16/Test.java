package com.interview.notes.code.year.y2025.march.common.test16;

public class Test {

    Test() {
        System.out.println("Constructor"); // Corrected sysout to System.out.println
    }

    void method10() {
        System.out.println("Method"); // Corrected sysout to System.out.println
    }

    {
        System.out.println("Instance block"); // Corrected sysout to System.out.println
    }

    static {
        System.out.println("static block"); // Corrected sysout to System.out.println
    }

    public static void main(String arg[]) {
        System.out.println("Hello world"); // Corrected sysout to System.out.println
        new Test().method10();
    }
}