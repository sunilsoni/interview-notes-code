package com.interview.notes.code.year.y2024.aug24.amz.test16;

public class SpecialString {
    public static String getSpecialString1(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Start from the rightmost character
        for (int i = n - 1; i >= 0; i--) {
            // Find the first character that can be incremented
            if (chars[i] < 'z') {
                chars[i]++;

                // Ensure the incremented character doesn't match its left neighbor
                if (i > 0 && chars[i] == chars[i - 1]) {
                    chars[i]++;
                }

                // Fill the rest with the smallest possible characters
                for (int j = i + 1; j < n; j++) {
                    chars[j] = 'a';
                    // Ensure no adjacent characters are the same
                    if (j > 0 && chars[j] == chars[j - 1]) {
                        chars[j] = 'b';
                    }
                }

                return new String(chars);
            }
        }

        // If we can't increment any character, return "-1"
        return "-1";
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abccde")); // Expected: abcdab     but actual is: abcdef
        System.out.println(getSpecialString("zzab"));   // Expected: -1
        System.out.println(getSpecialString("abbd"));   // Expected: abca     but actual is: abce
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

                // After incrementing, set all characters after the incremented one to 'a'
                for (int j = i + 1; j < n; j++) {
                    arr[j] = 'a';
                }

                // Ensure no adjacent duplicates by fixing the characters after increment
                for (int k = 1; k < n; k++) {
                    if (arr[k] == arr[k - 1]) {
                        arr[k] = (char) (arr[k] + 1);
                        if (arr[k] > 'z') {
                            return "-1";
                        }
                    }
                }

                // Final validation to ensure no two adjacent characters are the same
                for (int k = 1; k < n; k++) {
                    if (arr[k] == arr[k - 1]) {
                        return "-1";
                    }
                }

                return new String(arr);
            }
        }

        // If no valid special string can be formed, return -1
        return "-1";
    }

    public static String getSpecialString4(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Start from the end and work backward to find the first character that can be incremented
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != 'z') {
                arr[i] = (char) (arr[i] + 1);

                // After incrementing, set all characters after the incremented one to 'a'
                for (int j = i + 1; j < n; j++) {
                    arr[j] = 'a';
                }

                // Ensure no adjacent duplicates by fixing the characters after increment
                for (int k = 1; k < n; k++) {
                    if (arr[k] == arr[k - 1]) {
                        arr[k] = (char) (arr[k] + 1);
                        if (arr[k] > 'z') {
                            return "-1";
                        }
                    }
                }

                // Final check to ensure no two adjacent characters are the same
                for (int k = 1; k < n; k++) {
                    if (arr[k] == arr[k - 1]) {
                        return "-1";
                    }
                }

                return new String(arr);
            }
        }

        // If no valid special string can be formed, return -1
        return "-1";
    }

    public static String getSpecialString3(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Start from the end and work backward to find the first character that can be incremented
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] != 'z') {
                arr[i] = (char) (arr[i] + 1);

                // After incrementing, set all characters after the incremented one to 'a'
                for (int j = i + 1; j < n; j++) {
                    arr[j] = 'a';
                }

                // Ensure no adjacent duplicates by fixing the characters after increment
                for (int k = 1; k < n; k++) {
                    if (arr[k] == arr[k - 1]) {
                        arr[k] = (char) (arr[k] + 1);
                        if (arr[k] > 'z') {
                            return "-1";
                        }
                    }
                }

                // Validate and return the final string if it is "special"
                if (isSpecial(arr)) {
                    return new String(arr);
                }
            }
        }

        // If no valid special string can be formed, return -1
        return "-1";
    }

    // Helper method to check if the string has no adjacent duplicate characters
    private static boolean isSpecial(char[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static String getSpecialString2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;

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

        // Fill the rest with alternating 'a' and 'b'
        for (int j = i + 1; j < n; j++) {
            chars[j] = (j % 2 == 0) ? 'a' : 'b';
        }

        // Ensure the incremented character is different from its neighbors
        if (i > 0 && chars[i] == chars[i - 1]) {
            chars[i]++;
        }
        if (i < n - 1 && chars[i] == chars[i + 1]) {
            chars[i]++;
        }

        // If we've gone past 'z', we need to propagate the change leftward
        while (i >= 0 && chars[i] > 'z') {
            chars[i] = 'a';
            i--;
            if (i >= 0) {
                chars[i]++;
                // Ensure it's different from its left neighbor
                if (i > 0 && chars[i] == chars[i - 1]) {
                    chars[i]++;
                }
            }
        }

        // Final check: if we couldn't create a valid string, return "-1"
        if (i == -1) {
            return "-1";
        }

        // Ensure the first two characters are different if needed
        if (n > 1 && chars[0] == chars[1]) {
            chars[1] = (chars[1] == 'z') ? 'a' : (char) (chars[1] + 1);
        }

        return new String(chars);
    }

    public static String getSpecialStringOLD(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Start from the end of the string and try to find a place to modify
        for (int i = n - 1; i >= 0; i--) {
            // Increase the character if it's not 'z'
            if (arr[i] != 'z') {
                arr[i]++;

                // Check if the string still remains "special"
                if (i == 0 || arr[i] != arr[i - 1]) {
                    // Ensure the rest of the string after this position is 'a' and valid
                    for (int j = i + 1; j < n; j++) {
                        arr[j] = 'a';
                        if (arr[j] == arr[j - 1]) {
                            arr[j]++;
                        }
                    }
                    return new String(arr);
                }
            }
        }

        // If no valid "special" string was found
        return "-1";
    }

}
