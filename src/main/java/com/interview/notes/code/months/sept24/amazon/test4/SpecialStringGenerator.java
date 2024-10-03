package com.interview.notes.code.months.sept24.amazon.test4;

public class SpecialStringGenerator {

    public static String getSpecialString(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Try to increment from the last character backwards
        for (int i = n - 1; i >= 0; i--) {
            // Try to increment the current character
            for (char ch = (char) (arr[i] + 1); ch <= 'z'; ch++) {
                // Ensure no adjacent characters are the same
                if ((i > 0 && ch == arr[i - 1]) || (i < n - 1 && ch == arr[i + 1])) {
                    continue; // Skip if the new character creates an adjacency conflict
                }

                // We found a valid character, replace it
                arr[i] = ch;

                // Now fill the remaining part of the string with the smallest valid characters
                for (int j = i + 1; j < n; j++) {
                    for (char nextChar = 'a'; nextChar <= 'z'; nextChar++) {
                        if ((j > 0 && nextChar == arr[j - 1]) || (j < n - 1 && nextChar == arr[j + 1])) {
                            continue; // Skip if nextChar creates an adjacency conflict
                        }
                        arr[j] = nextChar;
                        break;
                    }
                }

                return new String(arr); // Return the modified string
            }
        }

        // If we couldn't find a valid string
        return "-1";
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
