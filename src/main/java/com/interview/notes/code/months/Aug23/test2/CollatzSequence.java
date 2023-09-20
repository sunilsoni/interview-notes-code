package com.interview.notes.code.months.Aug23.test2;

public class CollatzSequence {

    private static final int LIMIT = 10000;
    private static long[] memo = new long[LIMIT];

    public static void main(String[] args) {
        int maxLength = 0;
        int number = 0;

        // Iterate through all the numbers under 10,000
        for (int i = 1; i < LIMIT; i++) {
            int length = findLength(i);
            if (length > maxLength) {
                maxLength = length;
                number = i;
            }
        }

        System.out.println("Starting number under 10000 producing the longest chain is: " + number);
        System.out.println("Length of the chain: " + maxLength);
    }

    private static int findLength(int n) {
        // Base case
        if (n == 1) return 1;

        // If the value has been computed before, return it
        if (n < LIMIT && memo[n] != 0) return (int) memo[n];

        // Calculate the next number in the sequence
        long next = (n % 2 == 0) ? n / 2 : 3 * n + 1;

        // Recursively find the length of the sequence
        int length = 1 + findLength((int) next);

        // If the value fits in our memo array, store it
        if (n < LIMIT) memo[n] = length;

        return length;
    }
}
