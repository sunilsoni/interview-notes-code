package com.interview.notes.code.months.nov23.test6.glider;

import java.util.Arrays;
import java.util.List;

public class Outcome2 {
    public static int solve(List<Integer> ar, int K) {
        int low = 0;
        int high = ar.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (ar.get(mid) == K) {
                return mid;
            } else if (ar.get(mid) < K) {
                if (ar.get(low) <= ar.get(mid)) {
                    low = mid + 1;
                } else {
                    if (K < ar.get(low)) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            } else {
                if (ar.get(mid) <= ar.get(high)) {
                    high = mid - 1;
                } else {
                    if (K > ar.get(high)) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }
        }

        return -1;
    }

    // Example use of the solve function
    public static void main(String[] args) {
        List<Integer> exampleList1 = Arrays.asList(3, 4, 5, 6, 7, 10, 2);
        int result1 = solve(exampleList1, 10);

        List<Integer> exampleList2 = Arrays.asList(20, 33, 44, 1);
        int result2 = solve(exampleList2, 11);

        System.out.println("Example 1 Result: " + result1);
        System.out.println("Example 2 Result: " + result2);
    }
}
