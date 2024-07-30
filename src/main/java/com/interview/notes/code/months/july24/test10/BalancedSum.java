package com.interview.notes.code.months.july24.test10;

import java.util.Arrays;
import java.util.List;

class BalancedSum {
    public static int balancedSum(List<Integer> arr) {
        if (arr == null || arr.size() < 3) {
            throw new IllegalArgumentException("Array must have at least 3 elements");
        }

        long totalSum = arr.stream().mapToLong(Integer::longValue).sum();
        long leftSum = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (leftSum == totalSum - leftSum - arr.get(i)) {
                return i;
            }
            leftSum += arr.get(i);
        }

        throw new IllegalStateException("No balanced pivot found");
    }

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
}
