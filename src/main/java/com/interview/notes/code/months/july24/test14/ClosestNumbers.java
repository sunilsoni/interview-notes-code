package com.interview.notes.code.months.july24.test14;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Closest Numbers
 * Description
 * Given an array of distinct integers, determine the minimum absolute difference between any two elements. Print all element pairs with that difference in ascending order.
 * Example
 * numbers = [6,2,4, 10]
 * The minimum absolute difference is 2 and the pairs with that difference are (2,4) and (4,6). When printing element pairs (ij), they should be ordered ascending first by i and then by j.
 * 24
 * 4 6
 * Function Description
 * Complete the function closestNumbers in the editor.
 * closestNumbers has the following parameter(s):
 * int numbers[n]: an array of integers
 * Returns
 * NONE
 * Prints
 * distinct element pairs that share the minimum absolute difference, displayed in ascending order with each pair separated by one space on a single line
 * <p>
 * <p>
 * Constraints
 * • 2 ≤n≤ 105
 * • -10° ≤ numberslül ≤ 10°
 * • Input Format for Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer n, the size of the array numbers.
 * Each of the next n lines contains an integer, numbers[i].
 */
class ClosestNumbers {
    public static void closestNumbers(List<Integer> numbers) {
        // Sort the numbers
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());

        // Find the minimum difference
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < sortedNumbers.size(); i++) {
            minDiff = Math.min(minDiff, sortedNumbers.get(i) - sortedNumbers.get(i - 1));
        }

        // Find and print pairs with minimum difference
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < sortedNumbers.size(); i++) {
            if (sortedNumbers.get(i) - sortedNumbers.get(i - 1) == minDiff) {
                result.append(sortedNumbers.get(i - 1))
                        .append(" ")
                        .append(sortedNumbers.get(i))
                        .append("\n");
            }
        }

        // Print the result
        System.out.print(result);
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> test1 = Arrays.asList(6, 2, 4, 10);
        List<Integer> test2 = Arrays.asList(4, 2, 1, 3);
        List<Integer> test3 = Arrays.asList(4, -2, -1, 3);

        System.out.println("Test case 1:");
        closestNumbers(test1);
        System.out.println("\nTest case 2:");
        closestNumbers(test2);
        System.out.println("\nTest case 3:");
        closestNumbers(test3);
    }
}
