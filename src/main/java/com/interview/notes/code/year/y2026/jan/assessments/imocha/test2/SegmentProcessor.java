package com.interview.notes.code.year.y2026.jan.assessments.imocha.test2;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class SegmentProcessor {

    public static String removeNodes(int n, int[] nodes) {
        return Arrays.stream(nodes)
                .boxed()
                .collect(Collectors.groupingBy(k -> k, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() % 2 == 0)
                .flatMap(entry -> Collections.nCopies(entry.getValue().intValue(), entry.getKey()).stream())
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        // Test Case 1: Sample Input
        // 1(2-even), 2(3-odd), 3(1-odd), 4(2-even), 5(1-odd) -> Keep 1s and 4s
        runTest("Sample Input", 9, new int[]{1, 1, 2, 2, 2, 3, 4, 4, 5}, "1 1 4 4");

        // Test Case 2: Logic Validation
        // 10(2-even), 20(2-even), 30(3-odd) -> Keep 10s and 20s
        runTest("Logic Check", 7, new int[]{10, 10, 20, 20, 30, 30, 30}, "10 10 20 20");

        // Test Case 3: Large Data (100,000 nodes)
        // 99,998 ones (even-keep), 2 twos (even-keep)
        int largeSize = 100000;
        int[] largeInput = new int[largeSize];
        Arrays.fill(largeInput, 0, 99998, 1);
        Arrays.fill(largeInput, 99998, 100000, 2);
        
        // Construct expected output for validation
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<99998; i++) sb.append("1 ");
        sb.append("2 2");
        
        runTest("Large Data Input", largeSize, largeInput, sb.toString());
    }

    private static void runTest(String testName, int n, int[] input, String expected) {
        try {
            long startTime = System.currentTimeMillis();
            String result = removeNodes(n, input);
            long endTime = System.currentTimeMillis();
            
            boolean isPassed = result.trim().equals(expected.trim());
            System.out.printf("[%s] Test %s: %s (Time: %d ms)%n", 
                isPassed ? "PASS" : "FAIL", testName, isPassed ? "Success" : "Failed", (endTime - startTime));
            
        } catch (Exception e) {
            System.out.println("[FAIL] " + testName + " Exception: " + e.getMessage());
        }
    }
}