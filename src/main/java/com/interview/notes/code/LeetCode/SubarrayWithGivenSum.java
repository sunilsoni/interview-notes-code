package com.interview.notes.code.LeetCode;

/**
 * http://www.geeksforgeeks.org/find-subarray-with-given-sum/
 */
public class SubarrayWithGivenSum {

    public static void main(String[] args) {
        SubarrayWithGivenSum sgs = new SubarrayWithGivenSum();
        int[] input = {6, 3, 9, 11, 1, 3, 5};
        System.out.println(sgs.findSubArray(input, 15));
    }

    public Pair findSubArray(int[] input, int sum) {
        int currentSum = 0;
        Pair p = new Pair();
        p.start = 0;
        for (int i = 0; i < input.length; i++) {
            currentSum += input[i];
            p.end = i;
            if (currentSum == sum) {
                return p;
            } else if (currentSum > sum) {
                int s = p.start;
                while (currentSum > sum) {
                    currentSum -= input[s];
                    s++;
                }
                p.start = s;
                if (currentSum == sum) {
                    return p;
                }
            }
        }
        return null;
    }

    class Pair {
        int start;
        int end;

        public String toString() {
            return start + " " + end;
        }
    }
}