package com.interview.notes.code.test.test7;

public class NotIncreasingDecreasing {

    public static void main(String[] args) {

        int n = 5;

        int[] arr = {1, 2, 4, 1, 2};


        // int[] arr = {1, 2, 4, 1, 2};
        // int n = 5;
        int minRemove = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i; j < n; j++) {
                if (j - i < 3) {
                    continue;
                }

                boolean increasing = true;
                boolean decreasing = true;
                for (int k = i + 1; k < j; k++) {
                    if (arr[k] > arr[k - 1]) {
                        increasing = false;
                    } else if (arr[k] < arr[k - 1]) {
                        decreasing = false;
                    }
                }

                if (increasing || decreasing) {
                    count++;
                }
            }

            minRemove = Math.min(minRemove, count);
        }

        System.out.println(minRemove);
    }
}
