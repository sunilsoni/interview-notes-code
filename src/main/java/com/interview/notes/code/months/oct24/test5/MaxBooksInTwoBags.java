package com.interview.notes.code.months.oct24.test5;

import java.util.HashSet;
import java.util.Set;

public class MaxBooksInTwoBags {
    public static int maxBooks(int[] books) {
        if (books == null || books.length == 0) {
            return 0;
        }

        int maxBooks = 0;
        int n = books.length;

        for (int start = 0; start < n; start++) {
            Set<Integer> types = new HashSet<>();
            int count = 0;

            for (int i = start; i < n; i++) {
                if (types.size() < 2 || types.contains(books[i])) {
                    types.add(books[i]);
                    count++;
                } else {
                    break;
                }
            }

            maxBooks = Math.max(maxBooks, count);
        }

        return maxBooks;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 2, 3, 4},
                {1, 2, 1, 2, 3},
                {1, 1, 1, 2, 2, 2, 3, 3},
                {1},
                {1, 2, 1, 2, 1, 2},
                {1, 2, 3, 1, 2, 3, 1, 2, 3}
        };

        for (int i = 0; i < testCases.length; i++) {
            int result = maxBooks(testCases[i]);
            System.out.println("Test case " + (i + 1) + ": " + (result == getExpectedResult(i) ? "PASS" : "FAIL") +
                    " (Got: " + result + ", Expected: " + getExpectedResult(i) + ")");
        }
    }

    private static int getExpectedResult(int testCase) {
        switch (testCase) {
            case 0:
                return 2;
            case 1:
                return 4;
            case 2:
                return 8;
            case 3:
                return 1;
            case 4:
                return 6;
            case 5:
                return 6;
            default:
                return -1;
        }
    }
}
