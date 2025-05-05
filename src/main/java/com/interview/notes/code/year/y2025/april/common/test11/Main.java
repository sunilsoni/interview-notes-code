package com.interview.notes.code.year.y2025.april.common.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static String ArrayChallenge(int[] arr) {
        int targetSum = arr[0];
        List<String> pairs = new ArrayList<>();

        // Iterate over the array starting from the second element
        for (int i = 1; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == targetSum) {
                    pairs.add(arr[i] + "," + arr[j]);
                }
            }
        }

        // Return -1 if no pairs are found
        if (pairs.isEmpty()) {
            return "-1";
        }

        // Join pairs with spaces and return the result
        String result = String.join(" ", pairs);

        // Concatenate with ChallengeToken and replace every fourth character with _
        String challengeToken = "tig74x5pf6a";
        String finalResult = result + challengeToken;
        StringBuilder modifiedResult = new StringBuilder();
        for (int i = 0; i < finalResult.length(); i++) {
            if ((i + 1) % 4 == 0) {
                modifiedResult.append("_");
            } else {
                modifiedResult.append(finalResult.charAt(i));
            }
        }

        return modifiedResult.toString();
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {7, 3, 5, 2, -4, 8, 11},
                {17, 4, 5, 6, 10, 11, 4, -3, -5, 3, 15, 2, 7}
        };

        for (int[] testCase : testCases) {
            String result = ArrayChallenge(testCase);
            System.out.println("Input: " + Arrays.toString(testCase));
            System.out.println("Output: " + result);
            System.out.println();
        }
    }
}