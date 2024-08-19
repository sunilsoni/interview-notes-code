package com.interview.notes.code.months.aug24.amz.test15;

import java.util.Arrays;
import java.util.List;

public class Solution3 {

    public static String sortPermutationOLD(List<Integer> p, List<Integer> moves) {
        StringBuilder result = new StringBuilder();
        int inversions = countInversions(p);

        for (int move : moves) {
            if (canSort(inversions, move)) {
                result.append("1");
            } else {
                result.append("0");
            }
        }

        return result.toString();
    }

    private static boolean canSort(int inversions, int moves) {
        // We can sort if:
        // 1. We have enough moves (moves >= inversions)
        // 2. The parity of moves and inversions is the same
        // 3. Or if we have more moves than necessary, we can make additional swaps to match the parity
        return moves >= inversions && ((moves - inversions) % 2 == 0);
    }

    private static int countInversions(List<Integer> p) {
        int inversions = 0;
        int n = p.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (p.get(i) > p.get(j)) {
                    inversions++;
                }
            }
        }
        return inversions;
    }

    public static void main(String[] args) {
        // Test Case 0
        List<Integer> p0 = Arrays.asList(4, 5, 1, 3, 2);
        List<Integer> moves0 = Arrays.asList(1, 2, 3);
        System.out.println(sortPermutation(p0, moves0));  // Expected: 001

        // Test Case 1
        List<Integer> p1 = Arrays.asList(3, 1, 2);
        List<Integer> moves1 = Arrays.asList(2, 4);
        System.out.println(sortPermutation(p1, moves1));  // Expected: 11

        // Additional Test Case
        List<Integer> p2 = Arrays.asList(2, 3, 1, 4);
        List<Integer> moves2 = Arrays.asList(2, 3);
        System.out.println(sortPermutation(p2, moves2));  // Expected: 10


        // Additional Test Case
        List<Integer> p3 = Arrays.asList(4, 5, 1, 3, 2);
        List<Integer> moves3 = Arrays.asList(1, 2, 3);
        System.out.println(sortPermutation(p3, moves3));  // Expected: 001
    }

    public static String sortPermutation(List<Integer> p, List<Integer> moves) {
        int n = p.size();
        boolean[] visited = new boolean[n];
        int minSwaps = 0;

        // Calculate the minimum number of swaps needed to sort the permutation
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int cycleSize = 0;
                int x = i;
                while (!visited[x]) {
                    visited[x] = true;
                    x = p.get(x) - 1; // Move to the next index
                    cycleSize++;
                }
                if (cycleSize > 1) {
                    minSwaps += (cycleSize - 1);
                }
            }
        }

        // Build the result string
        StringBuilder result = new StringBuilder();
        for (int move : moves) {
            result.append(move == minSwaps ? '1' : '0');
        }

        return result.toString();
    }

}
