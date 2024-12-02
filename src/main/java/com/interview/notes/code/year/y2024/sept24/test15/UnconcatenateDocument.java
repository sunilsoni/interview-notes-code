package com.interview.notes.code.year.y2024.sept24.test15;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Punctuations, capitalizations and spaces.
Description
All spaces, punctuation and capitalization in a long document have been accidentally removed.
A sentence like "Please do a code review so I can submit my PR." became
"pleasedoacodereviewsoicansubmitmypr".
For now, the spaces need to be re-inserted (punctuations and capitalization can be done later). Most of the words in the document are in a dictionary but a few are not.
Given a dictionary (a list of strings) and a document (a string), create a java program to unconcatenate the document (string) in a way that minimizes the number of unrecognized characters.
Dictionary contains the following words:
"did", "the", "code", "review", "for", "pull", "request"
If document is
pauldidthecodereviewforsimonspullrequest
Output of program should be:
paul did the code review for simons pull request
There are 10 unrecognized characters.
 */
public class UnconcatenateDocument {

    // Method to unconcatenate the document
    public static String unconcatenate(String document, Set<String> dictionary) {
        int n = document.length();
        int[] dp = new int[n + 1]; // Minimum unrecognized characters up to index i
        Arrays.fill(dp, Integer.MAX_VALUE); // Initialize dp array with max value
        //   dp[0] = 0; // No unrecognized characters at the start

        // Array to store the split points for backtracking
        int[] split = new int[n + 1];
        Arrays.fill(split, -1);

        // Predicate

        // Dynamic programming to fill dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                String word = document.substring(j, i);
                int unrecognizedChars = dictionary.contains(word) ? 0 : word.length();

                if (dp[j] + unrecognizedChars < dp[i]) {
                    dp[i] = dp[j] + unrecognizedChars;
                    split[i] = j; // Store the split point
                }
            }
        }

        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(split));
        // Backtrack to reconstruct the sentence
        StringBuilder result = new StringBuilder();
        int i = n;
        while (i > 0) {
            int j = split[i];
            String word = document.substring(j, i);
            if (result.length() > 0) {
                result.insert(0, " "); // Add space between words
            }
            result.insert(0, word);
            i = j;
        }

        return result.toString();
    }

    // Main method to test the implementation
    public static void main(String[] args) {
        // Define the dictionary
        Set<String> dictionary = new HashSet<>(Arrays.asList(
                "did", "the", "code", "review", "for", "pull", "request"
        ));

        // Test case 1
        String document1 = "pauldidthecodereviewforsimonspullrequest";
        String result1 = unconcatenate(document1, dictionary);
        System.out.println("Result: " + result1);
        System.out.println("Unrecognized characters: " + countUnrecognizedCharacters(result1, dictionary));

        // Test case 2 (add more test cases as needed)
        // String document2 = ...
        // String result2 = unconcatenate(document2, dictionary);
        // System.out.println("Result: " + result2);
        // System.out.println("Unrecognized characters: " + countUnrecognizedCharacters(result2, dictionary));


    }

    // Helper method to count unrecognized characters in the final result
    private static int countUnrecognizedCharacters(String result, Set<String> dictionary) {
        String[] words = result.split(" ");
        int unrecognized = 0;
        for (String word : words) {
            if (!dictionary.contains(word)) {
                unrecognized += word.length();
            }
        }
        return unrecognized;
    }
}
