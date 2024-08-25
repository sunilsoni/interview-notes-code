package com.interview.notes.code.months.aug24.test27;

public class Solution1 {
    public String solution(int N) {
        StringBuilder result = new StringBuilder();
        
        // Step 1: Handle possible full 'z' transformations
        while (N > 1) {
            int count = 0;
            while ((1 << count) <= N) {
                count++;
            }
            count--; // Last count where 2^count <= N
            result.append((char) ('a' + count));
            N -= (1 << count);
        }
        
        // Step 2: Handle any remaining 'a's
        for (int i = 0; i < N; i++) {
            result.append('a');
        }
        
        // Step 3: Reverse the result to get the largest possible string
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        
        // Example Test Cases
        System.out.println(sol.solution(11)); // Expected output: "dba"
        System.out.println(sol.solution(1)); // Expected output: "a"
        System.out.println(sol.solution(67108876)); // Expected output: "zzdc"
    }
}
