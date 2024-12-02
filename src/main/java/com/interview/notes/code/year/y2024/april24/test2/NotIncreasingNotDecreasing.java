package com.interview.notes.code.year.y2024.april24.test2;

import java.util.Arrays;
import java.util.List;

//WORKING

/**
 * Java:
 * Not increasing, not decreasing
 * You are given an array consisting of distinct integers. Print the minimum number of elements to be removed such that no three consecutive elements in the array are either increasing or decreasing.
 * Input
 * The first line of input contains an integer n, representing the size of the array.
 * The second line of input contains n space-separated integers, representing the array elements.
 * Output
 * Print the minimum number of elements to be removed such that no three consecutive elements in the array are either increasing or decreasing.
 * <p>
 * <p>
 * Constraints
 * 1 <= n <= 104
 * Example #1
 * Input
 * 5
 * 1
 * 24 1 2
 * Output
 * 1
 * Explanation: We need to remove one element (4). (1, 2, 4, 1, 2}
 * -> (1, 2, 1, 2). There are no three consecutive elements in the array are either increasing or decreasing.
 * Example #2
 * Input
 * 4
 * 12 35
 * Output
 * 2
 * Explanation: We need to remove two elements. There are several ways of doing this:
 * <p>
 * <p>
 * <p>
 * 1)(7,2,3,5}-{3, 5}
 * 2) (7, 2, 3, 5) -> (2, 5)
 * 3） 11,2,3,5｝-01,5
 * 4) (1, ₴, 3, 5) -> (1, 5,)
 * 5） （1,2, 3,5｝->｛1,3
 * <p>
 * <p>
 * 6) (1,2,3,5}->{1,2
 * <p>
 * <p>
 * <p>
 * <p>
 * class Outcone 1
 * Implement method/function with name 'solve' below.
 * The function accepts following as parameters.
 * 1. ar is of type List<Integer>.
 * return int.
 * /
 * public static int solve(List<Integer> ar)i
 * //write your code here
 * return; //return type "int".
 * }
 * }
 */
public class NotIncreasingNotDecreasing {

    public static void main(String[] args) {
        // Example usage:
        List<Integer> input = Arrays.asList(1, 2, 3, 6, 5, 4, 8);
        System.out.println(solve(input)); // This should output 3
    }

    public static int solve(List<Integer> ar) {
        int n = ar.size();
        int removedCount = 0;

        for (int i = 1; i < n - 1; i++) {
            int prev = ar.get(i - 1);
            int curr = ar.get(i);
            int next = ar.get(i + 1);

            if ((prev < curr && curr < next) || (prev > curr && curr > next)) {
                // Remove the middle element
                removedCount++;
            }
        }

        return removedCount;
    }
}