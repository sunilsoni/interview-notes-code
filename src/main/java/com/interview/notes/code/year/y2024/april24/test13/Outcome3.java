package com.interview.notes.code.year.y2024.april24.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Outcome3 {
    public static List<Integer> missingAndDouble(List<Integer> A) {
        Collections.sort(A);
        List<Integer> result = new ArrayList<>();
        int doubleNum = -1;
        int missingNum = 1;

        for (int i = 0; i < A.size() - 1; i++) {
            if (A.get(i).equals(A.get(i + 1))) {
                doubleNum = A.get(i);
            } else if (A.get(i) + 1 < A.get(i + 1)) {
                missingNum = A.get(i) + 1;
            }
        }

        if (A.get(A.size() - 1) != A.size()) {
            missingNum = A.size();
        }

        result.add(doubleNum);
        result.add(missingNum);
        return result;
    }


    public static void main(String[] args) {
        List<Integer> sample1 = Arrays.asList(3, 2, 3);
        List<Integer> sample2 = Arrays.asList(4, 6, 7, 3, 5, 4, 7, 8);//41

        List<Integer> result1 = missingAndDouble(sample1);
        List<Integer> result2 = missingAndDouble(sample2);

        System.out.println("Sample 1:");
        System.out.println("Repeated: " + result1.get(0) + ", Missing: " + result1.get(1));
        System.out.println("Sample 2:");
        System.out.println("Repeated: " + result2.get(0) + ", Missing: " + result2.get(1));
    }
}
