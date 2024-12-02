package com.interview.notes.code.year.y2024.oct24.test23;

public class Solution {
    public static int getMinTransformations(String caption) {
        int n = caption.length();
        char[] chars = caption.toCharArray();
        int totalCost = 0;

        for (int i = 0; i < n; i++) {
            char c_i = chars[i];
            char c_i_prev = (i > 0) ? chars[i - 1] : '\0';
            char c_i_next = (i < n - 1) ? chars[i + 1] : '\0';

            boolean alone = true;

            if (i > 0 && c_i == c_i_prev) {
                alone = false;
            }
            if (i < n - 1 && c_i == c_i_next) {
                alone = false;
            }

            if (alone) {
                int costLeft = Integer.MAX_VALUE;
                int costRight = Integer.MAX_VALUE;

                if (i > 0) {
                    costLeft = Math.abs(chars[i] - chars[i - 1]);
                }
                if (i < n - 1) {
                    costRight = Math.abs(chars[i] - chars[i + 1]);
                }

                if (costLeft <= costRight && i > 0) {
                    totalCost += costLeft;
                    chars[i] = chars[i - 1];
                } else if (i < n - 1) {
                    totalCost += costRight;
                    chars[i] = chars[i + 1];
                }
            }
        }

        return totalCost;
    }
}
