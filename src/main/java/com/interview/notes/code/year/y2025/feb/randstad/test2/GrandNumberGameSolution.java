package com.interview.notes.code.year.y2025.feb.randstad.test2;

import java.util.ArrayList;
import java.util.List;

public class GrandNumberGameSolution {

    // Memoization array
    private static int[] memo;
    private static int[][] gcdTable;

    // Returns gcd of two numbers
    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    // DP approach with bitmask
    private static int dfs(int mask, int[] nums, int N) {
        if (mask == (1 << (2 * N)) - 1) {
            return 0;
        }
        if (memo[mask] != -1) {
            return memo[mask];
        }
        int usedPairs = Integer.bitCount(mask) / 2;
        int op = usedPairs + 1;
        int best = 0;
        for (int i = 0; i < 2 * N; i++) {
            if ((mask & (1 << i)) == 0) {
                for (int j = i + 1; j < 2 * N; j++) {
                    if ((mask & (1 << j)) == 0) {
                        int newMask = mask | (1 << i) | (1 << j);
                        int val = op * gcdTable[i][j] + dfs(newMask, nums, N);
                        if (val > best) {
                            best = val;
                        }
                    }
                }
                break;
            }
        }
        memo[mask] = best;
        return best;
    }

    // Main solver
    public static int solve(List<Integer> arr) {
        int size = arr.size();
        int N = size / 2;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = arr.get(i);
        }
        gcdTable = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                gcdTable[i][j] = gcd(nums[i], nums[j]);
                gcdTable[j][i] = gcdTable[i][j];
            }
        }
        memo = new int[1 << size];
        for (int i = 0; i < (1 << size); i++) {
            memo[i] = -1;
        }
        return dfs(0, nums, N);
    }

    // Simple test inside main
    public static void main(String[] args) {

        // Test data
        List<int[]> testCases = new ArrayList<>();
        List<Integer> expectedResults = new ArrayList<>();

        // Example #1
        testCases.add(new int[]{2, 3, 4, 9, 5});
        expectedResults.add(7);

        // Example #2
        testCases.add(new int[]{3, 1, 2, 3, 4, 5, 6});
        expectedResults.add(14);

        // Additional small test
        testCases.add(new int[]{1, 10, 10});
        expectedResults.add(10); // 1 * gcd(10,10)=10

        // Larger random test (just to ensure no crash/time out)
        // We'll just check if it runs quickly without a known expected result
        int[] largeTest = new int[20]; // 2N=20 => N=10
        largeTest[0] = 10;
        largeTest[1] = 100;
        largeTest[2] = 1000;
        largeTest[3] = 999999999;
        largeTest[4] = 123456789;
        largeTest[5] = 12345;
        largeTest[6] = 4321;
        largeTest[7] = 987654321;
        largeTest[8] = 999999998;
        largeTest[9] = 777777777;
        largeTest[10] = 111111111;
        largeTest[11] = 222222222;
        largeTest[12] = 333333333;
        largeTest[13] = 444444444;
        largeTest[14] = 555555555;
        largeTest[15] = 666666666;
        largeTest[16] = 42;
        largeTest[17] = 24;
        largeTest[18] = 314159265;
        largeTest[19] = 271828182;
        testCases.add(largeTest);
        expectedResults.add(null);

        for (int i = 0; i < testCases.size(); i++) {
            int[] input = testCases.get(i);
            int N = input[0];
            List<Integer> arr = new ArrayList<>();
            for (int j = 1; j < input.length; j++) {
                arr.add(input[j]);
            }
            int result = solve(arr);
            Integer expected = expectedResults.get(i);
            if (expected == null) {
                System.out.println("Test #" + (i + 1) + ": Result=" + result + " (No expected, just checking runtime)");
            } else {
                System.out.println("Test #" + (i + 1) + ": " + (result == expected ? "PASS" : ("FAIL - got " + result + ", expected " + expected)));
            }
        }
    }
}
