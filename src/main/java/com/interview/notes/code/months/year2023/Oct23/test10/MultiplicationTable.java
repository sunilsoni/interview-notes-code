package com.interview.notes.code.months.year2023.Oct23.test10;

public class MultiplicationTable {

    public static int countOccurrences(int N, int X) {
        int count = 0;
        for (int i = 1; i <= Math.min(N, Math.sqrt(X)); i++) {
            if (X % i == 0) {  // Check if X is divisible by i
                int j = X / i;  // Calculate j
                if (j <= N) {
                    count += 2;  // Increment count by 2 for each valid pair (i, j) and (j, i)
                    if (i == j) {
                        count--;  // Decrement count by 1 if i equals j to correct for overcounting
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int N = 6;
        int X = 12;
        int result = countOccurrences(N, X);
        System.out.println(result);  // Output: 4
    }
}
