package com.interview.notes.code.year.y2025.october.common.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniqueCharSubstringSum {

    // Method to calculate total sum of unique character counts of all substrings
    public static int sumOfUniqueChars(String str) {
        int n = str.length();
        int totalSum = 0;

        // Outer loop: start index
        for (int i = 0; i < n; i++) {
            // Frequency array for current window
            int[] freq = new int[256];
            int uniqueCount = 0;

            // Inner loop: end index
            for (int j = i; j < n; j++) {
                char c = str.charAt(j);

                // When we see a character for first time, increase unique count
                if (freq[c] == 0) uniqueCount++;
                // When we see it for second time, decrease unique count
                else if (freq[c] == 1) uniqueCount--;

                freq[c]++; // update frequency

                // add unique count of this substring
                totalSum += uniqueCount;
            }
        }
        return totalSum;
    }

    // Test method
    public static void runTests() {
        Map<String, Integer> tests = new LinkedHashMap<>();
        tests.put("ABA", 8);
        tests.put("AABCDC", 47);
        tests.put("ABC", 10);
        tests.put("AAA", 3);
        tests.put("A", 1);

        System.out.println("Running Test Cases...");
        tests.forEach((input, expected) -> {
            int result = sumOfUniqueChars(input);
            String status = (result == expected) ? "PASS" : "FAIL";
            System.out.printf("Input=%s | Output=%d | Expected=%d | Result=%s%n",
                    input, result, expected, status);
        });

        // Large data test
        String large = IntStream.range(0, 1000)
                .mapToObj(i -> String.valueOf((char) ('A' + (i % 26))))
                .collect(Collectors.joining());
        long start = System.currentTimeMillis();
        int res = sumOfUniqueChars(large);
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms | Result=" + res);
    }

    public static void main(String[] args) {
        runTests();
    }
}