package com.interview.notes.code.months.jan24.test6;

import java.util.Scanner;

/**
 * Increasing Triplet Subsequence
 * You are given an array A[i] of length N.
 * You have to print trat i ‹ j ‹ k and A[i] ‹ A[j] < A[k].
 * If no such indices exists, print false.
 * Function Description
 * In the provided code snippet, implement the provided
 * I tripletindices(...) method using the variables
 * to print true if there exists a triple of indices else print false. You can write your code in the space below the phrase "WRITE YOUR LOGIC HERE".
 * There will be multiple test cases running so the Input and Output should match exactly as provided.
 * The base Output variable result is set to a default vhich can be modified. Additionally, you can add or remove these output variables.
 * Input Format
 * The first line contains a single inng the length of array A[i].
 * The second line contain N space seperated strings, denoting the array A[i].
 * Sample Input
 * 5
 * 123 45|
 * -- denotes N
 * -- denotegs A[i]
 * Constraints
 * 1 ‹= N‹= 1
 * 5 * 105
 * -231<= 231 - 1
 * Output Format
 * The output contains true if there exists a triple of indices. If no such indices exists, print false.
 * Sample Output
 * true
 * Explanation
 * Given A[i] = 12 3 4 5
 */
public class IncreasingTriplet {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5}; // Sample input array
        boolean result = false; // Default result is false

        // Logic to find if there exists an increasing triplet subsequence
        for (int i = 0; i < array.length - 2 && !result; i++) {
            for (int j = i + 1; j < array.length - 1 && !result; j++) {
                for (int k = j + 1; k < array.length && !result; k++) {
                    if (array[i] < array[j] && array[j] < array[k]) {
                        result = true; // Set result to true if a triplet is found
                    }
                }
            }
        }

        // Output the result
        System.out.println(result ? "true" : "false");
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // The size of the array
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt(); // Reading the array elements
        }

        // Logic to find if there exists an increasing triplet subsequence
        boolean found = false;
        for (int i = 0; i < n - 2 && !found; i++) {
            for (int j = i + 1; j < n - 1 && !found; j++) {
                for (int k = j + 1; k < n && !found; k++) {
                    if (array[i] < array[j] && array[j] < array[k]) {
                        found = true;
                    }
                }
            }
        }

        System.out.println(found ? "true" : "false");

    }

    // Logic to find if there exists an increasing triplet subsequence
    boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first) {
                first = n; // update first if n is smaller than first
            } else if (n <= second) {
                second = n; // update second if n is smaller than second
            } else {
                return true; // return true if you find a number bigger than both
            }
        }
        return false; // if no such triplet found
    }

}
