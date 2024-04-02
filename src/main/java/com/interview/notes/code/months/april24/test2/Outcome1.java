package com.interview.notes.code.months.april24.test2;

import java.util.*;

class Outcome1 {
    public static int solve(List<Integer> ar) {
        int count = 0;
        for (int i = 1; i < ar.size() - 1; i++) {
            if ((ar.get(i) > ar.get(i - 1) && ar.get(i) > ar.get(i + 1)) || (ar.get(i) < ar.get(i - 1) && ar.get(i) < ar.get(i + 1))) {
                count++;
                if (i + 2 < ar.size() && ((ar.get(i - 1) > ar.get(i) && ar.get(i) < ar.get(i + 1) && ar.get(i + 1) > ar.get(i + 2)) || (ar.get(i - 1) < ar.get(i) && ar.get(i) > ar.get(i + 1) && ar.get(i + 1) < ar.get(i + 2)))) {
                    ar.remove(i);
                    i--;
                } else {
                    ar.remove(i + 1);
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        // Example usage:
        List<Integer> input = Arrays.asList(1, 2, 3, 6, 5, 4, 8);
        System.out.println(solve(input)); // This should output 3
    }
}

