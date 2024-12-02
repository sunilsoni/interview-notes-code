package com.interview.notes.code.year.y2024.nov24.amazon.test17;

import java.util.Arrays;

public class WordConjugates {

    public static long countValidSubstrings(String s) {
        long count = 0;
        int n = s.length();
        int[] lastA = new int[n], lastB = new int[n], lastC = new int[n], lastD = new int[n];
        Arrays.fill(lastA, -1);
        Arrays.fill(lastB, -1);
        Arrays.fill(lastC, -1);
        Arrays.fill(lastD, -1);

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'a') lastA[i] = i;
            else if (c == 'b') lastB[i] = i;
            else if (c == 'c') lastC[i] = i;
            else if (c == 'd') lastD[i] = i;

            if (i > 0) {
                lastA[i] = Math.max(lastA[i], lastA[i - 1]);
                lastB[i] = Math.max(lastB[i], lastB[i - 1]);
                lastC[i] = Math.max(lastC[i], lastC[i - 1]);
                lastD[i] = Math.max(lastD[i], lastD[i - 1]);
            }

            int minAB = Math.min(lastA[i], lastB[i]);
            int minCD = Math.min(lastC[i], lastD[i]);
            count += Math.min(minAB, minCD) + 1;
        }

        return count;
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "abdc",
                "adcb",
                "abcdad",
                "aaabbbcccddd",
                "abcdabcdabcd",
                "a",
                "ab",
                "cd",
                "abcd" + "a".repeat(100000) + "bcd",
                "a".repeat(200000) + "b".repeat(200000)
        };

        long[] expectedResults = {3, 2, 6, 22, 30, 0, 1, 1, 100015, 40000000000L};

        for (int i = 0; i < testCases.length; i++) {
            long start = System.currentTimeMillis();
            long result = countValidSubstrings(testCases[i]);
            long end = System.currentTimeMillis();

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + (testCases[i].length() > 50 ? testCases[i].substring(0, 47) + "..." : testCases[i]));
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println("Output: " + result);
            System.out.println("Time taken: " + (end - start) + "ms");
            System.out.println("Status: " + (result == expectedResults[i] ? "PASS" : "FAIL"));
            System.out.println();
        }
    }
}
