package com.interview.notes.code.year.y2026.jan.hackerrank.test1;

public class BinaryStringOptimizer {

    public static int getMinimumOperations(String s) {
        int operations = 0;
        var chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                if (i + 1 < chars.length && chars[i + 1] == '1') {
                    operations += 1;
                    i++;
                } else {
                    operations += 2;
                }
            }
        }
        return operations;
    }

    public static void main(String[] args) {
        runTest("Sample Case 0", "1111", 2);
        runTest("Sample Case 1", "0000", 0);
        runTest("Example 101", "101", 4);
        runTest("Odd Length", "111", 3);
        runTest("Mixed", "1101", 3);
        runTest("Edge Min", "10", 2);

        // Large Data Test
        var sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) sb.append("1");
        runTest("Large Input (All 1s)", sb.toString(), 50000);
    }

    private static void runTest(String testName, String input, int expected) {
        long start = System.nanoTime();
        int result = getMinimumOperations(input);
        double timeMs = (System.nanoTime() - start) / 1_000_000.0;

        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("%-20s | Status: %-4s | Expected: %-5d | Got: %-5d | Time: %.4f ms%n",
                testName, status, expected, result, timeMs);
    }
}