package com.interview.notes.code.year.y2025.June.amazon.test6;

import java.util.*;
import java.util.stream.*;

public class RobotAllocation {

    /**
     * Returns the minimum number of robots to boost so that
     * boosted total > total initial of unboosted.
     */
    public static int calcMinimumAllocation(List<Integer> initialSpeed,
                                            List<Integer> extraBoost) {
        int n = initialSpeed.size();
        long totalInitial = initialSpeed.stream()
                                        .mapToLong(Integer::longValue)
                                        .sum();

        // compute gains = 2*initial + extra, sort descending
        List<Long> gains = IntStream.range(0, n)
            .mapToObj(i -> 2L * initialSpeed.get(i) + extraBoost.get(i))
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());

        long accumulated = 0;
        for (int i = 0; i < gains.size(); i++) {
            accumulated += gains.get(i);
            if (accumulated > totalInitial) {
                return i + 1;
            }
        }
        // in worst case all robots must be boosted
        return n;
    }

    /**
     * Simple main method to run predefined and large random tests.
     * Prints PASS/FAIL for each small case, and timing info for large case.
     */
    public static void main(String[] args) {
        // sample cases
        List<List<Integer>> inits = Arrays.asList(
            Arrays.asList(3, 4, 5, 6),
            Arrays.asList(2, 4, 6, 7, 10)
        );
        List<List<Integer>> boosts = Arrays.asList(
            Arrays.asList(3, 2, 1, 1),
            Arrays.asList(4, 1, 3, 7, 4)
        );
        int[] expected = {2, 2};

        for (int t = 0; t < inits.size(); t++) {
            int result = calcMinimumAllocation(inits.get(t), boosts.get(t));
            if (result == expected[t]) {
                System.out.println("Test " + t + " PASS");
            } else {
                System.out.println("Test " + t + " FAIL: expected "
                                   + expected[t] + ", got " + result);
            }
        }

        // large random test for performance
        final int N = 200_000;
        Random rnd = new Random(0);
        List<Integer> bigInit = new ArrayList<>(N);
        List<Integer> bigBoost = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            bigInit.add(rnd.nextInt(1_000_000_000) + 1);
            bigBoost.add(rnd.nextInt(1_000_000_000) + 1);
        }
        long start = System.nanoTime();
        int resLarge = calcMinimumAllocation(bigInit, bigBoost);
        long elapsedMs = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Large test done in " + elapsedMs
                           + "ms, result = " + resLarge);
    }
}