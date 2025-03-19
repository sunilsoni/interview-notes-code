package com.interview.notes.code.year.y2025.march.common.test17;

public class CommonSubstringFinder {

    // Method to find the longest common substring in an array of strings.
    public static String longestCommonSubstring(String[] arr) {
        if (arr == null || arr.length == 0) return "";

        // Find the shortest string (to minimize the number of substrings to check)
        String shortest = arr[0];
        for (String s : arr) {
            if (s.length() < shortest.length()) {
                shortest = s;
            }
        }

        String longestCommon = "";
        int len = shortest.length();

        // Iterate over all possible substrings of the shortest string.
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String candidate = shortest.substring(i, j);
                boolean isCommon = true;
                // Check if the candidate substring is in every string in the array.
                for (String s : arr) {
                    if (!s.contains(candidate)) {
                        isCommon = false;
                        break;
                    }
                }
                // If the candidate is common and longer than our current result, update it.
                if (isCommon && candidate.length() > longestCommon.length()) {
                    longestCommon = candidate;
                }
            }
        }
        return longestCommon;
    }

    public static void main(String[] args) {
        String[] arr = {"abc", "abcd", "adcd", "abcefg"};
        String commonSubstring = longestCommonSubstring(arr);
        System.out.println("Longest common substring: " + commonSubstring);
    }
}