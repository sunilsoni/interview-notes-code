package com.interview.notes.code.months.july24.test2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int solve(List<Integer> ar) {
        if (ar == null || ar.isEmpty()) {
            return 0;
        }

        // This will hold the smallest possible last element of any increasing subsequence of length i+1
        ArrayList<Integer> dp = new ArrayList<>();

        for (int num : ar) {
            // Binary search for the location to replace or extend in dp
            int lo = 0, hi = dp.size();
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (dp.get(mid) < num) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }

            // If lo is equal to the size of dp, it means num is larger than any element in dp
            if (lo >= dp.size()) {
                dp.add(num);
            } else {
                dp.set(lo, num);
            }
        }

        return dp.size();
    }

    public static void main(String[] args) {
        // Example 1
        List<Integer> ar1 = List.of(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
        System.out.println(solve(ar1)); // Output: 6

        // Example 2
        List<Integer> ar2 = List.of(2, 7, 4, 3, 8);
        System.out.println(solve(ar2)); // Output: 3
    }
}
