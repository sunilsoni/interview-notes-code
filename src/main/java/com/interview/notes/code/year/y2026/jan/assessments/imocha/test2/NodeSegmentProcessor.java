package com.interview.notes.code.year.y2026.jan.assessments.imocha.test2;

import java.util.Arrays;

public class NodeSegmentProcessor {

    public static String processSegments(int n, int[] nodes) {
        var sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && nodes[j] == nodes[i]) {
                j++;
            }
            int count = j - i;
            if (count % 2 == 0) {
                sb.append((nodes[i] + " ").repeat(count));
            }
            i = j;
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        runTest("Sample Case", new int[]{1, 1, 2, 2, 2, 3, 4, 4, 5}, "1 1 4 4");
        runTest("All Pairs", new int[]{10, 10, 20, 20}, "10 10 20 20");
        runTest("Single Odd Segment", new int[]{5, 5, 5}, "");

        int largeSize = 100_000;
        int[] largeData = new int[largeSize];
        Arrays.fill(largeData, 0, 50000, 1);
        Arrays.fill(largeData, 50000, 100000, 2);

        long start = System.currentTimeMillis();
        String res = processSegments(largeSize, largeData);
        long end = System.currentTimeMillis();

        boolean largePass = res.length() > 0 && (end - start) < 200;
        System.out.println("Large Data (10^5 elements): " + (largePass ? "PASS" : "FAIL") + " [" + (end - start) + "ms]");
    }

    private static void runTest(String name, int[] input, String expected) {
        String actual = processSegments(input.length, input);
        boolean pass = expected.equals(actual);
        System.out.println(name + ": " + (pass ? "PASS" : "FAIL"));
        if (!pass) System.out.printf(" Expected: '%s'%n Actual:   '%s'%n", expected, actual);
    }
}