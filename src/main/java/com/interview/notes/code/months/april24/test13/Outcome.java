package com.interview.notes.code.months.april24.test13;

import java.util.*;

/**
 * Messed-Up Array
 * You are given a messed-up array of integers. It is designed to contain all the numbers from 1 to n, where n is the size of the array. Unfortunately, there is a bug, and a number gets repeated twice.
 * You have to find the repeated number and the number that this repeat has replaced and is thus missing. The array is unsorted.
 * Note:
 * If multiple numbers are repeated, return the smallest repeated number and smallest missing number. If there are no repeated or missing numbers, return -1-1.
 * Input
 * The first line of the input contains an integer n representing the size of the array.
 * The second line contains n space-separated integers representing the elements of the array.
 * Output
 * Print the repeating number followed by the missing number, separated by space.
 * Constraints
 * 1 ≤ n ≤ 100
 * 1 ≤ A[i] ≤n
 *
 *
 * Example #1
 * Input
 * 3
 * 3
 * 2 3
 * Output
 * 3
 * 1
 * Explanation: The sequence should contain the numbers: 123.
 * The number 3 is repeated, and the number 1 is missing.
 * Example #2
 * Input
 * 5
 * 43123
 * Output
 * 35
 * Explanation: The sequence should have been: 1234
 * The number 3 is repeated, and the number 5 is missing.
 */
//WORKING
public class Outcome {
    public static List<Integer> missingAndDouble(List<Integer> A) {
        List<Integer> result = new ArrayList<>();
        Collections.sort(A);
        
        int repeat = -1;
        int missing = -1;
        int n = A.size();
        
        for (int i = 0; i < n - 1; i++) {
            if (A.get(i).equals(A.get(i + 1))) {
                repeat = A.get(i);
                break;
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (A.get(i) != i + 1) {
                missing = i + 1;
                break;
            }
        }
        
        result.add(repeat);
        result.add(missing);
        
        return result;
    }

    public static void main(String[] args) {
        // Test cases
        List<Integer> test1 = Arrays.asList(2, 3, 2);
        List<Integer> test2 = Arrays.asList(4, 6, 7, 3, 5, 4, 7, 8);
        List<Integer> test3 = Arrays.asList(4, 6, 7, 3, 5, 4, 7, 8);

        System.out.println(missingAndDouble(test1)); // Example 1
        System.out.println(missingAndDouble(test2)); // Example 2
        System.out.println(missingAndDouble(test3)); // Provided test case
    }
}
