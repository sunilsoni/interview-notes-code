package com.interview.notes.code.months.aug24.test28;

/*
WORKING:


Task 1
There is a string of length N made only of letters "a". Whenever there are two identical adjacent letters (e.g. "aa"), they can be transformed into a single letter that is the next letter of the alphabet. For example, "aa" can be transformed into
"b" and "ee" into "f". However, "zz" cannot be further transformed.
What is the alphabetically largest string that can be obtained from the initial string?
Write a function:
class Solution { public String solution(int N); }
that, given an integer N, returns the alphabetically largest string that can be obtained after such transformations.
Examples:
1. Given N = 11, the function should return "dba". The initial string "aaaaaaaaaaa"
can be transformed in the following manner: "aaaaaaaaaaa" → "bbbbba" → "ccba"
→ "dba".
2. Given N = 1, the function should return "a". The initial string "a" cannot be
transformed in any way.
3. Given N = 67108876, the function should return "zzdc".
Write an efficient algorithm for the following assumptions:
• Nis an integer within the range [1.1,000,000,000).

 */
class SolutionWorking {
    public String solution(int N) {
        if (N == 1) return "a";

        StringBuilder result = new StringBuilder();
        int remaining = N;

        for (char c = 'z'; c >= 'a'; c--) {
            int pairsNeeded = 1 << (c - 'a');
            while (remaining >= pairsNeeded) {
                result.append(c);
                remaining -= pairsNeeded;
            }
            if (c == 'a' && remaining > 0) {
                result.append('a');
                break;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SolutionWorking sol = new SolutionWorking();
        
        // Test cases
        System.out.println(sol.solution(11)); // Expected: "dba"
        System.out.println(sol.solution(1));  // Expected: "a"
        System.out.println(sol.solution(67108876)); // Expected: "zzdc"
        
        // Additional test cases
        System.out.println(sol.solution(1000000000)); // Max input
        
        // Performance test
        long startTime = System.nanoTime();
        sol.solution(1000000000);
        long endTime = System.nanoTime();
        System.out.println("Time taken for max input: " + (endTime - startTime) / 1000000.0 + " ms");
    }
}
