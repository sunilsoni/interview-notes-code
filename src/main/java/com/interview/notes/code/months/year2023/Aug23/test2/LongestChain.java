package com.interview.notes.code.months.year2023.Aug23.test2;

/**
 * As part of the interview process, you are asked to create a Java application.
 * The following iterative sequence is defined for the set of positive integers:
 * n —> n/2 (n is even)
 * n —* 3n + 1 (n is odd)
 * Using the rule above and starting with 13, we generate the following sequence:
 * 13 — 40-20 — 10 — 5 — 16-8 — 4^2 — 1
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although
 * been proved yet, consider that all starting numbers finish at 1.
 * Which starting number, under 10000, produces the longest chain?
 */
public class LongestChain {

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
        System.out.println("Chain length: " + maxChainLength);
        System.out.println("Chain values: " + generateChain(startingNumber));
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

    public static String generateChain(int n) {
        StringBuilder chain = new StringBuilder();
        while (n != 1) {
            chain.append(n).append(" -> ");
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = 3 * n + 1;
            }
        }
        chain.append(1);
        return chain.toString();
    }
}
