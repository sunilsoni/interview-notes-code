package com.interview.notes.code.months.sept24.amazon.test7;

public class Solution2 {

    /*
     * Complete the 'getSpecialString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String getSpecialString(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // Iterate from the end of the string to the beginning
        for (int i = n - 1; i >= 0; i--) {
            // Try to increment the current character
            for (char c = (char) (arr[i] + 1); c <= 'z'; c++) {
                if ((i > 0 && c == arr[i - 1]) || (i < n - 1 && c == arr[i + 1])) {
                    continue; // Skip if it causes adjacent duplicates
                }
                arr[i] = c;

                // Attempt to build the rest of the string with the smallest possible characters
                if (buildRemainingString(arr, i + 1)) {
                    return new String(arr);
                }
            }
        }
        return "-1";
    }

    // Helper method to build the remaining string from position 'index'
    private static boolean buildRemainingString(char[] arr, int index) {
        int n = arr.length;
        for (int i = index; i < n; i++) {
            boolean found = false;
            // Try to assign the smallest character that doesn't cause duplicates
            for (char c = 'a'; c <= 'z'; c++) {
                if ((i > 0 && c == arr[i - 1]) || (i < n - 1 && c == arr[i + 1])) {
                    continue;
                }
                arr[i] = c;
                found = true;
                break;
            }
            if (!found) {
                return false; // Cannot find a valid character
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "abccde",  // Expected output: abcdab
                "zzab",    // Expected output: -1
                "a",       // Expected output: b
                "zzzz",    // Expected output: -1
                "abcde",   // Expected output: abcdf
                "abab",    // Expected output: abac
                "zzy",     // Expected output: zyz
                "aaa",     // Expected output: aab
                "xyzz",    // Expected output: -1
                "abcdzz",  // Expected output: abceab
                "aabbcc",  // Expected output: aabbcd
                "xyxyxy",  // Expected output: xyxyxz
                "xyxz",    // Expected output: xyya
                "azbzcz",  // Expected output: azbdaa
                "zzz",     // Expected output: -1
                "z",       // Expected output: -1
                "yzz",     // Expected output: -1
                "xxy",     // Expected output: xya
                "abcxyz",  // Expected output: abcyza
        };

        // Iterate through test cases
        for (String testCase : testCases) {
            String result = getSpecialString(testCase);
            System.out.println("Input: " + testCase + " => Output: " + result);
        }
    }
}
