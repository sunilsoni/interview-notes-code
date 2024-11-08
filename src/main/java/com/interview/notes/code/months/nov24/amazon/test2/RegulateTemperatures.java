package com.interview.notes.code.months.nov24.amazon.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RegulateTemperatures {

    public static long regulateTemperatures(List<Integer> temperature) {
        long operations = 0;
        int prev = 0;

        for (int i = 0; i < temperature.size(); i++) {
            int temp = temperature.get(i);
            operations += Math.abs(temp - prev);
            prev = temp;
        }
        return operations;
    }

    public static void main(String[] args) {
        testSampleInputs();
        testEdgeCases();
        testLargeInput();
    }

    public static void testSampleInputs() {
        List<Integer> temperature1 = Arrays.asList(2, 2, 4, 3);
        long result1 = regulateTemperatures(temperature1);
        if (result1 == 4) {
            System.out.println("Sample Input 0 Passed");
        } else {
            System.out.println("Sample Input 0 Failed. Expected 4, Got " + result1);
        }

        List<Integer> temperature2 = Arrays.asList(2, -2, -3, -1);
        long result2 = regulateTemperatures(temperature2);
        if (result2 == 10) {
            System.out.println("Sample Input 1 Passed");
        } else {
            System.out.println("Sample Input 1 Failed. Expected 10, Got " + result2);
        }
    }

    public static void testEdgeCases() {
        // Test with all zeros
        List<Integer> temperature = new ArrayList<>(Collections.nCopies(5, 0));
        long result = regulateTemperatures(temperature);
        if (result == 0) {
            System.out.println("Edge Case (All zeros) Passed");
        } else {
            System.out.println("Edge Case (All zeros) Failed. Expected 0, Got " + result);
        }

        // Test with maximum positive temperatures
        temperature = new ArrayList<>(Collections.nCopies(5, Integer.MAX_VALUE));
        result = regulateTemperatures(temperature);
        // Expected result may vary depending on the logic
        System.out.println("Edge Case (Max positive temperatures) Result: " + result);

        // Test with maximum negative temperatures
        temperature = new ArrayList<>(Collections.nCopies(5, Integer.MIN_VALUE));
        result = regulateTemperatures(temperature);
        // Expected result may vary depending on the logic
        System.out.println("Edge Case (Max negative temperatures) Result: " + result);
    }

    public static void testLargeInput() {
        int n = 200000;
        List<Integer> temperature = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            temperature.add(1000000000);
        }
        long startTime = System.currentTimeMillis();
        long result = regulateTemperatures(temperature);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test Completed in " + (endTime - startTime) + " ms");
        // Expected result may vary depending on the logic
        System.out.println("Large Input Result: " + result);
    }
}
