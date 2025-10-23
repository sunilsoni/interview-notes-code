package com.interview.notes.code.year.y2025.october.common.test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UniqueCharSubstringSum {

    // Method to calculate total sum of unique character counts of all substrings
    public static int sumOfUniqueChars(String str) {
        // length of string
        int n = str.length();

        // this will store final answer
        int totalSum = 0;

        // outer loop to pick each starting point of substring
        for (int i = 0; i < n; i++) {

            // inner loop to pick each ending point of substring
            for (int j = i; j < n; j++) {

                // get substring from index i to j (inclusive)
                String sub = str.substring(i, j + 1);

                // use frequency map to count characters in current substring
                Map<Character, Long> freq = sub.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

                // find number of characters that appear only once
                long uniqueCount = freq.values().stream()
                        .filter(count -> count == 1)
                        .count();

                // add that to total sum
                totalSum += uniqueCount;
            }
        }
        return totalSum;
    }

    // simple method to test and print result for multiple cases
    public static void runTests() {
        // prepare test cases
        Map<String, Integer> tests = new LinkedHashMap<>();
        tests.put("ABA", 8);
        tests.put("AABCDC", 47);
        tests.put("ABC", 10);
        tests.put("AAA", 3);
        tests.put("A", 1);

        // print results for each test case
        System.out.println("Running Test Cases...");
        tests.forEach((input, expected) -> {
            int result = sumOfUniqueChars(input);
            String status = (result == expected) ? "PASS" : "FAIL";
            System.out.printf("Input=%s | Output=%d | Expected=%d | Result=%s%n",
                    input, result, expected, status);
        });

        // Large data test to check performance
        String large = IntStream.range(0, 500)
                .mapToObj(i -> String.valueOf((char) ('A' + (i % 26))))
                .collect(Collectors.joining());
        long start = System.currentTimeMillis();
        int res = sumOfUniqueChars(large);
        long end = System.currentTimeMillis();
        System.out.println("Large data test executed in " + (end - start) + " ms | Result=" + res);
    }

    // main method to execute
    public static void main(String[] args) {
        runTests();
    }
}