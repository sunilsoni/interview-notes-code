package com.interview.notes.code.months.aug24.amz.test15;

import java.util.Arrays;
import java.util.List;

/*
WORKING:


1. Code Question 1
Amazon recently conducted interviews where the candidates were asked to sort the permutation p of length n. The ith candidate sorted the permutation in moves[i] moves. To verify the results once more, the interviewers want to find if it is possible to sort the permutation in the given number of moves. Given the original permutation array p and the number of moves made by each of the q candidates, find whether you can sort the permutation p by performing exactly moves[i] moves.
In one move you can swap values at any two distinct indices. Return the answer as a binary string of length q. The value at the jth index should be 1 if it is possible to sort the permutation p in exactly moves[i] moves, or 0 otherwise.
Note: A permutation is a sequence of n distinct integers such that each integer between [1, n] appears exactly once. For example, [1,3,2,4] is a permutation of size 4, but [1,3,4,5] or [1,2,2,4] are not.
Example
Let n = 4, p = [2,3,1,4], q = 2, moves = [2,3].

• In the first query, moves[0] = 2, We can sort the given permutation in exactly 2 moves,
• Swap Oth and 2nd index, p = [1,3,2,4]
• Swap 1st and 2nd index, p = [1,2,3,4]
• In the second query, moves[1] = 3, It can be shown that It is not possible to sort the given permutation in exactly 3 moves.
The answer is the string "10".
4-746256e19dba@ho
Function Description
Complete the function sortPermutation in the editor below.
sortPermutation has the following parameters:
int p[n]: the original permutation
int moves[q]: number of moves each candidate made
Returns
¿R.
string: a binary string of answers to the queries
Constraints

• 1 ≤n≤ 105
• 1 ≤9≤105
• 1 ≤ moves[i] ≤ 109
• It is guaranteed that p forms a permutation.

 */
public class Solution {

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
                    x = p.get(x) - 1; // Move to the next index in the cycle
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
            if (move >= minSwaps && (move - minSwaps) % 2 == 0) {
                result.append('1');
            } else {
                result.append('0');
            }
        }

        return result.toString();
    }


}
