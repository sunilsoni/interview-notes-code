package com.interview.notes.code.misc;

public class PalindromeChecker {

    public static boolean isPalindrome(String input) {
        String reverse = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reverse += input.charAt(i);
        }
        return input.equals(reverse);
    }

    public static void main(String[] args) {
        String[] inputs = {"racecar", "anna", "speed", "code"};
        for (String input : inputs) {
            boolean isPalindrome = isPalindrome(input);
            System.out.println(input + " => " + isPalindrome);
        }
    }
}
