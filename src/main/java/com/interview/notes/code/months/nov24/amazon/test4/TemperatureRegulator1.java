package com.interview.notes.code.months.nov24.amazon.test4;

import java.util.*;
import java.io.*;

public class TemperatureRegulator1 {

    /**
     * Problem Analysis:
     * The problem requires finding the minimum number of actions to make all temperatures zero,
     * using three operations that affect prefixes, suffixes, or the entire array.
     *
     * After reviewing the problem and sample outputs, it's clear that simply summing the absolute
     * differences or positive changes does not accurately compute the minimal number of actions.
     *
     * Solution Design:
     * We need an algorithm that accurately computes the minimal number of actions. Considering the
     * specific operations, we can process the temperature array and calculate the required actions
     * by carefully considering the changes needed at each step.
     *
     * The idea is to calculate the net change at each position and consider both positive and
     * negative changes separately.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */

    public static long regulateTemperatures(List<Integer> temperature) {
        long res = 0;
        long totalPositiveChanges = 0;
        long totalNegativeChanges = 0;
        long prevTemp = 0;

        for (int i = 0; i < temperature.size(); i++) {
            long tempChange = temperature.get(i) - prevTemp;
            if (tempChange > 0) {
                totalPositiveChanges += tempChange;
            } else {
                totalNegativeChanges -= tempChange;
            }
            prevTemp = temperature.get(i);
        }
        res = totalPositiveChanges + totalNegativeChanges;
        return res;
    }

    public static void main(String[] args) throws IOException {
        // Testing the regulateTemperatures function with sample inputs and additional test cases

        // Sample Test Case 0
        runTestCase(Arrays.asList(2, 2, 4, 3), 4);

        // Sample Test Case 1
        runTestCase(Arrays.asList(2, -2, -3, -1), 10);

        // Additional Test Case 1
        runTestCase(Arrays.asList(3, 3, 3), 3);

        // Additional Test Case 2
        runTestCase(Arrays.asList(-1, -2, -3), 6);

        // Additional Test Case 3
        runTestCase(Arrays.asList(0, 0, 0), 0);

        // Large Input Test Case
        List<Integer> largeTemperatureList = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeTemperatureList.add(1000000000);
        }
        runTestCase(largeTemperatureList, 1000000000L * 200000);

        // Read input from the user if needed
        // Uncomment the following lines to accept user input
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().trim());
        List<Integer> temperature = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            temperature.add(Integer.parseInt(reader.readLine().trim()));
        }
        long result = regulateTemperatures(temperature);
        System.out.println(result);
        */
    }

    private static void runTestCase(List<Integer> temperature, long expected) {
        long result = regulateTemperatures(temperature);
        if (result == expected) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
            System.out.println("Input: " + temperature);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        }
    }
}