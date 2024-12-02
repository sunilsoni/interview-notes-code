package com.interview.notes.code.year.y2024.aug24.test26;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 sol = new Solution3();

        // Test cases
        System.out.println(sol.solution(11));  // Expected: "dba"
        System.out.println(sol.solution(1));   // Expected: "a"
        System.out.println(sol.solution(67108876));  // Expected: "zzdc"

        // Additional test cases
        System.out.println(sol.solution(2));   // Expected: "b"
        System.out.println(sol.solution(3));   // Expected: "ba"
        System.out.println(sol.solution(26));  // Expected: "z"
        System.out.println(sol.solution(27));  // Expected: "za"
    }

    public String solution(int N) {
        StringBuilder result = new StringBuilder();
        int remaining = N;

        for (char c = 'z'; c >= 'a'; c--) {
            int pairsNeeded = (1 << (c - 'a')) / 2;

            while (remaining >= pairsNeeded * 2) {
                result.append(c);
                remaining -= pairsNeeded * 2;
            }

            if (c == 'a' && remaining > 0) {
                result.append("a".repeat(remaining));
                break;
            }
        }

        return result.toString();
    }
}
