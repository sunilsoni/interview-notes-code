package com.interview.notes.code.months.Oct23.test3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Kaprekar {
    // Main function to calculate steps for a given number
    public static int stepsTo6174(int number) {
        // Check if the number has all identical digits
        if (allSameDigits(number)) return 0;

        int steps = 0;
        // A set to detect loops
        Set<Integer> seen = new HashSet<>();

        while (number != 6174 && !seen.contains(number)) {
            seen.add(number);
            number = nextNumber(number);
            steps++;
        }

        if (number == 6174) return steps;
        else return -1;  // -1 indicates that 6174 is not reachable
    }

    private static boolean allSameDigits(int number) {
        String s = String.valueOf(number);
        char firstChar = s.charAt(0);
        for (char c : s.toCharArray()) {
            if (c != firstChar) return false;
        }
        return true;
    }

    private static int nextNumber(int number) {
        String s = String.format("%04d", number); // Pad with leading zeros
        char[] chars = s.toCharArray();

        // Sort ascending
        Arrays.sort(chars);
        String ascending = new String(chars);

        // Sort descending
        String descending = new StringBuilder(ascending).reverse().toString();

        int larger = Integer.parseInt(descending);
        int smaller = Integer.parseInt(ascending);

        return larger - smaller;
    }

    public static void main(String[] args) {
        for (int i = 1000; i <= 9999; i++) {
            int steps = stepsTo6174(i);
            if (steps != -1) {
                System.out.printf("Number %d takes %d steps to reach 6174.\n", i, steps);
            } else {
                System.out.printf("Number %d can't reach 6174.\n", i);
            }
        }
    }
}
