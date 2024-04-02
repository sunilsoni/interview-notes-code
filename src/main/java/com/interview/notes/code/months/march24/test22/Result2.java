package com.interview.notes.code.months.march24.test22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result2 {
    public static void main(String[] args) {
        List<Integer> arr1 = Arrays.asList(2, 4);
        List<Long> result1 = maxSubsetSum(arr1);
        System.out.println("Result 1: " + result1); // Output: Result 1: [3, 7]

        List<Integer> arr2 = Arrays.asList(1, 10);
        List<Long> result2 = maxSubsetSum(arr2);
        System.out.println("Result 2: " + result2); // Output: Result 2: [28, 39, 42]


        List<Integer> arr3 = Arrays.asList(2, 5, 6);
        List<Long> result3 = maxSubsetSum(arr2);
        System.out.println("Result 3: " + result3); // Output: Result 2: [6,12]
    }

    public static List<Long> maxSubsetSum(List<Integer> k) {
        List<Long> sums = new ArrayList<>();
        for (int num : k) {
            sums.add(sumOfFactors(num));
        }
        return sums;
    }

    private static long sumOfFactors(int number) {
        if (number == 1) return 1L;

        long sum = 1 + number; // 1 and the number itself are always factors
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                sum += i;
                int d = number / i;
                if (d != i) { // if the divisors are not the same
                    sum += d;
                }
            }
        }
        return sum;
    }
}
