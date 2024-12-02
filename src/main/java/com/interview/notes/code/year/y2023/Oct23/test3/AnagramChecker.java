package com.interview.notes.code.year.y2023.Oct23.test3;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Program on anagram with input parameter will be take from user like size
 * <p>
 * Anagram example:
 * LISTEN = SILENT
 */
public class AnagramChecker {
    public static void main(String[] args) {
        // Initialize scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Read the size of the first string from the user
        System.out.print("Enter the size of the first string: ");
        int size1 = scanner.nextInt();

        // Read the first string from the user
        System.out.print("Enter the first string: ");
        String str1 = scanner.next();

        // Validate if the entered size matches with string length
        if (str1.length() != size1) {
            System.out.println("Size doesn't match with the length of the entered string.");
            return;
        }

        // Read the size of the second string from the user
        System.out.print("Enter the size of the second string: ");
        int size2 = scanner.nextInt();

        // Read the second string from the user
        System.out.print("Enter the second string: ");
        String str2 = scanner.next();

        // Validate if the entered size matches with string length
        if (str2.length() != size2) {
            System.out.println("Size doesn't match with the length of the entered string.");
            return;
        }

        // Close the scanner
        scanner.close();

        // Check if strings are anagrams
        if (areAnagrams(str1, str2)) {
            System.out.println("The strings are anagrams.");
        } else {
            System.out.println("The strings are not anagrams.");
        }
    }

    // Function to check if two strings are anagrams
    public static boolean areAnagrams(String str1, String str2) {
        // Check the lengths, if they're not equal, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to character arrays
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        // Sort the character arrays
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        // Compare sorted strings
        return Arrays.equals(chars1, chars2);
    }
}
