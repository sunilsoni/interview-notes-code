package com.interview.notes.code.months.aug24.amz.test16;

public class SpecialString1 {

    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abccde")); // Expected: abcdab     but actual is: abccdf
        System.out.println(getSpecialString("zzab"));   // Expected: -1     but actual is: zzac
        System.out.println(getSpecialString("abbd"));   // Expected: abca     but actual is: abbe
        System.out.println(getSpecialString("z"));      // Expected: -1     but actual is: -1
        System.out.println(getSpecialString("a"));      // Expected: b     but actual is: b
    }

    public static String getSpecialString(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Start from the end and work backward to find the first character that can be incremented
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != 'z') {
                arr[i] = (char) (arr[i] + 1);

                // After incrementing, reset all characters after the incremented one to 'a'
                for (int j = i + 1; j < n; j++) {
                    arr[j] = 'a';
                }

                // Ensure no adjacent duplicates by fixing the characters after increment
                for (int k = 1; k < n; k++) {
                    if (arr[k] == arr[k - 1]) {
                        if (arr[k] == 'z') {
                            return "-1";
                        }
                        arr[k] = (char) (arr[k] + 1);
                    }
                }

                return new String(arr);
            }
        }

        // If no valid special string can be formed, return -1
        return "-1";
    }

    public static String getSpecialString3(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Special case for single character
        if (n == 1) {
            return chars[0] == 'z' ? "-1" : String.valueOf((char) (chars[0] + 1));
        }

        // Find the rightmost character that can be incremented
        int i = n - 1;
        while (i >= 0 && chars[i] == 'z') {
            i--;
        }

        // If all characters are 'z', no solution exists
        if (i == -1) {
            return "-1";
        }

        // Increment the character
        chars[i]++;

        // Reset all characters to the right
        for (int j = i + 1; j < n; j++) {
            chars[j] = 'a';
        }

        // Ensure no adjacent characters are the same
        for (int j = 0; j < n - 1; j++) {
            if (chars[j] == chars[j + 1]) {
                if (chars[j + 1] < 'z') {
                    chars[j + 1]++;
                } else {
                    chars[j + 1] = 'a';
                    chars[j]++;
                }
            }
        }

        // Final check for first two characters
        if (chars[0] == chars[1]) {
            if (chars[1] < 'z') {
                chars[1]++;
            } else {
                return "-1";  // No valid solution
            }
        }

        return new String(chars);
    }

    public static String getSpecialString2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = n - 1; i >= 0; i--) {
            char original = chars[i];
            chars[i]++;

            while (chars[i] <= 'z') {
                if ((i == 0 || chars[i] != chars[i - 1]) && (i == n - 1 || chars[i] != chars[i + 1])) {
                    // Found a valid character
                    for (int j = i + 1; j < n; j++) {
                        chars[j] = 'a';
                        while (chars[j] == chars[j - 1] || (j < n - 1 && chars[j] == chars[j + 1])) {
                            chars[j]++;
                        }
                    }
                    return new String(chars);
                }
                chars[i]++;
            }

            // Reset this character and move to the next
            chars[i] = original;
        }

        return "-1";
    }

    public static String getSpecialString1(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        for (int i = n - 1; i >= 0; i--) {
            if (chars[i] < 'z') {
                chars[i]++;

                // Check if the new character matches its left neighbor
                if (i > 0 && chars[i] == chars[i - 1]) {
                    continue; // Try the next character to the left
                }

                // Fill the rest with the smallest possible characters
                for (int j = i + 1; j < n; j++) {
                    char smallest = 'a';
                    while ((j > 0 && smallest == chars[j - 1]) || (j < n - 1 && smallest == chars[j + 1])) {
                        smallest++;
                    }
                    chars[j] = smallest;
                }

                return new String(chars);
            }
        }

        return "-1";
    }


}
