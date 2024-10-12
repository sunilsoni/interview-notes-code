package com.interview.notes.code.months.oct24.test5;

import java.util.Arrays;
import java.util.stream.Stream;

public class MultiDimensionalArraySum {

    public static int calculateSum(Object[] array) {
        return Arrays.stream(array)
                .flatMap(MultiDimensionalArraySum::flattenArray)
                .mapToInt(obj -> (int) obj)
                .sum();
    }

    private static Stream<?> flattenArray(Object obj) {
        if (obj instanceof Object[]) {
            return Arrays.stream((Object[]) obj).flatMap(MultiDimensionalArraySum::flattenArray);
        } else {
            return Stream.of(obj);
        }
    }

    public static void main(String[] args) {
        // Test cases
        Object[][] testCases = {
                {new Object[]{1, 2, 3}, new Object[]{4}, new Object[]{5, 6}},
                {new Object[]{1, 2}, new Object[]{3, 4, 5}},
                {new Object[]{1}, new Object[]{2}, new Object[]{3}},
                {new Object[]{}, new Object[]{1, 2, 3}, new Object[]{}},
                {new Object[]{new Object[]{1, 2}, new Object[]{3, 4}}, new Object[]{5, 6}}
        };

        for (int i = 0; i < testCases.length; i++) {
            int result = calculateSum(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ": " + (result == getExpectedSum(testCases[i]) ? "PASS" : "FAIL"));
        }

        // Large input test
        Object[] largeInput = generateLargeInput(1_000_000);
        long startTime = System.currentTimeMillis();
        int largeResult = calculateSum(largeInput);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Input Test: " + (endTime - startTime) + "ms, Sum: " + largeResult);
    }

    private static int getExpectedSum(Object[] array) {
        return Arrays.stream(array)
                .flatMap(MultiDimensionalArraySum::flattenArray)
                .mapToInt(obj -> (int) obj)
                .sum();
    }

    private static Object[] generateLargeInput(int size) {
        return Arrays.stream(new int[size])
                .mapToObj(i -> (Object) (int) (Math.random() * 100))
                .toArray();
    }
}
