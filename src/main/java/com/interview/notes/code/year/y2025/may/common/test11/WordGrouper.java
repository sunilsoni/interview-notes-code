package com.interview.notes.code.year.y2025.may.common.test11;

import java.util.*;
import java.util.stream.Collectors;

public class WordGrouper {

    /**
     * Groups words by their length and returns a map where:
     * - Key: length of words
     * - Value: list of words with that length
     *
     * @param words List of input strings to be grouped
     * @return Map with word lengths as keys and lists of words as values
     */
    public static Map<Integer, List<String>> groupWordsByLength(List<String> words) {
        // Handle null input case
        if (words == null) {
            return new HashMap<>();
        }

        // Using Java 8 Stream API to group words
        // Collectors.groupingBy creates a map with word length as key
        return words.stream()
                .filter(word -> word != null && !word.isEmpty()) // Filter out null/empty words
                .collect(Collectors.groupingBy(
                        String::length, // Key function: gets length of each word
                        HashMap::new,   // Map type to be created
                        Collectors.toList() // Value function: collects words into a List
                ));
    }

    public static void main(String[] args) {
        // Test Case 1: Normal case
        List<String> test1 = Arrays.asList("cat", "dog", "elephant", "ant", "tiger", "lion");
        testAndPrint("Test 1 - Normal case", test1);

        // Test Case 2: Empty list
        List<String> test2 = new ArrayList<>();
        testAndPrint("Test 2 - Empty list", test2);

        // Test Case 3: List with null values
        List<String> test3 = Arrays.asList("cat", null, "dog", "");
        testAndPrint("Test 3 - List with null values", test3);

        // Test Case 4: Large data set
        List<String> test4 = generateLargeDataSet(10000);
        testAndPrint("Test 4 - Large data set", test4);
    }

    private static void testAndPrint(String testName, List<String> input) {
        System.out.println("\n" + testName + ":");
        System.out.println("Input: " + input);
        Map<Integer, List<String>> result = groupWordsByLength(input);
        System.out.println("Result: " + result);
    }

    private static List<String> generateLargeDataSet(int size) {
        List<String> largeList = new ArrayList<>();
        String[] sampleWords = {"cat", "dog", "elephant", "ant", "tiger", "lion"};
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            largeList.add(sampleWords[random.nextInt(sampleWords.length)]);
        }
        return largeList;
    }
}
