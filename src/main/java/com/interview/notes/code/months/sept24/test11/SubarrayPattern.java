package com.interview.notes.code.months.sept24.test11;

import java.util.*;

public class SubarrayPattern {
    public static void main(String[] args) {
        // Example test case
        int[] numbers = {4, 1, 3, 4, 4, 5, 1};
        int[] pattern = {1, 0, -1};
        
        System.out.println(solution(numbers, pattern)); // Expected output: 1
    }

    public static int solution(int[] numbers, int[] pattern) {
        int count = 0;
        
        // Sliding window to check all subarrays of length pattern.length + 1
        for (int i = 1; i <= numbers.length - pattern.length; i++) {
            if (matches(numbers, pattern, i)) {
                count++;
            }
        }
        
        return count;
    }
    
    // Helper method to check if subarray starting from index `i-1` matches the pattern
    public static boolean matches(int[] numbers, int[] pattern, int start) {
        for (int j = 0; j < pattern.length; j++) {
            int prev = numbers[start - 1 + j]; // Previous number in the subarray
            int curr = numbers[start + j];     // Current number in the subarray
            
            if (pattern[j] == 1 && curr <= prev) return false;
            if (pattern[j] == 0 && curr != prev) return false;
            if (pattern[j] == -1 && curr >= prev) return false;
        }
        return true;
    }
}
