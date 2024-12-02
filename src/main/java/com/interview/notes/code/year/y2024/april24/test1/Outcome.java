package com.interview.notes.code.year.y2024.april24.test1;

import java.util.List;


public class Outcome {


    public static int solve(List<Integer> ar) {
        int removals = 0;
        for (int i = 0; i < ar.size() - 2 && ar.size() - removals > 2; ++i) {
            if (ar.get(i) < ar.get(i + 1) && ar.get(i + 1) < ar.get(i + 2) ||
                    ar.get(i) > ar.get(i + 1) && ar.get(i + 1) > ar.get(i + 2)) {
                removals++;
                i++;
            }
        }
        return removals;
    }

    public static void main(String[] args) {
        // Example usage:
        List<Integer> input = List.of(1, 2, 3, 6, 5, 4, 8);
        System.out.println(solve(input)); // This should output 3
    }
}
