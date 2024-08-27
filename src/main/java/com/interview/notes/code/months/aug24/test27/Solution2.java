package com.interview.notes.code.months.aug24.test27;

class Solution2 {
    public static void main(String[] args) {
        Solution2 sol = new Solution2();

        // Test cases
        System.out.println(sol.solution(11)); // Expected: "dba"
        System.out.println(sol.solution(1));  // Expected: "a"
        System.out.println(sol.solution(67108876)); // Expected: "zzdc"

    }

    public String solution(int N) {
        StringBuilder sb = new StringBuilder("a".repeat(N));

        while (true) {
            boolean transformed = false;
            for (int i = 0; i < sb.length() - 1; i++) {
                if (sb.charAt(i) == sb.charAt(i + 1)) {
                    sb.setCharAt(i, (char) (sb.charAt(i) + 1));
                    sb.deleteCharAt(i + 1);
                    transformed = true;
                    break;
                }
            }

            if (!transformed) {
                break;
            }
        }

        return sb.toString();
    }

    public String solution2(int N) {
        if (N == 1) {
            return "a";
        }
        StringBuilder result = new StringBuilder();
        int count = N;
        while (count > 0) {
            if (count >= 26) {
                result.append('z');
                count -= 26;
            } else {
                result.append((char) ('a' + count - 1));
                count = 0;
            }
        }
        return result.reverse().toString();
    }
}