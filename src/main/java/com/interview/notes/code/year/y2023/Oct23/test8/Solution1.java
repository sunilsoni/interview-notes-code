package com.interview.notes.code.year.y2023.Oct23.test8;

class Solution1 {
    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        int[] counterExample = sol.solution(4);

        for (int num : counterExample) {
            System.out.print(num + " ");
        }
    }

    public int[] solution(int N) {
        // Initialize an array of size N
        int[] A = new int[N];

        // Fill the array with integers larger than 0, since ans starts at 0 in find_min
        for (int i = 0; i < N; i++) {
            A[i] = i + 1;
        }

        return A;
    }
}
