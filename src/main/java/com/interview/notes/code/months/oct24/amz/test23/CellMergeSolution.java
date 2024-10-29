package com.interview.notes.code.months.oct24.amz.test23;

import java.util.PriorityQueue;

public class CellMergeSolution {
    
    public static int minPower(int[] cells) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int cell : cells) {
            pq.offer(cell);
        }
        
        int totalPower = 0;
        while (pq.size() > 1) {
            int first = pq.poll();
            int second = pq.poll();
            int sum = first + second;
            totalPower += sum;
            pq.offer(sum);
        }
        
        return totalPower;
    }
    
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {20, 30, 40},
            {30, 10, 20},
            {100, 1},
            {1, 2, 3, 4, 5},
            {50, 50, 50, 50},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        
        int[] expectedOutputs = {140, 90, 101, 50, 350, 29};
        
        for (int i = 0; i < testCases.length; i++) {
            int result = minPower(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ": " +
                               (result == expectedOutputs[i] ? "PASS" : "FAIL") +
                               " (Expected: " + expectedOutputs[i] + ", Got: " + result + ")");
        }
        
        // Large data input test
        int[] largeInput = new int[100000];
        for (int i = 0; i < 100000; i++) {
            largeInput[i] = (int) (Math.random() * 100) + 1;
        }
        long startTime = System.currentTimeMillis();
        int largeResult = minPower(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test: PASS (Time taken: " + (endTime - startTime) + "ms, Result: " + largeResult + ")");
    }
}