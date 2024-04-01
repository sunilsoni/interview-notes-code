package com.interview.notes.code.months.march24.test22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Result3 {

    public static List<Long> maxSubsetSum(List<Integer> arr) {
        List<Long> result = new ArrayList<>();
        for (int num : arr) {
            long sumOfFactors = getSumOfFactors(num);
            result.add(sumOfFactors);
        }
        return result;
    }

    private static long getSumOfFactors(int num) {
        long sum = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

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
}
