package com.interview.notes.code.months.aug24.test25;

public class PairCounter {

    public static void main(String[] args) {
        // Test case 1
        int[] a1 = {2, -2, 5, 3};
        int[] b1 = {1, 5, -1, 1};
        System.out.println(solution(a1, b1)); // Expected output: 6

        // Test case 2
        int[] a2 = {25, 0};
        int[] b2 = {0, 25};
        System.out.println(solution(a2, b2)); // Expected output: 3
    }

    public static long solution(int[] a, int[] b) {
        long count = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if ((a[i] - b[j]) == (a[j] - b[i])) {
                    count++;
                }
            }
        }

        return count;
    }
}
