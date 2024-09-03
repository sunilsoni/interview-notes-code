package com.interview.notes.code.months.aug24.test29;

import java.util.*;

/*
Brothers' Game
Two brothers were playing a game. Their mother gave them an array of numbers, with each element being either 0 or 1. She then asked them to find out the maximum number of 1s they could obtain by inversion of the bits, i.e., changing all the Os to 1 and all the is to 0 for any contiguous portion of the array.
The younger brother thought he could solve the problem in his head, but the elder brother chose to write a program to find the optimal solution. Can you help the elder brother?
Input
The first line of input contains an integer N, representing the size of the array.
The second line of input contains N space-separated integers, representing the array elements.
Output
The output is the maximum number of 1s which you can make in one inversion.
Constraints
1 <= N <= 100
Time Complexity
Optimize your solution for at least O(N); otherwise, some large test cases may fail.
Example #1
Input
5
01001
 */
public class BrothersGame {
    public static int solve(List<Integer> ar) {
        if (ar.size() <= 2) {
            return 0;
        }

        int n = ar.size();
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + 1;  // Remove current element by default

            if (!isMonotonic(ar.get(i - 2), ar.get(i - 1), ar.get(i))) {
                dp[i] = Math.min(dp[i], dp[i - 2]);
            }
        }

        return dp[n - 1];
    }

    private static boolean isMonotonic(int a, int b, int c) {
        return (a <= b && b <= c) || (a >= b && b >= c);
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> ar1 = Arrays.asList(1, 2, 4, 1, 2);
        System.out.println(solve(ar1));  // Expected output: 1

        // Test case 2
        List<Integer> ar2 = Arrays.asList(1, 2, 3, 5);
        System.out.println(solve(ar2));  // Expected output: 2

        // Additional test cases
        List<Integer> ar3 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(solve(ar3));  // Expected output: 3

        List<Integer> ar4 = Arrays.asList(5, 4, 3, 2, 1);
        System.out.println(solve(ar4));  // Expected output: 3

        List<Integer> ar5 = Arrays.asList(1, 3, 2, 4);
        System.out.println(solve(ar5));  // Expected output: 0

        List<Integer> ar6 = Arrays.asList(1);
        System.out.println(solve(ar6));  // Expected output: 0
    }
}
