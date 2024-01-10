package com.interview.notes.code.months.year2023.Oct23.test11;

import java.util.HashMap;
import java.util.Map;

/**
 * There is an array A  consisting of N integers. What is the m aximum sum of two integers from A that share their first
 * and last digits? For example, 1007 and 167 share their first (1) and last (7) digits, whereas 2002 and 55 do not.
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given an array A consisting of N integers, returns the maximum sum of two integers that share their first and
 * last digits. If there are no two integers that share their first and last digits, the function should return -1.
 * Examples:
 * 1. Given A = [130,191, 200,10], the function should return 140. The only integers in A that share first and last digits
 * are 130 and 10.
 * 2. Given A = [405,45, 300, 300], the function should return 600. There are two pairs of integers that share first and
 * last digits: (405, 45) and (300,300). The sum of the two 300s is bigger than the sum of 405 and 45.
 * 3. Given A = [50, 222,49, 52, 25], the function should return -1. There are no two integers that share their first and
 * last digits.
 * 4. Given A = [30, 909, 3190, 99, 3990, 9009], the function should return 9918.
 * Write an efficient algorithm for the following assumptions:
 * • N is an integer within the range [1..100,000];
 * • each element of array A is an integer within the range [10..1,000,000,000],
 */
class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] test1 = {130, 191, 200, 10};
        int[] test2 = {405, 45, 300, 300};
        int[] test3 = {50, 222, 49, 52, 25};
        int[] test4 = {30, 909, 3190, 99, 3990, 9009};

        System.out.println(solution.solution(test1));  // Expected: 140
        System.out.println(solution.solution(test2));  // Expected: 600
        System.out.println(solution.solution(test3));  // Expected: -1
        System.out.println(solution.solution(test4));  // Expected: 9918
    }

    private String getFirstAndLastDigit(int num) {
        String strNum = String.valueOf(num);
        return strNum.charAt(0) + "" + strNum.charAt(strNum.length() - 1);
    }

    public int solution(int[] array) {
        Map<String, int[]> map = new HashMap<>();

        for (int num : array) {
            String key = getFirstAndLastDigit(num);
            int[] currentMaxes = map.getOrDefault(key, new int[]{-1, -1});

            if (num > currentMaxes[0]) {
                currentMaxes[1] = currentMaxes[0];
                currentMaxes[0] = num;
            } else if (num > currentMaxes[1]) {
                currentMaxes[1] = num;
            }

            map.put(key, currentMaxes);
        }

        int maxSum = -1;
        for (int[] values : map.values()) {
            if (values[1] != -1) {
                maxSum = Math.max(maxSum, values[0] + values[1]);
            }
        }

        return maxSum;
    }
}
