package com.interview.notes.code.year.y2024.aug24.test26;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        // Test cases
        System.out.println(sol.solution(11));  // Expected: dba
        System.out.println(sol.solution(1));   // Expected: a
        System.out.println(sol.solution(67108876));  // Expected: zzdc

        // Additional test cases
        System.out.println(sol.solution(5));   // Expected: ba
        System.out.println(sol.solution(26));  // Expected: z
        System.out.println(sol.solution(100)); // Expected: zza
    }

    public String solution(int N) {
        StringBuilder result = new StringBuilder();
        int remaining = N;

        for (char c = 'z'; c >= 'a'; c--) {
            int count = remaining / 2;
            remaining %= 2;

            if (count > 0 || (c == 'a' && remaining > 0)) {
                result.append(String.valueOf(c).repeat(count + remaining));
                remaining = 0;
            }

            if (result.length() > 0 && result.charAt(result.length() - 1) == c + 1) {
                result.setLength(result.length() - 1);
                remaining += 2;
            }
        }

        return result.toString();
    }
}
