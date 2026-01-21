package com.interview.notes.code.year.y2025.december.hackerank.test4;

public class BinaryStringConverter {

    public static int getMinimumOperations(String s) {
        int ones = (int) s.chars().filter(c -> c == '1').count();
        if (ones == 0) {
            return 0;
        }
        int groups = 0;
        boolean inGroup = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (!inGroup) {
                    groups++;
                    inGroup = true;
                }
            } else {
                inGroup = false;
            }
        }
        return ones + groups - 1;
    }

    public static void main(String[] args) {
        runTest("1111", 2, 1);
        runTest("0000", 0, 2);
        runTest("101", 4, 3);
        runTest("00", 0, 4);
        runTest("11", 1, 5);
        runTest("10", 2, 6);
        runTest("01", 2, 7);
        runTest("1100", 2, 8);
        runTest("0011", 2, 9);
        runTest("1010", 5, 10);
        runTest("111111", 3, 11);
        runTest("1".repeat(100000), 50000, 12);
        runTest("0".repeat(100000), 0, 13);
        runTest("10".repeat(50000), 149999, 14);
        runTest("110011", 5, 15);
        runTest("1001", 4, 16);
    }

    static void runTest(String input, int expected, int testNum) {
        long start = System.currentTimeMillis();
        int result = getMinimumOperations(input);
        long time = System.currentTimeMillis() - start;
        String status = result == expected ? "PASS" : "FAIL";
        System.out.printf("Test %d: %s | Expected: %d, Got: %d | Time: %dms%n",
                testNum, status, expected, result, time);
    }
}