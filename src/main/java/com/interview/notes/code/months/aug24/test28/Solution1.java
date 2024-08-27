package com.interview.notes.code.months.aug24.test28;

public class Solution1 {
    public static void main(String[] args) {
        Solution1 sol = new Solution1();

        // Test cases
        System.out.println(sol.solution(11));  // Expected: "dba"
        System.out.println(sol.solution(1));   // Expected: "a"
        System.out.println(sol.solution(67108876));  // Expected: "zzdc"

        // Additional test cases
        System.out.println(sol.solution(2));   // Expected: "b"
        System.out.println(sol.solution(3));   // Expected: "ba"
        System.out.println(sol.solution(26));  // Expected: "z"
        System.out.println(sol.solution(27));  // Expected: "za"
        System.out.println(sol.solution(1000000000));  // Test with max input
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
                // Instead of using repeat(), we'll use a loop
                for (int i = 0; i < remaining; i++) {
                    result.append('a');
                }
                break;
            }
        }

        return result.toString();
    }
}
