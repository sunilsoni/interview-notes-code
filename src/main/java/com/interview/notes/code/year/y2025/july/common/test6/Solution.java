package com.interview.notes.code.year.y2025.july.common.test6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {

    /**
     * Complete the 'getUmbrellas' function below.
     * <p>
     * The function is expected to return an INTEGER.
     * The function accepts the following parameters:
     * 1. INTEGER requirement
     * 2. INTEGER_ARRAY sizes
     */
    public static int getUmbrellas(int requirement, List<Integer> sizes) {
        // Sentinel “infinity” (you’ll never need more than `requirement` umbrellas)
        final int INF = requirement + 1;

        // dp[i] = min umbrellas to cover exactly i people
        int[] dp = new int[requirement + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;  // zero people → zero umbrellas

        for (int i = 1; i <= requirement; i++) {
            // copy i into an effectively-final variable for the lambda
            final int target = i;

            // find best dp[target - size] among all sizes that fit
            int bestPrev = sizes.stream()
                    .filter(size -> size <= target && dp[target - size] < INF)
                    .mapToInt(size -> dp[target - size])
                    .min()
                    .orElse(INF);

            // if we found a reachable remainder, add one umbrella
            dp[target] = (bestPrev < INF ? bestPrev + 1 : INF);
        }

        return dp[requirement] < INF ? dp[requirement] : -1;
    }

    /**
     * Simple main method to run through test cases and print PASS/FAIL.
     */
    public static void main(String[] args) {
        // Define test inputs
        int[] requirements = {5, 8, 7, 4, 1};
        List<List<Integer>> allSizes = Arrays.asList(
                Arrays.asList(3, 5),
                Arrays.asList(3, 5),
                Arrays.asList(3, 5),
                Arrays.asList(2, 4),
                List.of(2)
        );
        int[] expected = {1, 2, -1, 1, -1};

        // Run provided test cases
        for (int i = 0; i < requirements.length; i++) {
            int req = requirements[i];
            List<Integer> sizes = allSizes.get(i);
            int result = getUmbrellas(req, sizes);
            if (result == expected[i]) {
                System.out.println("Test case " + i + ": PASS");
            } else {
                System.out.println(
                        "Test case " + i + ": FAIL (expected "
                                + expected[i] + ", got " + result + ")"
                );
            }
        }

        // Test with a large requirement to verify performance
        int largeReq = 1000;
        // Generate sizes [1, 2, 3, ..., 1000]
        List<Integer> largeSizes = IntStream.rangeClosed(1, largeReq)
                .boxed()
                .collect(Collectors.toList());
        // We expect 1 umbrella of size 1000 to cover exactly 1000 people
        int largeResult = getUmbrellas(largeReq, largeSizes);
        if (largeResult == 1) {
            System.out.println("Large-data test: PASS");
        } else {
            System.out.println("Large-data test: FAIL (got " + largeResult + ")");
        }
    }
}