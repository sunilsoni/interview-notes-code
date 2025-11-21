package com.interview.notes.code.year.y2024.aug24.test9;

import java.util.Arrays;

//@Builder
public class FindConsecutiveMissingNumbers {
    public static void main(String[] args) {
        arrayNumber();

        int[] numbers = {3, 2, 7, 1, 4, 8};
        int[] missingNumbers = findConsecutiveMissingNumbers(numbers);

        if (missingNumbers != null) {
            System.out.println("The two consecutive missing numbers are: " +
                    missingNumbers[0] + " and " + missingNumbers[1]);
        } else {
            System.out.println("No two consecutive missing numbers found.");
        }
    }

    private static void arrayNumber() {
        int[] intArray = {0, 1, 2, 3, 4, 5};
        Integer element = Arrays.stream(intArray).boxed().reduce((first, second) -> second).orElse(-1);
        System.out.println(element);
    }


    public static int[] findConsecutiveMissingNumbers(int[] numbers) {
        // Sort the array
        Arrays.sort(numbers);

        // Find the minimum and maximum numbers in the array
        int min = numbers[0];
        int max = numbers[numbers.length - 1];

        // Iterate through the range from min to max
        for (int i = min; i < max - 1; i++) {
            // Check if i and i+1 are both missing
            if (!contains(numbers, i) && !contains(numbers, i + 1)) {
                return new int[]{i, i + 1};
            }
        }

        // If no consecutive missing numbers found
        return null;
    }

    private static boolean contains(int[] arr, int num) {
        for (int n : arr) {
            if (n == num) {
                return true;
            }
        }
        return false;
    }
}
