package com.interview.notes.code.june23.test1;

import java.util.HashMap;

public class DistinctElementsArray1 {

    public static void main(String[] args) {
       /* Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int L = sc.nextInt();
        int R = sc.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }*/
        int N = 8;
        int L = 2;
        int R = 6;
        int[] A = {8, 6, 3, 8, 4, 4, 1, 9};

        int result = distinctElements(A, N, L, R);
        System.out.println(result);
        // int result = distinctElements(A, N, L, R);
        // System.out.println(result);
        // sc.close();
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
