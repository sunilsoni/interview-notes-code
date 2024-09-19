package com.interview.notes.code.months.sept24.test6;

import java.util.Arrays;
import java.util.Comparator;

public class SecondLargest {
    public static void main(String[] args) {
        String values = "6,3,5,12,10,18";

        int secondLargest = findSecondLargest(values);
        System.out.println("Second largest number: " + secondLargest);

        // Additional test cases
        testSecondLargest("1,2,3,4,5", 4);
        testSecondLargest("10,10,10", 10);
        testSecondLargest("7", -1);
        // testSecondLargest("", -1);
    }

    public static int findSecondLargest(String values) {
        return Arrays.stream(values.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(1)
                .findFirst()
                .orElse(-1);
    }

    public static void testSecondLargest(String input, int expected) {
        int result = findSecondLargest(input);
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Test " + (result == expected ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
