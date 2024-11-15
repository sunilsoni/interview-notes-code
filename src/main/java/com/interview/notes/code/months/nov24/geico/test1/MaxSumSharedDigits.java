package com.interview.notes.code.months.nov24.geico.test1;

import java.util.*;

public class MaxSumSharedDigits {
    public static int solution(int[] A) {
        Map<String, PriorityQueue<Integer>> digitMap = new HashMap<>();
        
        for (int num : A) {
            String key = getFirstLastDigits(num);
            digitMap.putIfAbsent(key, new PriorityQueue<>(Collections.reverseOrder()));
            digitMap.get(key).offer(num);
        }
        
        int maxSum = -1;
        for (PriorityQueue<Integer> pq : digitMap.values()) {
            if (pq.size() >= 2) {
                int sum = pq.poll() + pq.poll();
                maxSum = Math.max(maxSum, sum);
            }
        }
        
        return maxSum;
    }
    
    private static String getFirstLastDigits(int num) {
        String numStr = String.valueOf(num);
        return numStr.charAt(0) + "" + numStr.charAt(numStr.length() - 1);
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {130, 191, 200, 10},
            {405, 45, 300, 300},
            {50, 222, 49, 52, 25},
            {30, 909, 3190, 99, 3990, 9009},
            {1, 2, 3, 4, 5},  // Edge case: all single-digit numbers
            {100, 200, 300, 400, 500},  // Edge case: no matching pairs
            {11, 22, 33, 44, 55}  // Edge case: all numbers share first and last digit
        };
        
        int[] expectedOutputs = {140, 600, -1, 9918, -1, -1, 99};
        
        for (int i = 0; i < testCases.length; i++) {
            int result = solution(testCases[i]);
            boolean passed = result == expectedOutputs[i];
            System.out.println("Test case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("  Expected: " + expectedOutputs[i] + ", Got: " + result);
            }
        }
        
        // Large data input test
        int[] largeInput = new int[100000];
        Random rand = new Random();
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = 10 + rand.nextInt(999999990);
        }
        long startTime = System.currentTimeMillis();
        int largeResult = solution(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test: " + (largeResult != -1 ? "PASS" : "FAIL"));
        System.out.println("  Execution time: " + (endTime - startTime) + "ms");
    }
}
