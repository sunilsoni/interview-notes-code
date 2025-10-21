package com.interview.notes.code.year.y2025.october.assessments.test1;

public class ThirdLargest {
    public static Integer findThirdLargestOptimal(Integer[] arr) {
        if (arr == null || arr.length < 3) {
            return null;
        }

        Integer first = null;  // Largest
        Integer second = null; // Second largest
        Integer third = null;  // Third largest

        for (Integer num : arr) {
            if (num == null) continue;

            if (first == null || num > first) {
                third = second;
                second = first;
                first = num;
            } else if (num < first && (second == null || num > second)) {
                third = second;
                second = num;
            } else if (num < second && (third == null || num > third)) {
                third = num;
            }
        }

        return third;
    }

    public static void main(String[] args) {
        // Test cases
        Integer[][] testCases = {
                {10, 20, 30, 40, 50},           // Normal case
                {1},                            // Less than 3 elements
                {10, 10, 10},                   // All same elements
                {null, 1, 2, 3, null},          // Contains null
                {5, 2, 8, 1, 3},               // Unsorted array
                {10, 10, 20, 30, 40, 40}       // Duplicate elements
        };

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            // System.out.println("Array: " + Arrays.toString(testCases[i]));
            System.out.println("Third Largest: " + findThirdLargestOptimal(testCases[i]));
            System.out.println();
        }
    }
}
