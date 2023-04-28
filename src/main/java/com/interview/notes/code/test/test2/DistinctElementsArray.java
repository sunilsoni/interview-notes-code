package com.interview.notes.code.test.test2;

import java.util.*;

public class DistinctElementsArray {

    public static int distinctElements(int[] A, int L, int R) {
        int result = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = L - 1; i < R; i++) {
            set.add(A[i]);
        }
        result = set.size();
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 8;
        int L = 2;
        int R = 6;
        int[] A = {8, 6, 3, 8, 4, 4, 4, 9};
        int distinct = distinctElements(A, L, R);
        System.out.println(distinct);
        scanner.close();
    }
}
