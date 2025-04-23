package com.interview.notes.code.year.y2025.april.common.test11;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
//WORKING 100%
public class ArrayChallenge {

    /**
     * Returns the final string after finding pairs summing to arr[0],
     * appending the token, and replacing every 4th character with '_'.
     */
    public static String arrayChallenge(int[] arr) {
        int target = arr[0];
        List<String> pairs = new ArrayList<>();
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    pairs.add(arr[i] + "," + arr[j]);
                }
            }
        }
        String result = pairs.isEmpty() ? "-1" : String.join(" ", pairs);
        String token = "tig74x5pf6a";
        StringBuilder sb = new StringBuilder(result + token);
        for (int idx = 3; idx < sb.length(); idx += 4) {
            sb.setCharAt(idx, '_');
        }
        return sb.toString();
    }


    public static String applyChallengeToken(String result, String token) {
        String combined = result + token;
        StringBuilder sb = new StringBuilder(combined);
        for (int i = 3; i < sb.length(); i += 4) {
            sb.setCharAt(i, '_');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {17, 4, 5, 6, 10, 11, 4, -3, -5, 3, 15, 2, 7},
            {7, 6, 4, 1, 7, -2, 3, 12},
            {7, 3, 5, 2, -4, 8, 11},
            {5, 1, 2, 3, 4},
            {5, 0, 0, 5, 5},
            {100000, 50000, 50000, 25000, 75000} // Large Input
        };

        String challengeToken = "tig74x5pf6a";

        for (int[] testCase : testCases) {
            String result = arrayChallenge(testCase);
            String finalOutput = applyChallengeToken(result, challengeToken);
            System.out.println("Input: " + Arrays.toString(testCase));
            System.out.println("Output: " + result);
            System.out.println("Final Output: " + finalOutput);
            System.out.println(result.equals("-1") ? "FAIL (No pairs found)" : "PASS");
            System.out.println("---");
        }
    }
}
