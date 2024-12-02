package com.interview.notes.code.year.y2024.july24.test11;

import java.util.Arrays;
import java.util.List;

public class BalancedSum {

    public static void main(String[] args) {
        // Test cases from the examples
        List<Integer> test1 = Arrays.asList(1, 2, 3);
        List<Integer> test2 = Arrays.asList(1, 2, 1);
        List<Integer> test3 = Arrays.asList(1, 2, 3, 3);
        List<Integer> test4 = Arrays.asList(1, 2, 3, 4, 6);

        // Print the outputs of each test case
        System.out.println("Pivot Index for [1, 2, 3]: " + balancedSum(test1));    // Expected: 1
        System.out.println("Pivot Index for [1, 2, 1]: " + balancedSum(test2));    // Expected: 1
        System.out.println("Pivot Index for [1, 2, 3, 3]: " + balancedSum(test3)); // Expected: 2
        System.out.println("Pivot Index for [1, 2, 3, 4, 6]: " + balancedSum(test4)); // Expected: 3
    }

    public static int balancedSum(List<Integer> arr) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < arr.size(); i++) {
            totalSum -= arr.get(i);
            if (leftSum == totalSum) {
                return i;
            }
            leftSum += arr.get(i);
        }

        throw new RuntimeException("No pivot index found, although a solution is guaranteed.");
    }
}
