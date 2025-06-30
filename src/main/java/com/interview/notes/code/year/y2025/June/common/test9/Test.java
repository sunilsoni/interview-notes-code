package com.interview.notes.code.year.y2025.June.common.test9;

public class Test {
    public static void main(String[] args) {
        Object o = new String[] {"a", "b", "c"};
        for (String s : (String[]) o) {
            System.out.print(s); // Expected output: abc
        }
    }
}
