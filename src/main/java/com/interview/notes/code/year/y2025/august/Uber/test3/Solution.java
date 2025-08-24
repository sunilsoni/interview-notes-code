package com.interview.notes.code.year.y2025.august.Uber.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
    static int solution(int[] numbers, int threshold) {
        for (int i = 0; i <= numbers.length - 3; i++) {
            if (numbers[i] > threshold && numbers[i + 1] > threshold && numbers[i + 2] > threshold) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        List<int[]> testCases = Arrays.asList(
                new int[]{0, 1, 4, 3, 2, 5},
                new int[]{-9, 95, 94, 4, 51},
                new int[]{5, 6, 7, 8, 9},
                new int[]{1, 1, 1, 1},
                IntStream.range(0, 2000).toArray()
        );

        List<Integer> thresholds = Arrays.asList(1, 42, 4, 2, 500);
        List<Integer> expected = Arrays.asList(2, -1, 0, -1, 501);

        for (int i = 0; i < testCases.size(); i++) {
            int result = solution(testCases.get(i), thresholds.get(i));
            if (result == expected.get(i)) {
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL (Expected " + expected.get(i) + ", Got " + result + ")");
            }
        }
    }
}