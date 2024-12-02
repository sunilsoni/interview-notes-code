package com.interview.notes.code.year.y2024.april24.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Outcome2 {
    public static List<Integer> missingAndDouble(List<Integer> A) {
        List<Integer> result = new ArrayList<>();
        int[] count = new int[A.size() + 1];
        int repeated = -1, missing = -1;

        for (int num : A) {
            count[num]++;
            if (count[num] == 2) {
                repeated = num;
            }
        }

        for (int i = 1; i <= A.size(); i++) {
            if (count[i] == 0) {
                missing = i;
                break;
            }
        }

        result.add(repeated);
        result.add(missing);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> sample1 = Arrays.asList(3, 2, 3);
        List<Integer> sample2 = Arrays.asList(4, 6, 7, 3, 5, 4, 7, 8);

        List<Integer> result1 = missingAndDouble(sample1);
        List<Integer> result2 = missingAndDouble(sample2);

        System.out.println("Sample 1:");
        System.out.println("Repeated: " + result1.get(0) + ", Missing: " + result1.get(1));
        System.out.println("Sample 2:");
        System.out.println("Repeated: " + result2.get(0) + ", Missing: " + result2.get(1));
    }
}
