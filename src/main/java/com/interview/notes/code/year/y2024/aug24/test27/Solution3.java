package com.interview.notes.code.year.y2024.aug24.test27;

class Solution3 {
    public static void main(String[] args) {
        Solution3 sol = new Solution3();
        System.out.println(sol.solution(11)); // Expected: "dba"
        System.out.println(sol.solution(1));  // Expected: "a"
        System.out.println(sol.solution(67108876)); // Expected: "zzdc"
    }

    public String solution(int N) {
        if (N == 1) {
            return "a";
        }

        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            if (N == 1) {
                sb.append('a');
                break;
            }
            int zPairs = N / 26;
            if (zPairs > 0) {
                sb.append('z');
                N -= 26;
            } else {
                sb.append((char) ('a' + (N / 2) - 1));
                break;
            }
        }
        return sb.reverse().toString();
    }
}
