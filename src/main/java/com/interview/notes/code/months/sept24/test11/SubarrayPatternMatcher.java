package com.interview.notes.code.months.sept24.test11;

import java.util.Arrays;
/*

WORKING

Given an array of integers rumbers and an array pattern representing a comparison pattern, find how many subarrays of given pattern. pattern can only contain the following integers:
numbers match the
• pattern[i] = 1, represents that the number corresponding to this element of the pattern is greater than the previous one.
• pattern[i] = 0, represents that the number corresponding to this element of the pattern is equa/ to the previous one.
• pattern[1] = -1, represents that the number corresponding to this element of the pattern is less than the previous one.
It is guaranteed that the numbers. length › pattern. length
Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than pattern. length) will fit within the execution time limit.
O(rumbers. length x
Example
• For rumbers = [4, 1, 3, 4, 4, 5, 5, 1] and pattern = [1, 0, 1], the output should be solution(rumbers, pattern) = 1 -
Explanation:
Let's check all possible subarrays of length 3 . Note, that the subarray [4, 1, 3] , starting with numbers [0] = 4 does not need to be
checked, as there is nothing to compare the first element with.
• Subarray [1, 3, 4] doesn't satisfy the pattern. pattern[0] = 1 means that the first element of the subarray should be greater
than the previous one, but numbers [1] = 1 < numbers[0] = 4 .
• Subarray [3, 4, 4] doesn't satisfy the pattern. pattern[1] = 0 means that the second element of the subarray should be
equal to the previous one, but rumbers [3] = 4 != rumbers [2] = 3 .
• Subarray [4, 4, 5] doesn't satisfy the pattern. pattern[2] = -1 means that the third element of the subarray should be less
than the previous one, but numbers [5] = 5 > numbers [4] = 4 .
• Following the same logic, subarray [4, 5, 5] doesn't satisfy the pattern.
• Subarray [5, 5, 1] satisfies the pattern, because:
• rumbers[5] = 5 › rumbers[4] = 4 and pattern[0] = 1 ;
• rumbers [6] = rumbers[5] = 5 and pattern[1] = 0 :
• rumbers[7] = 1 ‹ rumbers[6] = 5 and pattern[2] = -1 ;
Since there is a single subarray that satisfies the given pattern, the answer is 1 .
Input/Output
• [execution time limit] 3 seconds (java)
• [memory limit] 1 GB
• [input] array.integer numbers
An array of integers.
Guaranteed constraints:
2 < numbers. length ≤ 104
0 ≤ numbers [i] ≤ 104
• [input) array.integer pattern
An array of integers, containing only -1s, 0s and 1 s.
Guaranteed constraints:
1 ≤ pattern. length ≤ numbers. length - 1
-1 ≤ pattern[i] ≤ 1 -
• [output] integer
The number of subarrays within the rumbers that satisfies the given pattern.






 */
public class SubarrayPatternMatcher {

    public static int solution(int[] numbers, int[] pattern) {
        int count = 0;
        int patternLength = pattern.length;
        int numbersLength = numbers.length;

        // Iterate through all possible subarrays
        for (int i = 0; i <= numbersLength - patternLength - 1; i++) {
            if (matchesPattern(numbers, pattern, i)) {
                count++;
            }
        }

        return count;
    }

    private static boolean matchesPattern(int[] numbers, int[] pattern, int startIndex) {
        for (int i = 0; i < pattern.length; i++) {
            int current = numbers[startIndex + i + 1];
            int previous = numbers[startIndex + i];
            
            if (pattern[i] == 1 && current <= previous) {
                return false;
            }
            if (pattern[i] == 0 && current != previous) {
                return false;
            }
            if (pattern[i] == -1 && current >= previous) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Test case 1
        int[] numbers1 = {4, 1, 3, 4, 4, 5, 5, 1};
        int[] pattern1 = {1, 0, -1};
        int result1 = solution(numbers1, pattern1);
        System.out.println("Test case 1: " + (result1 == 1 ? "PASS" : "FAIL"));

        // Test case 2 (edge case: minimum length)
        int[] numbers2 = {1, 2};
        int[] pattern2 = {1};
        int result2 = solution(numbers2, pattern2);
        System.out.println("Test case 2: " + (result2 == 1 ? "PASS" : "FAIL"));

        // Test case 3 (edge case: all equal numbers)
        int[] numbers3 = {5, 5, 5, 5, 5};
        int[] pattern3 = {0, 0};
        int result3 = solution(numbers3, pattern3);
        System.out.println("Test case 3: " + (result3 == 3 ? "PASS" : "FAIL"));

        // Test case 4 (larger dataset)
        int[] numbers4 = new int[10000];
        Arrays.fill(numbers4, 1);
        int[] pattern4 = {0, 0, 0};
        int result4 = solution(numbers4, pattern4);
        System.out.println("Test case 4: " + (result4 == 9997 ? "PASS" : "FAIL"));
    }
}