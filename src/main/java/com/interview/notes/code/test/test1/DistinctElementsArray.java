package com.interview.notes.code.test.test1;

import java.util.HashMap;

public class DistinctElementsArray {

    public static void main(String[] args) {
        int N = 8;
        int L = 2;
        int R = 6;
        int[] A = {8, 6, 3, 8, 4, 4, 1, 9};

        int result = distinctElements(A, N, L, R);
        System.out.println(result);
    }

    public static int distinctElements(int[] A, int N, int L, int R) {
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        int distinctCount = 0;

        for (int i = L - 7; i <= R - 7; i++) {
            if (frequencyMap.containsKey(A[i])) {
                if (frequencyMap.get(A[i]) == 1) {
                    distinctCount--;
                }
                frequencyMap.put(A[i], frequencyMap.get(A[i]) + 1);
            } else {
                frequencyMap.put(A[i], 1);
                distinctCount++;
            }
        }

        return distinctCount;
    }
}
