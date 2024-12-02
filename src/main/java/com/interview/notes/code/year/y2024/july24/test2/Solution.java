package com.interview.notes.code.year.y2024.july24.test2;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static int solve(List<Integer> ar) {
        if (ar == null || ar.size() == 0) {
            return 0;
        }

        List<Integer> temp = new ArrayList<>();
        for (int num : ar) {
            if (temp.size() == 0 || num > temp.get(temp.size() - 1)) {
                temp.add(num);
            } else {
                int left = 0;
                int right = temp.size() - 1;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (temp.get(mid) < num) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                temp.set(right, num);
            }
        }
        return temp.size();
    }

    public static void main(String[] args) {
        List<Integer> ar = List.of(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15);
        System.out.println("Length of the longest increasing subsequence is: " + solve(ar));
    }
}
