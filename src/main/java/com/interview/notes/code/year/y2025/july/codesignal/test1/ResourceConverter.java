package com.interview.notes.code.year.y2025.july.codesignal.test1;

import java.util.Random;

public class ResourceConverter {

    /**
     * Simulates the cycles until no more events can happen.
     *
     * @param resources      array of "A" and "P" (all "A"s in front, "P"s in back)
     * @param conversionRate number of "P"s that can be removed to produce one "A"
     * @return number of cycles until the process halts
     */
    public static int solution(String[] resources, int conversionRate) {
        // Count initial A's and P's
        int countA = 0, countP = 0;
        for (String s : resources) {
            if ("A".equals(s)) countA++;
            else               countP++;
        }

        int cycles = 0;
        // Each cycle we try Option 1, then Option 2, else break
        while (true) {
            if (countP >= conversionRate) {
                // Option 1: remove conversionRate P's, add one A
                countP    -= conversionRate;
                countA    += 1;
                cycles++;
            }
            else if (countA >= 1) {
                // Option 2: turn one A into P
                countA--;
                countP++;
                cycles++;
            }
            else {
                // Option 3: halt
                break;
            }
        }
        return cycles;
    }

    /** Helper to run & report a single test case. */
    private static void testCase(String[] resources, int rate, int expected, int id) {
        int got = solution(resources, rate);
        String passFail = (got == expected) ? "PASS" : "FAIL";
        System.out.printf(
            "Test %2d [%s, rate=%d]: %s (expected %d, got %d)%n",
            id, String.join("", resources), rate, passFail, expected, got
        );
    }

    public static void main(String[] args) {
        // —— Provided examples —— 
        testCase(new String[]{"A","A","A","P","P","P"}, 2, 13, 1);
        testCase(new String[]{"A","A"},             2,  4, 2);
        testCase(new String[]{"P","P","P"},         3,  2, 3);

        // —— Edge / small cases —— 
        testCase(new String[]{"A","P"},             2,  3, 4);
        testCase(new String[]{"P","P"},             3,  0, 5);
        testCase(new String[]{"A","A","P","P"},     5,  4, 6);

        // —— Randomized stress test —— 
        int N = 500;
        String[] large = new String[N];
        Random rnd = new Random(123);
        for (int i = 0; i < N; i++) {
            large[i] = (i < N/2 ? "A" : "P");
        }
        long start = System.currentTimeMillis();
        int cycles = solution(large, 5);
        long millis = System.currentTimeMillis() - start;
        System.out.printf(
            "Large test (N=%d, rate=5): %d cycles in %d ms%n",
            N, cycles, millis
        );
    }
}