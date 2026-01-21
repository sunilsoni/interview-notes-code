package com.interview.notes.code.year.y2025.december.common.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AnagramGrouper {

    // Main method to group anagrams using Stream API
    public static List<List<String>> groupAnagrams(String[] words) {

        // Handle null or empty input - return empty list
        if (words == null || words.length == 0) return new ArrayList<>();

        // Stream through all words and group by sorted character key
        return new ArrayList<>(
                Arrays.stream(words) // Convert array to stream for processing
                        .collect(Collectors.groupingBy( // Group elements by a key
                                word -> sortChars(word) // Key = sorted characters of word
                        ))
                        .values() // Get only the grouped lists, ignore keys
        );
    }

    // Helper: Sort characters of a word to create unique key for anagrams
    private static String sortChars(String word) {

        // Convert string to char array for sorting
        char[] chars = word.toCharArray();

        // Sort characters alphabetically
        Arrays.sort(chars);

        // Return sorted string as key
        return new String(chars);
    }

    // Test runner method - checks if actual matches expected
    public static void runTest(String name, String[] input, List<List<String>> expected) {

        // Get actual result from our method
        var actual = groupAnagrams(input);

        // Sort both for comparison (order may differ)
        var sortedActual = sortResult(actual);
        var sortedExpected = sortResult(expected);

        // Check if results match
        boolean pass = sortedActual.equals(sortedExpected);

        // Print result with test name
        System.out.println(name + ": " + (pass ? "PASS ✓" : "FAIL ✗"));

        // If failed, show details for debugging
        if (!pass) {
            System.out.println("  Expected: " + sortedExpected);
            System.out.println("  Actual:   " + sortedActual);
        }
    }

    // Helper: Sort result for consistent comparison
    private static List<List<String>> sortResult(List<List<String>> result) {

        // Sort each inner list, then sort outer list by first element
        return result.stream()
                .map(list -> list.stream().sorted().toList()) // Sort words in each group
                .sorted((a, b) -> a.isEmpty() ? -1 : b.isEmpty() ? 1 : a.get(0).compareTo(b.get(0))) // Sort groups
                .toList();
    }

    // Main method with all test cases
    public static void main(String[] args) {

        System.out.println("=== ANAGRAM GROUPER TESTS ===\n");

        // TEST 1: Given example from problem
        runTest("Test 1 - Basic Example",
                new String[]{"eat", "tea", "tan", "ate", "nat", "bat"},
                List.of(
                        List.of("eat", "tea", "ate"),  // Group 1: e,a,t letters
                        List.of("tan", "nat"),          // Group 2: t,a,n letters
                        List.of("bat")                  // Group 3: b,a,t letters
                )
        );

        // TEST 2: Empty array input
        runTest("Test 2 - Empty Array",
                new String[]{},
                List.of()
        );

        // TEST 3: Single word only
        runTest("Test 3 - Single Word",
                new String[]{"hello"},
                List.of(List.of("hello"))
        );

        // TEST 4: No anagrams exist
        runTest("Test 4 - No Anagrams",
                new String[]{"abc", "def", "ghi"},
                List.of(
                        List.of("abc"),
                        List.of("def"),
                        List.of("ghi")
                )
        );

        // TEST 5: All words are anagrams
        runTest("Test 5 - All Anagrams",
                new String[]{"abc", "bca", "cab", "acb"},
                List.of(List.of("abc", "bca", "cab", "acb"))
        );

        // TEST 6: Empty strings
        runTest("Test 6 - Empty Strings",
                new String[]{"", ""},
                List.of(List.of("", ""))
        );

        // TEST 7: Null input
        runTest("Test 7 - Null Input",
                null,
                List.of()
        );

        // TEST 8: Words with same letters different counts
        runTest("Test 8 - Different Letter Counts",
                new String[]{"aab", "aba", "baa", "abb"},
                List.of(
                        List.of("aab", "aba", "baa"),
                        List.of("abb")
                )
        );

        // TEST 9: Large data test - performance check
        System.out.println("\nTest 9 - Large Data (10000 words): ");
        String[] largeInput = generateLargeInput(10000); // Create 10000 words
        long start = System.currentTimeMillis(); // Start timer
        var result = groupAnagrams(largeInput); // Process
        long end = System.currentTimeMillis(); // End timer
        System.out.println("  Processed " + largeInput.length + " words into " + result.size() + " groups");
        System.out.println("  Time: " + (end - start) + "ms - " + ((end - start) < 1000 ? "PASS ✓" : "FAIL ✗"));

        // TEST 10: Very large single group
        System.out.println("\nTest 10 - Large Single Group (1000 anagrams): ");
        String[] sameAnagrams = generateAnagrams("abcde", 1000);
        start = System.currentTimeMillis();
        result = groupAnagrams(sameAnagrams);
        end = System.currentTimeMillis();
        boolean pass = result.size() == 1 && result.get(0).size() == 1000;
        System.out.println("  Groups: " + result.size() + ", Size: " + result.get(0).size());
        System.out.println("  Time: " + (end - start) + "ms - " + (pass ? "PASS ✓" : "FAIL ✗"));

        System.out.println("\n=== ALL TESTS COMPLETED ===");
    }

    // Helper: Generate large random input for performance testing
    private static String[] generateLargeInput(int size) {

        var random = new Random(42); // Fixed seed for reproducibility
        var words = new String[size]; // Array to hold generated words

        // Generate random words of length 3-6
        for (int i = 0; i < size; i++) {
            int len = 3 + random.nextInt(4); // Random length 3 to 6
            var sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                sb.append((char) ('a' + random.nextInt(26))); // Random letter a-z
            }
            words[i] = sb.toString();
        }
        return words;
    }

    // Helper: Generate multiple anagrams of a base word
    private static String[] generateAnagrams(String base, int count) {

        var result = new String[count]; // Array for anagrams
        var chars = base.toCharArray(); // Base word characters
        var random = new Random(42); // Fixed seed

        // Shuffle base word to create anagrams
        for (int i = 0; i < count; i++) {
            for (int j = chars.length - 1; j > 0; j--) {
                int k = random.nextInt(j + 1); // Random index
                char temp = chars[j]; // Swap characters
                chars[j] = chars[k];
                chars[k] = temp;
            }
            result[i] = new String(chars);
        }
        return result;
    }
}