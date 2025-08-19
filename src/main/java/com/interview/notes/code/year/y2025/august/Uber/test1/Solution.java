package com.interview.notes.code.year.y2025.august.Uber.test1;

import java.util.*;
import java.util.stream.*;

public class Solution {
    static int[] solution(int[] numbers) {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        first.add(numbers[0]);
        second.add(numbers[1]);

        for (int i = 2; i < numbers.length; i++) {
            int val = numbers[i];
            long greaterInFirst = first.stream().filter(x -> x > val).count();
            long greaterInSecond = second.stream().filter(x -> x > val).count();

            if (greaterInFirst > greaterInSecond) {
                first.add(val);
            } else if (greaterInSecond > greaterInFirst) {
                second.add(val);
            } else {
                if (first.size() <= second.size()) {
                    first.add(val);
                } else {
                    second.add(val);
                }
            }
        }

        first.addAll(second);
        return first.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        List<int[]> testCases = Arrays.asList(
            new int[]{5, 7, 6, 9, 2},
            new int[]{1, 2},
            new int[]{3, 5, 1, 7, 2, 9, 4},
            IntStream.rangeClosed(1, 20).toArray()
        );

        List<int[]> expected = Arrays.asList(
            new int[]{5, 9, 2, 7, 6},
            new int[]{1, 2},
            new int[]{3, 7, 2, 5, 1, 9, 4},
            new int[]{1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20}
        );

        for (int i = 0; i < testCases.size(); i++) {
            int[] result = solution(testCases.get(i));
            if (Arrays.equals(result, expected.get(i))) {
                System.out.println("Test " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test " + (i + 1) + ": FAIL (Expected " +
                        Arrays.toString(expected.get(i)) + ", Got " + Arrays.toString(result) + ")");
            }
        }
    }
}