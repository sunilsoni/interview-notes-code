package com.interview.notes.code.year.y2025.november.capitalOne.test4;

import java.util.Arrays;
import java.util.stream.IntStream;

class LongestCommonPrefixFinder {
    
    public static void main(String[] args) {
        var solver = new LongestCommonPrefixFinder();

        record TestCase(int[] firstArray, int[] secondArray, int expected) {}

        var testCases = new TestCase[]{
            new TestCase(
                new int[]{25, 288, 2655, 54546, 54, 555},
                new int[]{2, 255, 266, 244, 26, 5, 54547},
                4
            ),
            new TestCase(
                new int[]{25, 288, 2655, 544, 54, 555},
                new int[]{2, 255, 266, 244, 26, 5, 5444444},
                3
            ),
            new TestCase(
                new int[]{817, 99},
                new int[]{1999, 1909},
                0
            ),
            new TestCase(
                new int[]{123, 456, 789},
                new int[]{124, 457, 790},
                2
            ),
            new TestCase(
                new int[]{111111, 222222},
                new int[]{111112, 222223},
                5
            ),
            new TestCase(
                new int[]{1},
                new int[]{1},
                1
            ),
            new TestCase(
                new int[]{999999999},
                new int[]{999999998},
                8
            ),
            new TestCase(
                generateLargeArray(50000, 1000000),
                generateLargeArray(50000, 1000001),
                6
            )
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            var tc = testCases[i];
            int result = solver.solution(tc.firstArray, tc.secondArray);
            boolean isPass = result == tc.expected;

            System.out.printf("Test %2d: %s%n", i + 1, isPass ? "PASS" : "FAIL");

            if (isPass) {
                passed++;
            } else {
                failed++;
                System.out.printf("  Expected: %d, Got: %d%n", tc.expected, result);
            }
        }

        System.out.printf("%nResults: %d PASS, %d FAIL%n", passed, failed);
    }
    
    static int[] generateLargeArray(int size, int startValue) {
        return IntStream.range(0, size)
            .map(i -> startValue + (i % 1000))
            .toArray();
    }
    
    int solution(int[] firstArray, int[] secondArray) {
        return Arrays.stream(firstArray)
            .map(first -> Arrays.stream(secondArray)
                .map(second -> getCommonPrefixLength(String.valueOf(first), String.valueOf(second)))
                .max()
                .orElse(0))
            .max()
            .orElse(0);
    }
    
    int getCommonPrefixLength(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        int count = 0;

        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}