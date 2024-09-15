package com.interview.notes.code.months.sept24.test7;

public class RangeGame1 {
    public static void main(String[] args) {
        System.out.println(solve(5)); // Expected output: 11
        System.out.println(solve(10)); // Additional test case
        System.out.println(solve(1)); // Edge case
        System.out.println(solve(1000000)); // Max input
    }

    static long solve(int n) {
        long result = 0;
        int position = 1;
        
        while (n > 1) {
            // Add odd-indexed targets to the result
            result += (long) position * ((n + 1) / 2);
            
            // Update for the next round
            position *= 2;
            n /= 2;
        }
        
        // Add the last remaining target
        if (n == 1) {
            result += position;
        }
        
        return result;
    }
}