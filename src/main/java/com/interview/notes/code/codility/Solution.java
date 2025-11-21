package com.interview.notes.code.codility;

import java.util.HashMap;

class Solution {

    /**
     * There are N empty glasses with a capacity of 1, 2, ..., N liters (there is exactly one glass of each unique capacity). You want to pour exactly K liters of water into glasses. Each glass may be either full or empty (a glass cannot be partially filled). What is the minimum number of glasses that you need to contain K liters of water?
     * Write a function:
     * class Solution { public int solution(int N, int K); }
     * that, given two integers N and K, returns the minimum number of glasses that are needed to contain exactly K liters of water. If it is not possible to pour exactly K liters of water into glasses then the function should return -1.
     * <p>
     * <p>
     * Examples:
     * 1. Given N = 5 and K = 8, the function should return 2. There are five glasses of capacity 1, 2, 3, 4 and 5. You can use two glasses with capacity 3 and 5 to hold 8 liters of water.
     * 2. Given N = 4 and K = 10, the function should return 4. You must use all the glasses to contain 10 liters of water.
     * <p>
     * <p>
     * 3. Given N = 1 and K = 2, the function should return -1. There is only one glass with capacity 1, so you cannot pour 2 liters of water.
     * 4. Given N = 10 and K = 5, the function should return 1. You can use the glass with capacity 5.
     * Write an efficient algorithm for the following assumptions:
     * • N is an integer within the range [1..1,000,000]; • K is an integer within the range [1..1,000,000,000].
     *
     * @param N
     * @param K
     * @return
     */
    public static int solution(int N, int K) {
        if (K > N * (N + 1) / 2) {
            return -1;
        }

        int count = 0;
        for (int i = N; i >= 1; i--) {
            if (K >= i) {
                K -= i;
                count++;
            }
        }
        return count;
    }

    /**
     * You are given an array of integers. Your task is to create pairs of them, such that every pair consists of equal numbers. Each array element may belong to one pair only. Is it possible to use all of the integers?
     * Write a function:
     * class Solution { public boolean solution(int[] A); }
     * that, given an array A consisting of N integers, returns whether it is possible to split all integers into pairs.
     * <p>
     * Examples:
     * 1. Given A = [1, 2, 2, 1], your function should return True, as the pairs are (A[0], A[3]) (both have value 1) and (A[1], A[2]) (both have value 2).
     * 2. Given A = [7, 7, 7], your function should return False, as you can make one pair of numbers 7, but you still have a single 7 left.
     * 3. Given A = [1, 2, 2, 3], your function should return False, as there's nothing that A[0] can be paired with.
     * Write an efficient algorithm for the following assumptions:
     * • N is an integer within the range [1..100,000]; • each element of array A is an integer within the range I-1,000,000..1,000,000].
     *
     * @param A
     * @return
     */
    public static boolean solution2(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            int value = A[i];
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        for (int value : map.values()) {
            if (value % 2 != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 2, 1};

        System.out.println(solution2(a));
        System.out.println(solution(1, 2));
    }
}