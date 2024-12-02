package com.interview.notes.code.year.y2024.nov24.amazon.test28;

import java.util.*;

/*
• Write a function that identifies all anagrams in a list.
• anagram = A word formed from another word by rearranging its letters.
•e.g.:
• input: [arts, book, ring, rats, star, kobo]
•output: larts, book, rats, star, kobo]
 */
public class AnagramFinder {
    public static void main(String[] args) {
        // Test case 1: Provided example
        List<String> input1 = Arrays.asList("arts", "book", "ring", "rats", "star", "kobo");
        List<String> expectedOutput1 = Arrays.asList("arts", "book", "rats", "star", "kobo");
        testAnagramFinder(input1, expectedOutput1, "Test Case 1");

        // Test case 2: Empty list
        List<String> input2 = new ArrayList<>();
        List<String> expectedOutput2 = new ArrayList<>();
        testAnagramFinder(input2, expectedOutput2, "Test Case 2");

        // Test case 3: No anagrams
        List<String> input3 = Arrays.asList("hello", "world", "java", "python");
        List<String> expectedOutput3 = new ArrayList<>();
        testAnagramFinder(input3, expectedOutput3, "Test Case 3");

        // Test case 4: All words are anagrams
        List<String> input4 = Arrays.asList("listen", "silent", "enlist", "inlets");
        List<String> expectedOutput4 = Arrays.asList("listen", "silent", "enlist", "inlets");
        testAnagramFinder(input4, expectedOutput4, "Test Case 4");

        // Test case 5: Duplicate words
        List<String> input5 = Arrays.asList("dusty", "study", "study", "dusty");
        List<String> expectedOutput5 = Arrays.asList("dusty", "study", "study", "dusty");
        testAnagramFinder(input5, expectedOutput5, "Test Case 5");

        // Test case 6: Large data input
        List<String> input6 = generateLargeInput(100000);
        // Since all words are "abc", the expected output is the same as input
        testAnagramFinder(input6, input6, "Test Case 6 (Large Data Input)");
    }

    /**
     * Finds all words in the list that have at least one anagram in the list.
     *
     * @param words List of words to process
     * @return List of words that have at least one anagram
     */
    public static List<String> findAnagrams(List<String> words) {
        Map<String, List<String>> signatureMap = new HashMap<>();
        for (String word : words) {
            String signature = getSignature(word);
            signatureMap.computeIfAbsent(signature, k -> new ArrayList<>()).add(word);
        }

        Set<String> anagramWords = new HashSet<>();
        for (List<String> group : signatureMap.values()) {
            if (group.size() > 1) {
                anagramWords.addAll(group);
            }
        }

        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (anagramWords.contains(word)) {
                result.add(word);
            }
        }

        return result;
    }

    /**
     * Generates a signature for a word by sorting its characters.
     *
     * @param word The word to generate a signature for
     * @return The signature string
     */
    private static String getSignature(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * Tests the findAnagrams method with a given input and expected output.
     *
     * @param input          The input list of words
     * @param expectedOutput The expected output list of words
     * @param testCaseName   The name of the test case
     */
    private static void testAnagramFinder(List<String> input, List<String> expectedOutput, String testCaseName) {
        List<String> actualOutput = findAnagrams(input);
        if (actualOutput.equals(expectedOutput)) {
            System.out.println(testCaseName + ": PASS");
        } else {
            System.out.println(testCaseName + ": FAIL");
            System.out.println("Expected Output: " + expectedOutput);
            System.out.println("Actual Output: " + actualOutput);
        }
    }

    /**
     * Generates a large input list for testing large data inputs.
     *
     * @param size The number of words to generate
     * @return The generated list of words
     */
    private static List<String> generateLargeInput(int size) {
        List<String> largeInput = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            largeInput.add("abc");
        }
        return largeInput;
    }
}
