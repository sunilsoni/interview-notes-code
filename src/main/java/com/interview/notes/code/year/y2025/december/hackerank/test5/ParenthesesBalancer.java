package com.interview.notes.code.year.y2025.december.hackerank.test5;

public class ParenthesesBalancer {

    public static int getMin(String s) {
        int open = 0;
        int insertions = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '（') {
                open++;
            } else {
                if (open > 0) {
                    open--;
                } else {
                    insertions++;
                }
            }
        }
        return open + insertions;
    }

    public static void main(String[] args) {
        runTest("(()))", 1, 1);
        runTest("))((", 4, 2);
        runTest("()", 0, 3);
        runTest("((()))", 0, 4);
        runTest("(((", 3, 5);
        runTest(")))", 3, 6);
        runTest("", 0, 7);
        runTest("(", 1, 8);
        runTest(")", 1, 9);
        runTest("()()", 0, 10);
        runTest(")(", 2, 11);
        runTest(")))()()()()()))))(())))))))()()))()())))))))(((", 29, 12);
        String large = "(".repeat(50000) + ")".repeat(50000);
        runTest(large, 0, 13);
        String allOpen = "(".repeat(100000);
        runTest(allOpen, 100000, 14);
        String allClose = ")".repeat(100000);
        runTest(allClose, 100000, 15);
    }

    static void runTest(String input, int expected, int testNum) {
        long start = System.currentTimeMillis();
        int result = getMin(input);
        long time = System.currentTimeMillis() - start;
        String status = result == expected ? "PASS" : "FAIL";
        System.out.printf("Test %d: %s | Expected: %d, Got: %d | Time: %dms%n",
                          testNum, status, expected, result, time);
    }
}