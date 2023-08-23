package com.interview.notes.code.Aug23.test2;

public class LongestChain1 {

    public static void main(String[] args) {
        int maxChainLength = 0;
        int startingNumber = 0;

        for (int i = 1; i < 10000; i++) {
            int chainLength = calculateChainLength(i);
            if (chainLength > maxChainLength) {
                maxChainLength = chainLength;
                startingNumber = i;
            }
        }

        System.out.println("Starting number under 10000 that produces the longest chain: " + startingNumber);
    }

    public static int calculateChainLength(int n) {
        int chainLength = 1;
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
            chainLength++;
        }
        return chainLength;
    }
}
