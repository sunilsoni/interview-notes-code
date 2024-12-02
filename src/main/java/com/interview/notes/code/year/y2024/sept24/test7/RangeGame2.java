package com.interview.notes.code.year.y2024.sept24.test7;

public class RangeGame2 {
    static int solve(int N) {
        long totalSum = (long) N * (N + 1) / 2;
        long lastLabel = Integer.highestOneBit(N);
        return (int) (totalSum - lastLabel);
    }

    public static void main(String[] args) {
        // Example test case
        int n = 5; // You can modify this as per other test cases
        System.out.println(solve(n)); // Should print 11 for the sample input
    }
}
