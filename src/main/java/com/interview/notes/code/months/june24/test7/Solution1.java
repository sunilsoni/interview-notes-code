package com.interview.notes.code.months.june24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution1 {

    public static void closestNumbers(List<Integer> numbers) {
        // Sort the numbers to find the closest pairs easily
        Collections.sort(numbers);

        // This will hold the minimum difference
        int minDiff = Integer.MAX_VALUE;

        // This will hold the pairs having the minimum difference
        List<String> pairs = new ArrayList<>();

        // Calculate the minimum difference
        for (int i = 0; i < numbers.size() - 1; i++) {
            int diff = Math.abs(numbers.get(i + 1) - numbers.get(i));
            if (diff < minDiff) {
                minDiff = diff;
                // Clear previous pairs since we found a smaller difference
                pairs.clear();
                pairs.add(numbers.get(i) + " " + numbers.get(i + 1));
            } else if (diff == minDiff) {
                pairs.add(numbers.get(i) + " " + numbers.get(i + 1));
            }
        }

        // Print each pair that has the minimum difference
        for (String pair : pairs) {
            System.out.println(pair);
        }
    }

    public static void main(String[] args) {
        closestNumbers(Arrays.asList(5, -9, -5, 9, 10, 12));
    }
}
