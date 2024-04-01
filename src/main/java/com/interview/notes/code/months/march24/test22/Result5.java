package com.interview.notes.code.months.march24.test22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result5 {



    public static void main(String[] args) {
        List<Integer> arr1 = Arrays.asList(2, 4);
        List<Long> result1 = maxSubsetSum(arr1);
        System.out.println("Result 1: " + result1); // Output: Result 1: [3, 7]

        List<Integer> arr2 = Arrays.asList(1, 10);
        List<Long> result2 = maxSubsetSum(arr2);
        System.out.println("Result 2: " + result2); // Output: Result 2: [28, 39, 42]


        List<Integer> arr3 = Arrays.asList(2,5,6);
        List<Long> result3 = maxSubsetSum(arr2);
        System.out.println("Result 3: " + result3); // Output: Result 2: [6,12]
    }
    public static List<Long> maxSubsetSum(List<Integer> arr) {
        List<Long> result = new ArrayList<>();

        for (int num : arr) {
            if (num <= 0) {
                result.add(0L); // Return 0 for non-positive numbers
            } else {
                long sum = getFactorsSum(num);
                result.add(sum);
            }
        }

        return result;
    }

    private static long getFactorsSum(int num) {
        long sum = 1; // Include 1 as a factor

        // Iterate from 2 to sqrt(num) to find factors
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sum += i; // Add the factor
                if (i != num / i) {
                    sum += num / i; // Add the other factor (if different)
                }
            }
        }

        return sum;
    }
}