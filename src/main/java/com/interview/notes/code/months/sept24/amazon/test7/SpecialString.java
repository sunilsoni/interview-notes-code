package com.interview.notes.code.months.sept24.amazon.test7;

public class SpecialString {

    /*
     * Complete the 'getSpecialString' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String getSpecialString(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // Precompute whether the prefix up to each position is special
        boolean[] isSpecialUpTo = new boolean[n];
        isSpecialUpTo[0] = true;
        for (int i = 1; i < n; i++) {
            isSpecialUpTo[i] = isSpecialUpTo[i - 1] && (arr[i] != arr[i - 1]);
        }

        // Iterate from the end of the string to the beginning
        for (int i = n - 1; i >= 0; i--) {
            // Only proceed if the prefix up to i-1 is special
            if (i > 0 && !isSpecialUpTo[i - 1]) {
                continue;
            }

            // Try to increment the current character
            for (char c = (char) (arr[i] + 1); c <= 'z'; c++) {
                if ((i > 0 && c == arr[i - 1]) || (i < n - 1 && c == arr[i + 1])) {
                    continue; // Skip if it causes adjacent duplicates
                }
                arr[i] = c;

                // Update isSpecialUpTo[i]
                isSpecialUpTo[i] = (i == 0) || (isSpecialUpTo[i - 1] && arr[i] != arr[i - 1]);
                if (!isSpecialUpTo[i]) {
                    continue;
                }

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
        System.out.println(getSpecialString("abcd")); // Output: 4
        System.out.println(getSpecialString("fghbbadcba")); // Output: 5
        System.out.println(getSpecialString("aabbccdd"));  // Output: 0
        System.out.println(getSpecialString("zabcdzy"));  // Output: 5
        System.out.println(getSpecialString("zzaab"));  // Output: 5
        System.out.println(getSpecialString("abccde"));  // Output: abcdab

        System.out.println(getSpecialString("vbyetnoababababababababababababababababababababababababababcabababababababababababababcababababababa"));



    }
}
