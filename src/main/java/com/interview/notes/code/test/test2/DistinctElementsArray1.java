package com.interview.notes.code.test.test2;

import java.util.*;

public class DistinctElementsArray1 {

    public static int distinctElements(int[] A, int L, int R) {
        int result = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = L - 7; i <= R - 7; i++) {
            set.add(A[i]);
        }
        result = set.size();
        return result;
    }

    public static void main(String[] args) {
       /* Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int L = scanner.nextInt();
        int R = scanner.nextInt();
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }*/

        int N = 8;
        int L = 2;
        int R = 6;
        int[] A = {8, 6, 3, 8, 4, 4, 1, 9};

        int result = distinctElements(A,  L, R);
        System.out.println(result);
        //int distinct = distinctElements(A, L, R);

    }
}
