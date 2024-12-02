package com.interview.notes.code.year.y2024.march24.test2;

import java.util.List;


/**
 * 2. Question 2
 * Given an array arr of n integers, and two integers / and r, find the number of pairs (i, g) where (1 ≤ i < j≤ n) such that the value arr+ arriff lies between / and r, both inclusive. Array indices start at 1.
 * Example
 * Suppose n = 4, arr = [2, 3, 4, 5], /= 5 and r= 7.
 * There are 4 pairs with a sum between 5 and 7.
 * • arr[1] + arr[2]=2 + 3 = 5
 * • arr[1] + arr[3]= 2 + 4 = 6
 * • arr[1] + arr[4]= 2 + 5 = 7
 * • arr[2] + arr[3]= 3 + 4 = 7.
 * Return 4.
 * Function Description
 * Complete the function getNumPairs in the editor below.
 * The function getNumPairs has the following parameters:
 * int arrn]: an array of integers
 * I: the minimum acceptable sum
 * r. the maximum acceptable sum
 * Return
 * int: the number of pairs that satisfy the condition
 * <p>
 * Constraints
 * • 1 ≤n≤105
 * • 1 ≤ arr[i] ≤ 109
 * • 0515r 109
 * • Input Format For Custom Testing
 * The first line contains an integer, n, the number of elements in arr.
 * Each line i of the n subsequent lines (where 0 ≤ i < n) contains an integer, arrli].
 * The next line contains an integer, I.
 * The last line contains an integer, r.
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * lIllー
 * 3
 * 6
 * 2
 * 3
 * 7
 * 10
 * FUNCTION
 * arr[] size n = 3
 * arr = 16, 2, 31
 * →
 * 1 = 7
 * r = 10
 * Sample Output
 * 2
 * Explanation
 * • (1,2), 6 + 2 = 8
 * • (1,3), 6 + 3=9
 * <p>
 * <p>
 * • Sample Case 1
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * 2
 * 100
 * 100
 * 200
 * 200
 * →
 * ーーーー
 * arr[] size n = 3
 * arr = [100, 100]
 * 1 = 200
 * r = 200
 * Sample Output
 * 1
 * Explanation
 * • (1, 2), 100 + 100 = 200
 * <p>
 * <p>
 * <p>
 * Time limit exceeded
 * Allowed time limit:4 secs
 * Your code did not execute in time. Please optimize your code. For more details on runtime environment, click the “Info” button
 */
public class Main2 {

    public static int getNumPairs(List<Integer> arr, int l, int r) {
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                int sum = arr.get(i) + arr.get(j);
                if (sum >= l && sum <= r) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(2, 3, 4, 5);
        System.out.println(getNumPairs(arr, 5, 7)); // Expected output: 4

        arr = List.of(6, 2, 3);
        System.out.println(getNumPairs(arr, 7, 10)); // Expected output: 2

        arr = List.of(100, 100);
        System.out.println(getNumPairs(arr, 200, 200)); // Expected output: 1
    }
}
