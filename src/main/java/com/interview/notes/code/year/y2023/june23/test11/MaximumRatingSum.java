package com.interview.notes.code.year.y2023.june23.test11;

/**
 * In Java Maximum Rating Sum :
 * <p>
 * Ryan is movie obsessed and has collected a list of movie quality ratings. He wants
 * to watch the largest contiguous list of movies with the highest cumulative ratings
 * possible. To do this, he must calculate the sum of all contiguous subarrays in order
 * to determine the maximum possible subarray sum.
 * <p>
 * For example, ratings are arr = f-1,3,4,-2,5,-7]. We can see that the highest value
 * contiguous subarray runs from arr[1]-arr[4]and \s 3 + 4 +-2 + 5 = 10.
 * <p>
 * Function Description
 * Complete the function maximumSum in the editor below. It must return a long
 * integer denoting the maximum sum for any contiguous subarray in arr.
 * maximumSum has the following parameter(s):
 * arr[arr[0],...arr[n-1]]: an array of integers
 * <p>
 * <p>
 * Constraints
 * •  l^n^lO6
 * •  -IO7 < arr[i] < 107
 * <p>
 * <p>
 * Input Format for Custom Testing
 * <p>
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer n, the size of the array arr.
 * Each of the next n lines contains an integer arr[i].
 * <p>
 * <p>
 * ▼ Sample Case
 * Sample Input 0
 * 4
 * -1
 * -2
 * 1
 * 3
 * Sample Output 0
 * 4
 * Explanation 0
 * The maximum sum for any contiguous subarray inf- 7, -2, 1,3]\s 1+3 = 4.
 * <p>
 * <p>
 * <p>
 * Sample Case 1
 * Sample Input 1
 * 4
 * 1
 * 2
 * 3
 * 4
 * Sample Output 1
 * 10
 * Explanation 1
 * The maximum sum for any contiguous subarray in [1,2, 3, 4]\s 1 + 2 + 3 + 4
 * = W.
 */
public class MaximumRatingSum {
    public static long maximumSum(int[] arr) {
        long maxSoFar = arr[0];
        long maxEndingHere = arr[0];

        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {-1, -2, 1, 3};
        System.out.println(maximumSum(arr));  // prints 4

        arr = new int[]{1, 2, 3, 4};
        System.out.println(maximumSum(arr));  // prints 10

        arr = new int[]{4, 1, 2, 3, 4};
        System.out.println(maximumSum(arr));  // prints 10
    }
}
