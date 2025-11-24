package com.interview.notes.code.year.y2025.november.capitalOne.test1;

import java.util.stream.IntStream;

class WebsiteVisitAnalyzer {
    
    public static void main(String[] args) {
        var analyzer = new WebsiteVisitAnalyzer();

        record TestCase(int[] visits, int target, int expected) {}

        var testCases = new TestCase[]{
            new TestCase(new int[]{300, 200, 100, 200, 500}, 700, 3),
            new TestCase(new int[]{900, 821, 1090}, 900, 0),
            new TestCase(new int[]{700, 800, 500}, 2001, -1),
            new TestCase(new int[]{100}, 100, 0),
            new TestCase(new int[]{100}, 101, -1),
            new TestCase(new int[]{50, 50, 50}, 150, 2),
            new TestCase(new int[]{1, 1, 1, 1, 1}, 3, 2),
            new TestCase(new int[]{1000000, 1000000, 1000000}, 2000000, 1),
            new TestCase(new int[]{}, 1, -1),
            new TestCase(new int[]{0, 0, 0}, 0, 0),
            new TestCase(new int[]{Integer.MAX_VALUE}, Integer.MAX_VALUE, 0),
            new TestCase(generateLargeArray(1000, 100), 50000, 499),
            new TestCase(generateLargeArray(1000, 1000), 1000000, 999),
            new TestCase(generateLargeArray(1000, 1), 500, 499)
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length; i++) {
            var tc = testCases[i];
            int result = analyzer.solution(tc.visits, tc.target);
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
    
    static int[] generateLargeArray(int size, int value) {
        return IntStream.generate(() -> value)
            .limit(size)
            .toArray();
    }
    
    int solution(int[] visits, int target) {
        return IntStream.range(0, visits.length)
            .filter(i -> IntStream.rangeClosed(0, i)
                .map(j -> visits[j])
                .sum() >= target)
            .findFirst()
            .orElse(-1);
    }
}