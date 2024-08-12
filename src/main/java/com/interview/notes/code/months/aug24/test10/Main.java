package com.interview.notes.code.months.aug24.test10;

public class Main {
    public static void main(String[] args) {
        int a = 0;
        System.out.println((~a == 0) ? "true" : "false"); // Prints "false"

        boolean b = false;
        System.out.println((b = true) ? "true" : "false"); // Prints "true"

        int c = 0;
        System.out.println((0 == ++c) ? "true" : "false"); // Prints "false"

        String e = "1";
        System.out.println(("1" != e) ? "true" : "false"); // Prints "false"

        Double d = null;
        System.out.println((d instanceof Double) ? "true" : "false"); // Prints "false"
    }
}
