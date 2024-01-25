package com.interview.notes.code.months.jan24.test3;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class WordSplitter {

    // Method to check if the string can be split into dictionary words
    public static boolean canBeSplit(String input, String[] dictionary) {
        Set<String> dictSet = new HashSet<>(Arrays.asList(dictionary));
        return canSplit(input, dictSet);
    }

    // Helper method to perform the split logic
    private static boolean canSplit(String input, Set<String> dictSet) {
        for (int i = 1; i <= input.length(); i++) {
            // Split the string into two parts
            String left = input.substring(0, i);
            String right = input.substring(i);

            // Check if the left part is in the dictionary
            if (dictSet.contains(left)) {
                // If right part is empty or also in dictionary, return true
                if (right.isEmpty() || dictSet.contains(right) || canSplit(right, dictSet)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Main method for example execution
    public static void main(String[] args) {
        String input = "maneater";
        String[] dictionary = {"man", "eater"};
        System.out.println("Can be split: " + canBeSplit(input, dictionary));

        input = "helloworld";
        dictionary = new String[]{"hello", "world","me"};
        System.out.println("Can be split: " + canBeSplit(input, dictionary));
    }
}
