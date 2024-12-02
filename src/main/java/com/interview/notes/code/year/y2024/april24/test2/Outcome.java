package com.interview.notes.code.year.y2024.april24.test2;

import java.util.List;

public class Outcome {

    public static int solve(List<Integer> ar) {
        int removals = 0;
        for (int i = 2; i < ar.size(); i++) {
            if (ar.get(i) > ar.get(i - 1) && ar.get(i - 1) > ar.get(i - 2) ||
                    ar.get(i) < ar.get(i - 1) && ar.get(i - 1) < ar.get(i - 2)) {
                removals++;
                i++; // Skip the next element as it's part of a triplet that caused removal.
            }
        }
        return removals;
    }

    public static void main(String[] args) {
        // Example usage:
        List<Integer> input1 = List.of(1, 2, 3, 6, 5, 4, 8);
        List<Integer> input2 = List.of(1, 2, 4, 1, 2);
        System.out.println(solve(input1)); // This should output 3
        System.out.println(solve(input2)); // This should output 1
    }
}
