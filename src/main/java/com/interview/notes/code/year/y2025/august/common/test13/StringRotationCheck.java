package com.interview.notes.code.year.y2025.august.common.test13;

public class StringRotationCheck {

    // Method to check if s2 is a rotation of s1
    public static boolean isRotation(String s1, String s2) {
        // If lengths are not equal, cannot be rotation
        if (s1.length() != s2.length()) {
            return false;
        }
        // Concatenate s1 with itself
        String doubled = s1 + s1;
        // Check if s2 is a substring of doubled
        return doubled.contains(s2);
    }

    public static void main(String[] args) {
        // Test cases
        String s1 = "geet";
        String s2 = "tgee";
        String s3 = "etge";
        String s4 = "gete";
        String s5 = "gee"; // shorter string

        System.out.println(s2 + " -> " + isRotation(s1, s2)); // true
        System.out.println(s3 + " -> " + isRotation(s1, s3)); // true
        System.out.println(s4 + " -> " + isRotation(s1, s4)); // false
        System.out.println(s5 + " -> " + isRotation(s1, s5)); // false
    }
}