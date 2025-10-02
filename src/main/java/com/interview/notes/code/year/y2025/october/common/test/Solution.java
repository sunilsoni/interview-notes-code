package com.interview.notes.code.year.y2025.october.common.test;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println(solution.longestCommonPrefix(strs1));  // Output: "fl"

        // Test case 2
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println(solution.longestCommonPrefix(strs2));  // Output: ""
    }

    public String longestCommonPrefix(String[] strs) {
        // If array is empty, return empty string
        if (strs == null || strs.length == 0) {
            return "";
        }

        // Take first string as reference
        String firstStr = strs[0];

        // For each character in first string
        for (int i = 0; i < firstStr.length(); i++) {
            char currentChar = firstStr.charAt(i);

            // Compare this character with same position in other strings
            for (int j = 1; j < strs.length; j++) {
                // If we reached end of any other string
                // Or if characters don't match
                if (i >= strs[j].length() || strs[j].charAt(i) != currentChar) {
                    // Return substring from 0 to current position
                    return firstStr.substring(0, i);
                }
            }
        }

        // If we get here, entire first string is common prefix
        return firstStr;
    }

}
