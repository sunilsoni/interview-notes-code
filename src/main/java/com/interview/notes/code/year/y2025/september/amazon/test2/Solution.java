package com.interview.notes.code.year.y2025.september.amazon.test2;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static int minOperationsToUnbias(int n, String data1, String data2) {
        int minOps = Integer.MAX_VALUE;

        for (int i = 0; i <= data1.length(); i++) {
            for (int j = 0; j <= data2.length(); j++) {
                String remainingData1 = data1.substring(0, data1.length() - i);
                String remainingData2 = data2.substring(j);

                String combined = remainingData1 + remainingData2;

                long ones = combined.chars().filter(c -> c == '1').count();
                long zeros = combined.chars().filter(c -> c == '0').count();

                if (ones == zeros) {
                    minOps = Math.min(minOps, i + j);
                }
            }
        }

        return minOps;
    }

    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        tests.add(new TestCase(2, "01", "11", 2));
        tests.add(new TestCase(6, "111001", "010110", 6));
        tests.add(new TestCase(4, "1111", "0000", 0));
        tests.add(new TestCase(8, "11110000", "00001111", 0));
        tests.add(new TestCase(10, "1010101010", "0101010101", 0));
        tests.add(new TestCase(5, "11100", "00011", 0));
        tests.add(new TestCase(3, "111", "000", 0));
        tests.add(new TestCase(7, "1111111", "0000000", 0));
        tests.add(new TestCase(6, "101010", "010101", 0));
        tests.add(new TestCase(8, "11001100", "00110011", 0));

        StringBuilder largeData1 = new StringBuilder();
        StringBuilder largeData2 = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            largeData1.append("1");
            largeData2.append("0");
        }
        tests.add(new TestCase(100000, largeData1.toString(), largeData2.toString(), 0));

        StringBuilder largeUnbalanced1 = new StringBuilder();
        StringBuilder largeUnbalanced2 = new StringBuilder();
        for (int i = 0; i < 30000; i++) {
            largeUnbalanced1.append("1");
        }
        for (int i = 0; i < 20000; i++) {
            largeUnbalanced2.append("0");
        }
        tests.add(new TestCase(50000, largeUnbalanced1.toString(), largeUnbalanced2.toString(), 10000));

        tests.add(new TestCase(12, "111111000000", "000000111111", 0));
        tests.add(new TestCase(9, "111111111", "000000", 3));
        tests.add(new TestCase(15, "111111111111111", "000000000000000", 0));
        tests.add(new TestCase(4, "1100", "1100", 2));
        tests.add(new TestCase(6, "111100", "001111", 2));
        tests.add(new TestCase(8, "11111111", "00000000", 0));
        tests.add(new TestCase(5, "11111", "00000", 0));
        tests.add(new TestCase(7, "1111000", "0001111", 1));

        int passCount = 0;
        int failCount = 0;

        for (int i = 0; i < tests.size(); i++) {
            TestCase tc = tests.get(i);
            int result = minOperationsToUnbias(tc.n, tc.data1, tc.data2);
            boolean passed = result == tc.expected;

            if (passed) {
                passCount++;
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                failCount++;
                System.out.println("Test " + (i + 1) + ": FAIL (Expected: " + tc.expected + ", Got: " + result + ")");
            }
        }

        System.out.println("\n=== Test Summary ===");
        System.out.println("Total Tests: " + tests.size());
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + failCount);
        System.out.println("Success Rate: " + (passCount * 100.0 / tests.size()) + "%");
    }

    static class TestCase {
        int n;
        String data1;
        String data2;
        int expected;

        TestCase(int n, String data1, String data2, int expected) {
            this.n = n;
            this.data1 = data1;
            this.data2 = data2;
            this.expected = expected;
        }
    }
}