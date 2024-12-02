package com.interview.notes.code.year.y2023.july23.test11;

class Solution1 {
    int solution(int[] A) {
        int ans = A[0]; // Initialize ans with the first element of the array
        for (int i = 1; i < A.length; i++) {
            if (ans > A[i]) {
                ans = A[i];
            }
        }
        return ans;
    }
}
