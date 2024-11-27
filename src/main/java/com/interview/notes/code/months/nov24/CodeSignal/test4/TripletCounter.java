package com.interview.notes.code.months.nov24.CodeSignal.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TripletCounter {
    public static int[] solution(String[] queries, int diff) {
        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Integer> triplets = new HashMap<>();
        int[] result = new int[queries.length];
        long totalTriplets = 0;

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            char op = query.charAt(0);
            int x = Integer.parseInt(query.substring(1));

            if (op == '+') {
                // Update counts and triplets before increasing count[x]
                int cX = count.getOrDefault(x, 0);
                int cXMinusDiff = count.getOrDefault(x - diff, 0);
                int cXPlusDiff = count.getOrDefault(x + diff, 0);

                // Triplets where x - diff is the middle element
                if (cXMinusDiff > 0 && cXPlusDiff > 0) {
                    int delta = cXMinusDiff * cXPlusDiff;
                    totalTriplets += delta;
                }

                // Update counts
                count.put(x, cX + 1);

            } else {
                // Update counts and triplets before decreasing count[x]
                int cX = count.get(x);
                int cXMinusDiff = count.getOrDefault(x - diff, 0);
                int cXPlusDiff = count.getOrDefault(x + diff, 0);

                // Triplets where x is the middle element
                if (cXMinusDiff > 0 && cXPlusDiff > 0) {
                    int delta = cXMinusDiff * cXPlusDiff;
                    totalTriplets -= delta;
                }

                // Update counts
                if (cX == 1) {
                    count.remove(x);
                } else {
                    count.put(x, cX - 1);
                }
            }

            result[i] = (int) totalTriplets;
        }

        return result;
    }

    // Method to compare expected and actual outputs
    public static boolean testSolution(String[] queries, int diff, int[] expectedOutput) {
        int[] actualOutput = solution(queries, diff);
        boolean isEqual = Arrays.equals(actualOutput, expectedOutput);
        if (!isEqual) {
            System.out.println("Expected: " + Arrays.toString(expectedOutput));
            System.out.println("Actual:   " + Arrays.toString(actualOutput));
        }
        return isEqual;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test Case 1
        String[] queries1 = {"+4", "+5", "+6", "+4", "+3", "-4"};
        int diff1 = 1;
        int[] expected1 = {0, 0, 1, 2, 4, 0};
        boolean result1 = testSolution(queries1, diff1, expected1);
        System.out.println("Test Case 1: " + (result1 ? "Pass" : "Fail"));

        // Test Case 2
        String[] queries2 = {"+1", "+2", "+3", "+4", "+5"};
        int diff2 = 1;
        int[] expected2 = {0, 0, 1, 3, 6};
        boolean result2 = testSolution(queries2, diff2, expected2);
        System.out.println("Test Case 2: " + (result2 ? "Pass" : "Fail"));

        // Test Case 3
        String[] queries3 = {"+1", "+1", "+1", "-1", "-1", "-1"};
        int diff3 = 0;
        int[] expected3 = {0, 1, 4, 1, 0, 0};
        boolean result3 = testSolution(queries3, diff3, expected3);
        System.out.println("Test Case 3: " + (result3 ? "Pass" : "Fail"));

        // Test Case 4
        String[] queries4 = {"+-3", "+-2", "+-1", "+0", "+1", "+2", "+3"};
        int diff4 = 1;
        int[] expected4 = {0, 0, 0, 0, 1, 3, 6};
        boolean result4 = testSolution(queries4, diff4, expected4);
        System.out.println("Test Case 4: " + (result4 ? "Pass" : "Fail"));

        // Test Case 5
        String[] queries5 = {"+5", "+5", "+5", "+5"};
        int diff5 = 0;
        int[] expected5 = {0, 1, 4, 10};
        boolean result5 = testSolution(queries5, diff5, expected5);
        System.out.println("Test Case 5: " + (result5 ? "Pass" : "Fail"));

        // Test Case 6 (Large Input)
        int n = 100000;
        String[] queries6 = new String[n];
        for (int i = 0; i < n; i++) {
            queries6[i] = "+" + (i % 1000);
        }
        int diff6 = 2;
        try {
            solution(queries6, diff6);
            System.out.println("Test Case 6 (Large Input): Pass");
        } catch (Exception e) {
            System.out.println("Test Case 6 (Large Input): Fail");
        }

        // Test Case 8
        String[] queries8 = {"+2", "+2", "+2", "+2", "-2", "-2"};
        int diff8 = 0;
        int[] expected8 = {0, 1, 4, 10, 4, 1};
        boolean result8 = testSolution(queries8, diff8, expected8);
        System.out.println("Test Case 8: " + (result8 ? "Pass" : "Fail"));
    }
}
