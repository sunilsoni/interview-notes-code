package com.interview.notes.code.year.y2024.july24.test12;

import java.util.Optional;

public class FirstNonRepeatingCharacter {
    public static char findFirst(String input) {
        if (input == null || input.isEmpty()) {
            return '\0'; // Return null character for empty or null input
        }

        // Use an array to count character occurrences (extended ASCII)
        int[] charCount = new int[Character.MAX_VALUE + 1];
        char[] chars = input.toCharArray();

        // First pass: Count occurrences of each character
        for (char c : chars) {
            charCount[c]++;
        }

        // Second pass: Find the first character with count 1
        for (char c : chars) {
            if (charCount[c] == 1) {
                return c;
            }
        }

        // If no non-repeating character found
        return '\0';
    }

    public static Optional<Character> firstNonRepeatingCharacter(String str) {
        if (str == null || str.isEmpty()) {
            return Optional.empty();
        }

        int[] charCounts = new int[65536]; // Cover all possible char values

        // First pass: count occurrences
        for (char c : str.toCharArray()) {
            charCounts[c]++;
        }

        // Second pass: find first non-repeating character
        for (char c : str.toCharArray()) {
            if (charCounts[c] == 1) {
                return Optional.of(c);
            }
        }

        return Optional.empty(); // No non-repeating character found
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(findFirst("aabcccdeef")); // Expected: 'b'
        System.out.println(findFirst("abcdefg")); // Expected: 'a'
        System.out.println(findFirst("aabbcc")); // Expected: '\0'
        System.out.println(findFirst("")); // Expected: '\0'
        System.out.println(findFirst(null)); // Expected: '\0'
        System.out.println(findFirst("a\u00A9b\u00A9c")); // Expected: 'a' (handles extended ASCII)
        System.out.println(findFirst("abcABC123")); // Expected: 'a' (case-sensitive)
        System.out.println(findFirst("a\\u")); // Expected: '\0'


        Optional<Character> result1 = firstNonRepeatingCharacter("111\0");
        Optional<Character> result2 = firstNonRepeatingCharacter("aaa");

        if (result1.isPresent()) {
            System.out.println("First non-repeating character in '111\\0': " + result1.get());
        } else {
            System.out.println("No non-repeating character found in '111\\0'");
        }

        if (result2.isPresent()) {
            System.out.println("First non-repeating character in 'aaa': " + result2.get());
        } else {
            System.out.println("No non-repeating character found in 'aaa'");
        }
    }
}

