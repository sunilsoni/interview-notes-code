package com.interview.notes.code.months.year2023.Oct23.test3;

import java.util.Arrays;
import java.util.Scanner;

public class Anagram {

    public static boolean isAnagram(String str1, String str2) {
        // Convert both strings to lowercase.
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // Check if the strings have the same length.
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert both strings to character arrays.
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        // Sort both character arrays.
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // Check if the sorted character arrays are equal.
        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        // Create a scanner to read input from the user.
        Scanner scanner = new Scanner(System.in);

        // Get the size of the input strings from the user.
        System.out.println("Enter the size of the input strings: ");
        int size = scanner.nextInt();

        // Get the two input strings from the user.
        System.out.println("Enter the first string: ");
        String str1 = scanner.next();
        System.out.println("Enter the second string: ");
        String str2 = scanner.next();

        // Check if the two strings are anagrams of each other.
        boolean isAnagram = isAnagram(str1, str2);

        // Print the result to the user.
        if (isAnagram) {
            System.out.println(str1 + " and " + str2 + " are anagrams.");
        } else {
            System.out.println(str1 + " and " + str2 + " are not anagrams.");
        }

        // Close the scanner.
        scanner.close();
    }
}
