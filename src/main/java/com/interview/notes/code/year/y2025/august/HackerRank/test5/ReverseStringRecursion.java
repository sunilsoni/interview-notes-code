package com.interview.notes.code.year.y2025.august.HackerRank.test5;

public class ReverseStringRecursion {

    // Recursive function to reverse a string
    public static String reverse(String str) {
        if (str == null || str.length() <= 1) {
            return str;  // Base case
        }
        // Recursive case: reverse the substring except the first char,
        // then add the first char at the end
        return reverse(str.substring(1)) + str.charAt(0);
    }

    public static void main(String[] args) {
        String input = "recursion";
        String output = reverse(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + output);
    }
}
