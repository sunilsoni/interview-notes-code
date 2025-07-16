package com.interview.notes.code.year.y2025.july.hackerank.test6;

import java.util.*;

/**
 * DigitSumLottery solves the ticket lottery problem:
 * tickets from lowLimit to highLimit have coupon codes equal to digit sums.
 * We need the number of digit sums that occur most often (ways)
 * and that maximum occurrence count (maxCount).
 */
public class DigitSumLottery {

    /**
     * Compute the result [ways, maxCount] for the input range.
     * @param lowLimit inclusive lower bound of ticket numbers
     * @param highLimit inclusive upper bound of ticket numbers
     * @return List of two elements: [number of sums with max frequency, max frequency]
     */
    public static List<Long> waysToChooseSum(long lowLimit, long highLimit) {
        // assume both bounds are inclusive, as per examples
        // get distribution of digit sums for [0, highLimit]
        long[] highDist = countDigitSumDistribution(highLimit);
        // get distribution of digit sums for [0, lowLimit - 1]
        long[] lowDist  = countDigitSumDistribution(lowLimit - 1);

        int n = highDist.length;        // possible sums: 0..n-1
        long maxCount = 0;             // track highest ticket count for any sum
        long ways     = 0;             // count of sums that reach maxCount

        // for each possible digit sum, find count in [lowLimit, highLimit]
        for (int sum = 0; sum < n; sum++) {
            long count = highDist[sum] - (sum < lowDist.length ? lowDist[sum] : 0);
            if (count > maxCount) {
                maxCount = count;  // new highest frequency
                ways     = 1;      // reset ways to 1
            } else if (count == maxCount) {
                ways++;            // another sum ties for highest
            }
        }
        // return as a list [ways, maxCount]
        return Arrays.asList(ways, maxCount);
    }

    /**
     * Digit DP: count how many numbers from 0..N have each possible digit sum.
     * @param N upper bound (inclusive)
     * @return array dist where dist[s] = count of numbers with digit sum s
     */
    private static long[] countDigitSumDistribution(long N) {
        if (N < 0) {
            return new long[0];   // no numbers in negative range
        }
        String s = Long.toString(N);   // digits of N, most significant first
        int len    = s.length();       // number of digits
        int maxSum = 9 * len;         // maximum possible digit sum

        // dpPrev[tight][sum] = number of ways to build prefix
        long[][] dpPrev = new long[2][maxSum + 1];
        dpPrev[1][0] = 1;              // start with tight = true and sum = 0

        // process each digit position
        for (int pos = 0; pos < len; pos++) {
            long[][] dpCurr = new long[2][maxSum + 1];
            int digit = s.charAt(pos) - '0'; // current digit of N

            for (int tight = 0; tight <= 1; tight++) {
                int limit = tight == 1 ? digit : 9;  // if tight, can't exceed this digit
                for (int sum = 0; sum <= maxSum; sum++) {
                    long ways = dpPrev[tight][sum];
                    if (ways == 0) continue;         // skip impossible states
                    // try all next digits from 0..limit
                    for (int d = 0; d <= limit; d++) {
                        int nextTight = (tight == 1 && d == limit) ? 1 : 0;
                        dpCurr[nextTight][sum + d] += ways;
                    }
                }
            }
            dpPrev = dpCurr;  // move to next position
        }

        // combine both tight=0 and tight=1 states for final counts
        long[] dist = new long[maxSum + 1];
        for (int sum = 0; sum <= maxSum; sum++) {
            dist[sum] = dpPrev[0][sum] + dpPrev[1][sum];
        }
        return dist;
    }

    /**
     * Main method to test various cases without JUnit.
     * Prints PASS or FAIL for each test, plus a performance check.
     */
    public static void main(String[] args) {
        // prepare sample test cases with expected output
        List<TestCase> tests = Arrays.asList(
            new TestCase(1,   5,  5, 1),  // sample 0
            new TestCase(3,  12,  1, 2),  // sample 1
            new TestCase(1,  10,  1, 2)   // example in description
        );

        // execute each test
        for (TestCase t : tests) {
            List<Long> out = waysToChooseSum(t.low, t.high);
            boolean pass = out.get(0).equals(t.expWays)
                        && out.get(1).equals(t.expMaxCount);
            // print formatted result
            System.out.printf("Test [%d, %d]: expected=(%d,%d), got=(%d,%d) => %s%n",
                    t.low, t.high,
                    t.expWays, t.expMaxCount,
                    out.get(0), out.get(1),
                    pass ? "PASS" : "FAIL");
        }

        // performance check on a large range
        long start = System.currentTimeMillis();
        waysToChooseSum(1, 1_000_000_000_000L);
        System.out.println("Large test time: "
                + (System.currentTimeMillis() - start) + " ms");
    }

    // helper class to store test parameters and expected results
    private static class TestCase {
        long low, high, expWays, expMaxCount;
        TestCase(long low, long high, long w, long m) {
            this.low = low;
            this.high = high;
            this.expWays = w;
            this.expMaxCount = m;
        }
    }
}