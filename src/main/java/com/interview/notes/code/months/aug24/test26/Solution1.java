package com.interview.notes.code.months.aug24.test26;

public class Solution1 {
    public String solution(int N) {
        int[] stack = new int[26];
        int index = 25; // Start from 'z'
        
        while (N > 0 && index > 0) {
            int count = N / 2;
            stack[index] = count;
            N -= count * 2;
            if (N > 0) {
                stack[--index] = 1;
                N--;
            }
            index--;
        }
        
        if (N > 0) {
            stack[0] = N; // Remaining 'a's
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            result.append(String.valueOf((char)('a' + i)).repeat(stack[i]));
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        
        // Test cases
        System.out.println(solution.solution(11)); // Expected: "dba"
        System.out.println(solution.solution(1));  // Expected: "a"
        System.out.println(solution.solution(67108876)); // Expected: "zzdc"
        
        // Additional test cases
        System.out.println(solution.solution(5));  // Expected: "ca"
        System.out.println(solution.solution(26)); // Expected: "z"
        System.out.println(solution.solution(27)); // Expected: "za"
    }
}
