package com.interview.notes.code.months.aug24.test33;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class StringCompare {
    public static void main(String[] args) {
        // Test cases
        testCase("ABCDE", "ABCDE", true);
        testCase("ABCDEFGHIJKL", "ABCDEFXYZ", true);
        testCase("ABC", "ABC", true);
        testCase("ABCD", "ABCDEFGH", false);
        testCase("ABC", "ABCD", false);
        testCase("", "", true);
        testCase(null, "ABC", false);
        testCase("ABC", null, false);
        testCase("A", "A", true);
        testCase("AB", "AC", false);

        // Runnable
        Runnable runnable = () -> System.out.println("Hello from Runnable");
        new Thread(runnable).start();

// Callable
        Callable<String> callable = () -> "Hello from Callable";
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(callable);
        // String result = future.get();
        //System.out.println(result);
        //executor.shutdown();

    }

    public static boolean compareStrings(String input1, String input2) {
        // Handle null inputs
        if (input1 == null || input2 == null) {
            return false;
        }

        // Determine the length to compare
        int lengthToCompare = Math.min(Math.min(input1.length(), input2.length()), 5);

        // Compare substrings
        return input1.substring(0, lengthToCompare).equals(input2.substring(0, lengthToCompare));
    }

    private static void testCase(String input1, String input2, boolean expected) {
        boolean result = compareStrings(input1, input2);
        System.out.printf("Input1: %s, Input2: %s, Expected: %b, Result: %b, %s%n",
                input1, input2, expected, result, (result == expected ? "PASS" : "FAIL"));
    }
}
