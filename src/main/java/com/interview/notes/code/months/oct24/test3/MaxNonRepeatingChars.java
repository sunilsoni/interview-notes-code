package com.interview.notes.code.months.oct24.test3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaxNonRepeatingChars {

    // Function to count the number of unique characters in a string
    public static int countUniqueChars(String str) {
        Set<Character> uniqueChars = new HashSet<>();

        // Add each character to the set (which automatically handles duplicates)
        for (char c : str.toCharArray()) {
            uniqueChars.add(c);
        }

        // The size of the set is the number of unique characters
        return uniqueChars.size();
    }

    // Function to find the string with the maximum non-repeating characters
    public static String findMaxNonRepeating(List<String> strings) {
        String result = "";
        int maxUniqueCount = 0;

        // Iterate over each string in the list
        for (String str : strings) {
            int uniqueCount = countUniqueChars(str);

            // Check if this string has more unique characters than the current max
            if (uniqueCount > maxUniqueCount) {
                maxUniqueCount = uniqueCount;
                result = str;
            }
        }

        return result; // Return the string with the maximum unique characters
    }

    public static void main(String[] args) {
        // Example list of strings
        List<String> inputStrings = Arrays.asList("apple", "banana", "abcd", "abcabc", "xyzzy");

        String result = findMaxNonRepeating(inputStrings);

        System.out.println("String with the maximum non-repeating characters: " + result);
    }
}
