package com.interview.notes.code.months.year2023.july23.test5;

public class RotationString {
    public static void main(String[] args) {
        String x = "abcd";
        String y = "bcda";

        System.out.println(isRotation(x, y));
    }

    public static boolean isRotation(String x, String y) {
        if (x.length() != y.length()) {
            return false;
        }

        String concatenated = x + x;
        return concatenated.contains(y);
    }
}

