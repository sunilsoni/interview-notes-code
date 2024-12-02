package com.interview.notes.code.year.y2024.may24.test3;

/**
 * Question: 2 of 2
 * Searching Algorithm: Valley Points
 * You are given two arrays A and B of size N and an integer K.
 * A point i said to be valley point if it satisfies these 2 conditions:
 * 1. prel(i]>=pre2[i]
 * 2. prel(i]<K
 * where, prefix sum for prel[i] is equal to A[0] + A[1] + A[2] . A[i] and prefix sum pre2[i] is equal to B[0] + B[1] + B[2] ... B[i].
 * You have to count the total number of valley points.
 * Note
 * Consider 0-based indexing of array.
 * Function Description
 * In the provided code snippet, implement the provided valleyPoints...) method using the variables to count the total numbers of valley points. You can write your code in the space below the phrase "WRITE YOUR LOGIC HERE".
 * There will be multiple test cases running so the Input and Output should match exactly as provided.
 * The base Output variable result is set to a default value of -404 which can be modified.
 * Additionally, you can add or remove these output variables.
 * Input Format
 * The first line contains an integer N, denoting the size of the array.
 * The second line contains the array element of A.
 * The third line contains the array element of B.
 * The fourth line contains an integer K.
 * Sample Input
 * 5
 * 7 3 6 4 12
 * 123 4 28
 * 50
 * <p>
 * <p>
 * Sample Input
 * 5
 * 7 3 6 412
 * 123 4 28
 * 50
 * Constraints
 * 1く=N<=100000
 * 1‹= A[i] <=1000
 * 1‹= B[i] <=1000
 * 1<=K＜=100000
 * Output Format
 * Print an integer denoting the total number of valley points.
 * Sample Output
 * 4
 * Explanation
 * Prefix sum array of array A is 7 10 16 20 32.
 * Prefix sum array of array B is 1 3 6 10 38.
 * There are 4 valley points satisfying the above conditions (considering 0- based indexing)
 * These are: i=0, i=1, i=2, i=3.
 * Hence, the output is 4.
 * <p>
 * <p>
 * <p>
 * import java.util.*;
 * import java.io.*;
 * import java.lang.Math;
 * public class Main
 * public static int valleyPoints(int N,intl] A,intl] B,int K)
 * //this is default OUTPUT. You can change it.
 * int result = -404;
 * //write your Logic here:
 * return result;
 * }
 * }
 */
public class ValleyPoints {
    public static int valleyPoints(int N, int[] A, int[] B, int K) {
        // Calculate prefix sums for arrays A and B
        int[] preA = new int[N];
        int[] preB = new int[N];
        preA[0] = A[0];
        preB[0] = B[0];
        for (int i = 1; i < N; i++) {
            preA[i] = preA[i - 1] + A[i];
            preB[i] = preB[i - 1] + B[i];
        }

        // Count the number of valley points
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (preA[i] >= preB[i] && preA[i] < K) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        // Example Input from screenshots
        int N = 5;
        int[] A = {7, 3, 6, 4, 12};
        int[] B = {1, 2, 3, 4, 28};
        int K = 50;

        // Execute the function and print the output
        System.out.println("Total number of valley points: " + valleyPoints(N, A, B, K));
    }
}
