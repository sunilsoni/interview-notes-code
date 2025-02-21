package com.interview.notes.code.year.y2025.feb25.imocha.test2;

import java.util.Arrays;

/*
MAX Value
You are given four integers, N, A, B, and C.
You need to perform some operations on N.
Divide Ninto maximum parts; each part should have lengths A, B, or C.
The numbers A, B, and C can coincide.
Find the maximum number of parts you can divide N into that satisfies the condition.
Function Description
In the provided code snippet, implement the provided MaxValue(...) method to find the maximum number of parts you can divide N into that satisfies the condition. You can write your code in the space below the phrase "WRITE YOUR LOGIC HERE"
There will be multiple test cases running so the Input and Output should match exactly as provided.
The base Output variable result is set to a default value of -404 which can be modified. Additionally, you can add or remove these output variables.
Input Format
The input contains four space-separated integers, N, A, B, and C.
Sample Input
5 5 3 2
-- denotes N, A, B, and C
Constraints
≤ N, A, B, C ≤ 4000
Output Format
The output contains an integer denoting the maximum number of parts you can divide N into that satisfies the condition.
Sample Output
2
Explanation
N= 5
A = 5
B = 3
C = 2
Nis divided such that the first part has length 2 and the second part has length 3.
The maximum number of parts you can divide N into that satisfies the condition is 2.
Hence, the output is 2.
 */
public class TestMaxValue {

    // Method to find the maximum number of parts
    // into which you can divide N using A, B, or C.
    public static int MaxValue(int N, int A, int B, int C) {
        // dp[i] holds the maximum number of parts that sum to i
        // or -404 if it's not possible to form i.
        int[] dp = new int[N + 1];
        Arrays.fill(dp, -404);

        // Base case: 0 length can be formed with 0 parts.
        dp[0] = 0;

        // Fill dp array from 1 to N.
        for (int i = 1; i <= N; i++) {
            if (i - A >= 0 && dp[i - A] != -404) {
                dp[i] = Math.max(dp[i], dp[i - A] + 1);
            }
            if (i - B >= 0 && dp[i - B] != -404) {
                dp[i] = Math.max(dp[i], dp[i - B] + 1);
            }
            if (i - C >= 0 && dp[i - C] != -404) {
                dp[i] = Math.max(dp[i], dp[i - C] + 1);
            }
        }

        // dp[N] is the final answer (or -404 if not possible).
        return dp[N];
    }

    public static void main(String[] args) {
        // Each test case: {N, A, B, C, expectedOutput}
        int[][] testCases = {
                // Test case from the image: N=5, A=5, B=3, C=2 => Expected 2
                {5, 5, 3, 2, 2},
                // Another example (if present in the problem explanation): N=5, A=2, B=5, C=3 => Expected 2
                {5, 2, 5, 3, 2}
        };

        for (int[] test : testCases) {
            int N = test[0];
            int A = test[1];
            int B = test[2];
            int C = test[3];
            int expected = test[4];

            int result = MaxValue(N, A, B, C);
            String passFail = (result == expected) ? "PASS" : "FAIL";

            System.out.println(
                    "Input: " + N + " " + A + " " + B + " " + C +
                            " | Output: " + result +
                            " | Expected: " + expected +
                            " | " + passFail
            );
        }
    }
}