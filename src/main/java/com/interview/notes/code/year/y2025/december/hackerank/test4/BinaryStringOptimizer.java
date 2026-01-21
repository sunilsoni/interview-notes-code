package com.interview.notes.code.year.y2025.december.hackerank.test4;

public class BinaryStringOptimizer {

    public static int getMinimumOperations(String s) {
        var operations = 0;
        var n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (i + 1 < n) {
                    operations += (s.charAt(i + 1) == '1') ? 1 : 2;
                    i++;
                } else {
                    operations += 2;
                }
            }
        }
        return operations;
    }

    public static void main(String[] args) {
        System.out.println("Running Tests...");

        runTestCase(0, "1111", 2);
        runTestCase(1, "0000", 0);
        runTestCase(2, "101", 4);
        runTestCase(3, "111", 3);
        runTestCase(4, "110", 1);

        var largeSb = new StringBuilder();
        for (int k = 0; k < 50000; k++) largeSb.append("10");
        runTestCase(5, largeSb.toString(), 100000);

        System.out.println("Testing Complete.");
    }

    private static void runTestCase(int id, String input, int expected) {
        var start = System.nanoTime();
        var result = getMinimumOperations(input);
        var end = System.nanoTime();

        var status = (result == expected) ? "PASS" : "FAIL";
        var displayInput = input.length() > 20 ? "Large Input (Len: " + input.length() + ")" : input;

        System.out.printf("Case %d: [%s] Input: %s -> Expected: %d, Got: %d (Time: %.3f ms)%n",
                id, status, displayInput, expected, result, (end - start) / 1e6);
    }
}