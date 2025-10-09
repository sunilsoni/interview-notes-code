package com.interview.notes.code.year.y2025.october.common.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnagramChecker {

    // Method to check if two strings are anagrams
    public static boolean areAnagrams(String str1, String str2) {

        // Step 1: Null or empty check to avoid errors
        if (str1 == null || str2 == null) return false;

        // Step 2: Normalize both strings (remove spaces, convert to lowercase)
        String s1 = str1.replaceAll("\\s+", "").toLowerCase();
        String s2 = str2.replaceAll("\\s+", "").toLowerCase();

        // Step 3: Quick length check – if lengths differ, they can't be anagrams
        if (s1.length() != s2.length()) return false;

        // Step 4: Convert each string to a sorted sequence of characters using Java 8 Stream
        String sorted1 = s1.chars()                               // Get IntStream of characters
                .sorted()                                         // Sort the characters
                .mapToObj(c -> String.valueOf((char) c))          // Convert int → char → String
                .collect(Collectors.joining());                   // Join them back into a single String

        String sorted2 = s2.chars()
                .sorted()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());

        // Step 5: Compare sorted strings
        return sorted1.equals(sorted2);
    }

    // ================== TESTING SECTION ==================
    public static void main(String[] args) {

        // List of test cases with expected results
        Map<String[], Boolean> testCases = new LinkedHashMap<>();

        // Small normal cases
        testCases.put(new String[]{"listen", "silent"}, true);
        testCases.put(new String[]{"Triangle", "Integral"}, true);
        testCases.put(new String[]{"apple", "pale"}, false);
        testCases.put(new String[]{"Astronomer", "Moon starer"}, true);
        testCases.put(new String[]{"Hello", "Olelh"}, true);
        testCases.put(new String[]{"School master", "The classroom"}, true);
        testCases.put(new String[]{"Conversation", "Voices rant on"}, true);
        testCases.put(new String[]{"Debit card", "Bad credit"}, true);
        testCases.put(new String[]{"Dormitory", "Dirty room"}, true);
        testCases.put(new String[]{"abc", "abcd"}, false);

        // Large data test (performance)
        String largeA = Stream.generate(() -> "a").limit(1_000_000).collect(Collectors.joining());
        String largeB = Stream.generate(() -> "a").limit(1_000_000).collect(Collectors.joining());
        testCases.put(new String[]{largeA, largeB}, true);

        // Large mismatched case
        String largeC = Stream.generate(() -> "a").limit(999_999).collect(Collectors.joining()) + "b";
        testCases.put(new String[]{largeA, largeC}, false);

        System.out.println("Running Anagram Tests...\n");

        int passCount = 0; // counter for passed cases

        // Loop through each test case
        for (Map.Entry<String[], Boolean> entry : testCases.entrySet()) {
            String[] inputs = entry.getKey();
            boolean expected = entry.getValue();

            // Call our anagram function
            boolean actual = areAnagrams(inputs[0], inputs[1]);

            // Compare expected vs actual
            if (actual == expected) {
                System.out.println("✅ PASS for: [" + inputs[0] + "] & [" + inputs[1] + "]");
                passCount++;
            } else {
                System.out.println("❌ FAIL for: [" + inputs[0] + "] & [" + inputs[1] + "]");
            }
        }

        System.out.println("\nSummary: Passed " + passCount + " / " + testCases.size() + " test cases.");
    }
}
