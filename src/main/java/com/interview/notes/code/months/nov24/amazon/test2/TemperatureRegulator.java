package com.interview.notes.code.months.nov24.amazon.test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TemperatureRegulator {

    /**
     * Problem Analysis:
     * The problem requires finding the minimum number of actions to regulate the temperatures of n CPU cores to 0.
     * We have three operations:
     * 1. Decrease temperatures[0..i] by 1.
     * 2. Decrease temperatures[i..n-1] by 1.
     * 3. Increase all temperatures by 1.
     * <p>
     * Key Observations:
     * - We can think of the problem as transforming the initial temperature array to an array of zeros using the minimal number of operations.
     * - The operations affect ranges in the array, and our goal is to find an efficient way to calculate the total number of unit changes needed.
     * - We can model the total operations required as the sum of the absolute differences between adjacent elements.
     * <p>
     * Solution Design:
     * - Initialize a variable res to store the total number of operations.
     * - Iterate through the temperature array and calculate the absolute difference between the current temperature and the previous one.
     * - Add the absolute difference to res.
     * - Since the operations can only change temperatures by 1, the total number of unit changes needed is equal to the sum of these absolute differences.
     * - This approach ensures we account for all increases and decreases in temperature.
     * <p>
     * Time Complexity: O(n), where n is the number of cores.
     * Space Complexity: O(1).
     */

    public static long regulateTemperatures(List<Integer> temperature) {
        long res = Math.abs(temperature.get(0));
        for (int i = 1; i < temperature.size(); i++) {
            res += Math.abs((long) temperature.get(i) - temperature.get(i - 1));
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        // Test the regulateTemperatures function with sample inputs
        // and verify it passes all test cases, including edge cases.

        // Read input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        List<Integer> temperature = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            temperature.add(Integer.parseInt(reader.readLine().trim()));
        }

        // Call the function and print the result
        long result = regulateTemperatures(temperature);
        System.out.println(result);
    }
}