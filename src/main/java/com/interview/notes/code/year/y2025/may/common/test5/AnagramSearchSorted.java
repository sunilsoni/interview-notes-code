package com.interview.notes.code.year.y2025.may.common.test5;

import java.util.*;
import java.util.stream.Collectors;

public class AnagramSearchSorted {

    // Sorts characters of word (in lowercase) and returns the sorted string
    private static String getSortedLowercase(String word) {
        char[] chars = word.toLowerCase().toCharArray();
        Arrays.sort(chars); // Sort character array
        return new String(chars);
    }

    // Finds all words in dictionary that are sorted-letter matches of the input word
    public static List<String> findMatchingWords(List<String> dictionary, String input) {
        String sortedInput = getSortedLowercase(input);
        int targetLength = input.length(); // Eliminate early by length

        return dictionary.stream()
                .filter(word -> word.length() == targetLength)           // Early exit optimization
                .filter(word -> getSortedLowercase(word).equals(sortedInput)) // Check if sorted form matches
                .collect(Collectors.toList());
    }

    // Main method for running test cases
    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(
                "testing", "tes", "test", "estt", "Test", "TTes", "text", "extt", "tex", "ext"
        );
        String inputWord = "Test";

        Set<String> expectedOutput = new HashSet<>(Arrays.asList("test", "estt", "Test", "TTes"));
        List<String> result = findMatchingWords(dictionary, inputWord);

        System.out.println("Search Word: " + inputWord);
        System.out.println("Matched Words: " + result);
        boolean isPass = result.size() == expectedOutput.size() && expectedOutput.containsAll(result);
        System.out.println("Test Case: " + (isPass ? "PASS" : "FAIL"));

        // Edge Case
        System.out.println("\nEmpty Dictionary Test:");
        System.out.println("Result: " + findMatchingWords(Collections.emptyList(), "Test"));

        // Large Dictionary Test
        System.out.println("\nLarge Dictionary Test:");
        List<String> largeDict = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeDict.add("test");
            largeDict.add("estt");
            largeDict.add("TTes");
            largeDict.add("text"); // mismatch
            largeDict.add("TesT");
        }
        long start = System.currentTimeMillis();
        List<String> largeResult = findMatchingWords(largeDict, "Test");
        long end = System.currentTimeMillis();
        System.out.println("Total Matches: " + largeResult.size());
        System.out.println("Time Taken: " + (end - start) + " ms");
    }
}