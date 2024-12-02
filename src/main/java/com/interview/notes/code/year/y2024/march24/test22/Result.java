package com.interview.notes.code.year.y2024.march24.test22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 5. Largest Subset Sum
 * For each number in an array, get the sum of its factors. Return an array of results.
 * Example
 * arr = [12]
 * The factors of arr[0] = 12 are [1, 2, 3, 4, 6, 12). The sum of these factors is 28. Return the array [28].
 * Function Description
 * Complete the function maxSubsetSum in the editor below.
 * maxSubsetSum has the following parameters):
 * int arr[n]: an array of integers
 * Returns
 * long[n]: the sums calculated for each arr[i]
 * Constraints
 * • 1≤n≤ 103
 * ・ 1≤arrly≤ 109
 * • Input Format For Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer n, the number of elements in arr.
 * Each of the next n lines contains an integer describing arr[i].
 * <p>
 * <p>
 * <p>
 * <p>
 * • Sample Case 0
 * <p>
 * STDIN
 * ーーーー
 * 2
 * 2
 * 4
 * Function
 * ---
 * ーー
 * arr[] size n = 2
 * arr = [2, 4]
 * Sample Output 0
 * 3
 * 7
 * Explanation 0
 * Factors of arr[0] = 2 are [1, 2] and their sum is 3.
 * Factors of arr[1] = 4 are [1, 2, 4] and their sum is 7.
 * <p>
 * <p>
 * class Result {
 * /*
 * Complete the 'maxSubsetSum' function below.
 * <p>
 * The function is expected to return a LONG_INTEGER_ARRAY.
 * The function accepts INTEGER_ARRAY k as parameter.
 * /
 * public static List<Long› maxSubsetSum(List«Integerk f
 * // Write your code here
 * <p>
 * <p>
 * }
 * }
 */
//WORKING FINAL
class Result {
    /*
     * Complete the 'maxSubsetSum' function below.
     *
     * The function is expected to return a LONG_INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Long> maxSubsetSum(List<Integer> arr) {
        List<Long> sums = new ArrayList<>();
        for (int num : arr) {
            sums.add(getSumOfFactors(num));
        }
        return sums;
    }

    private static long getSumOfFactors(int num) {
        long sum = 0;
        int sqrt = (int) Math.sqrt(num);
        for (int i = 1; i <= sqrt; i++) {
            if (num % i == 0) {
                sum += i;
                int complement = num / i;
                if (complement != i) {
                    sum += complement;
                }
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


        List<Integer> arr3 = Arrays.asList(2, 5, 6);
        List<Long> result3 = maxSubsetSum(arr2);
        System.out.println("Result 3: " + result3); // Output: Result 2: [28, 39, 42]
    }
}
