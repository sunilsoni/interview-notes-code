package com.interview.notes.code.year.y2025.april.Artech.test1;

public class Solution {

    public static String solve(int x) {
        if (x % 2 == 0) {
            return "John";
        } else {
            return "Kate";
        }
    }

    public static void main(String[] args) {
        // Test Cases
        System.out.println(test(1, "Kate"));
        System.out.println(test(2, "John"));
        System.out.println(test(3, "Kate"));
        System.out.println(test(4, "John"));
        System.out.println(test(999999999, "Kate"));
        System.out.println(test(1000000000, "John"));
    }

    private static String test(int input, String expected) {
        String result = solve(input);
        return (result.equals(expected) ? "PASS" : "FAIL") + " for input: " + input + " Expected: " + expected + " Got: " + result;
    }
}
