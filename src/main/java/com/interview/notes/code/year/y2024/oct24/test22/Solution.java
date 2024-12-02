package com.interview.notes.code.year.y2024.oct24.test22;

import java.io.*;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // Example 1
        String caption1 = "aca";
        int result1 = getMinTransformations(caption1);
        bw.write("Example 1: " + caption1 + " -> " + result1);
        bw.newLine();

        // Example 2
        String caption2 = "abab";
        int result2 = getMinTransformations(caption2);
        bw.write("Example 2: " + caption2 + " -> " + result2);
        bw.newLine();

        // Example 3
        String caption3 = "abcdef";
        int result3 = getMinTransformations(caption3);
        bw.write("Example 3: " + caption3 + " -> " + result3);
        bw.newLine();

        // Large input example
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append((char) ('a' + i % 26));
        }
        String largeInput = sb.toString();
        long startTime = System.currentTimeMillis();
        int resultLarge = getMinTransformations(largeInput);
        long endTime = System.currentTimeMillis();
        bw.write("Large input (100,000 characters) -> " + resultLarge);
        bw.newLine();
        bw.write("Time taken: " + (endTime - startTime) + " ms");
        bw.newLine();

        // Custom input
        bw.write("Enter your own input:");
        bw.newLine();
        bw.flush();
        String customInput = br.readLine().trim();
        int customResult = getMinTransformations(customInput);
        bw.write("Result: " + customResult);
        bw.newLine();

        br.close();
        bw.close();
    }
}
