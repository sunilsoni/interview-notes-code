package com.interview.notes.code.year.y2023.Sep23;

import java.util.List;

public class PizzaTreats {
    public static String solve(List<Integer> visitors) {
        int n = visitors.size();

        for (int i = 0; i < n - 1; i++) {
            if (visitors.get(i) % 2 == 1) {
                // If using a coupon today and no visitors tomorrow
                if (visitors.get(i + 1) == 0) {
                    return "NO";
                }
                visitors.set(i + 1, visitors.get(i + 1) + 1);
            }
        }

        // Check if last day has odd visitors
        if (visitors.get(n - 1) % 2 == 1) {
            return "NO";
        } else {
            return "YES";
        }
    }

    public static void main(String[] args) {
        // You can test the solve() method here.
    }
}
