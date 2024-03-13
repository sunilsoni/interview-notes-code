package com.interview.notes.code.months.march24.test8;

/**
 * 1. Question 1
 * The discovery of the structure of DNA in 1953 was made possible by Dr. Franklin's X-ray diffraction. Dr. Franklin's creation of the famous Photo 51 demonstrated the double-helix structure of deoxyribonucleic acid. Palindromic structures are widely studied in string processing and combinatorics and have applications in the analysis of DNA.
 * During her studies, Rosalind had come across a problem where she was given a string s consisting of lowercase English letters and the character '?'. She was required to develop an algorithm that would interpolate the string by replacing the question marks with lowercase English letters such that it can be rearranged to form a palindrome. If there were more than one palindromes possible, it had to be lexicographically smallest one, and if there were none, the algorithm would return "-1" as the answer.
 * Note:
 * • A String p is lexicographically smaller than string q, if pis a prefix of q and pis not equal to q or there exists i, such that p; < q; and for all j< i. it is satisfied that pj= qj
 * • A palindrome is a string that reads the same backward as forward; for example, strings "z", "aaa", "aba", "abccba" are palindromes, but strings "hackerland", "reality", and "ab" are not.
 * Example
 * Given, 5 = "axxb??".
 * The two question marks can be replaced with the characters 'a' and 'b' respectively to form a string 5 = "axxbab". The string can be
 * rearranged to "abba" which is the lexicographically smallest palindrome possible by interpolating the string.
 * Function Description
 * Complete the function getSmallestPalindrome in the editor below.
 * getSmallestPalindrome has the following parameter:
 * strings: a string
 * Returns
 * string: the lexicographically smallest palindrome possible or -1
 * Constraints
 * •1≤/5/≤105
 * • String s contains only lowercase English letters and '?'s.
 * <p>
 * <p>
 * <p>
 * Constraints
 * ・ 1≤/5/≤105
 * • String s contains only lowercase English letters and '?'s.
 * • Input Format For Custom Testing
 * The first line contains a string, s.
 * • Sample Case 0
 * Sample Input For Custom Testing
 * STDIN
 * ーーーーー
 * a?rt???
 * FUNCTION
 * S =
 * "a?rt???"
 * Sample Output
 * aartraa
 * Explanation
 * The question marks can be replaced with 'a', 'r', 'a', and 'a' to form the palindrome "aartraa".
 * • Sample Case 1
 * Sample Input For Custom Testing
 * STDIN
 * FUNCTION
 * yh??tx
 * →
 * s = "yh??tx"
 * Sample Output
 * -1
 * Explanation
 * It is not possible to replace '?'s in such a way that the string can be rearranged to form a palindrome.
 */
class SmallestPalindromeWorking {
    public static void main(String[] args) {
        String input = "a?rt???";
        System.out.println(getSmallestPalindrome(input));

        String input1 = "ai??a?u";
        System.out.println(getSmallestPalindrome(input1));// aaiuiaa


        String input2 = "ai?а??u";
        System.out.println(getSmallestPalindrome(input2));//aaiuiaa

        String input3 = "ai??a?u";
        System.out.println(getSmallestPalindrome(input3));
    }


    public static String getSmallestPalindrome2(String s) {
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '?') {
                charCount[s.charAt(i) - 'a']++;
            }
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount > 1) return "-1";

        char[] result = new char[s.length()];
        int start = 0, end = s.length() - 1;
        char oddChar = 0;

        for (int i = 0; i < 26; i++) {
            char c = (char) (i + 'a');
            while (charCount[i] >= 2) {
                result[start++] = c;
                result[end--] = c;
                charCount[i] -= 2;
            }
            if (charCount[i] == 1) oddChar = c;
        }

        if (oddChar != 0) {
            result[start] = oddChar;
        }

        return new String(result);
    }

    //WORKING
    public static String getSmallestPalindrome3(String s) {
        char[] str = s.toCharArray();
        int left = 0, right = s.length() - 1;

        while (left <= right) {
            if (str[left] == '?' && str[right] == '?') {
                str[left] = str[right] = 'a';
            } else if (str[left] == '?') {
                str[left] = str[right];
            } else if (str[right] == '?') {
                str[right] = str[left];
            } else if (str[left] != str[right]) {
                return "-1";
            }
            left++;
            right--;
        }

        return new String(str);
    }

    public static String getSmallestPalindrome4(String s) {
        char[] str = s.toCharArray();
        int changes = 0;

        // First pass: make the string a palindrome
        for (int i = 0; i < str.length / 2; i++) {
            if (str[i] != str[str.length - i - 1]) {
                if (str[i] == '?' || str[str.length - i - 1] == '?') {
                    changes++;
                    if (str[i] == '?') {
                        str[i] = str[str.length - i - 1];
                    } else {
                        str[str.length - i - 1] = str[i];
                    }
                } else {
                    return "-1";
                }
            }
        }

        // Second pass: if there are no changes, make it the lexicographically smallest
        if (changes == 0) {
            for (int i = 0; i < str.length / 2; i++) {
                if (str[i] > str[str.length - i - 1]) {
                    str[i] = str[str.length - i - 1] = (char) Math.min(str[i], str[str.length - i - 1]);
                }
            }
        }

        // Third pass: replace any remaining '?' with 'a'
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '?') {
                if (i < str.length / 2) {
                    str[i] = str[str.length - i - 1] = 'a';
                } else if (i == str.length / 2) {
                    str[i] = 'a';
                }
            }
        }

        return new String(str);
    }
    public static String getSmallestPalindrome5(String s) {
        char[] arr = s.toCharArray();
        int questionMarks = 0; // To count the number of question marks

        // First pass to count '?' and make it palindrome
        for (int i = 0; i < arr.length / 2; i++) {
            int j = arr.length - 1 - i;
            if (arr[i] == '?' && arr[j] == '?') {
                questionMarks += 2; // Count both '?' characters
            } else if (arr[i] == '?') {
                arr[i] = arr[j]; // Make the string palindrome
            } else if (arr[j] == '?') {
                arr[j] = arr[i]; // Make the string palindrome
            } else if (arr[i] != arr[j]) {
                return "-1"; // Cannot form a palindrome
            }
        }

        if (questionMarks == 0) {
            return new String(arr); // No '?' found, already a palindrome
        }

        // Second pass to replace remaining '?' with the lexicographically smallest valid character
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] == '?') {
                arr[i] = arr[arr.length - 1 - i] = 'a'; // Replace '?' with 'a'
            }
        }
        if (arr.length % 2 != 0 && arr[arr.length / 2] == '?') {
            arr[arr.length / 2] = 'a'; // Handle the middle character for odd length strings
        }

        return new String(arr); // Return the smallest lexicographic palindrome
    }
    public static String getSmallestPalindrome6(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        // First pass: Replace '?' with the mirrored character or 'a'
        while (left <= right) {
            if (chars[left] == '?' && chars[right] == '?') {
                // Both are '?', replace with 'a'
                chars[left] = chars[right] = 'a';
            } else if (chars[left] == '?') {
                // Only left is '?', replace with right character
                chars[left] = chars[right];
            } else if (chars[right] == '?') {
                // Only right is '?', replace with left character
                chars[right] = chars[left];
            } else if (chars[left] != chars[right]) {
                // Cannot form a palindrome
                return "-1";
            }
            left++;
            right--;
        }

        // If we've reached this point, we've successfully formed a palindrome
        return new String(chars);
    }

    public static String getSmallestPalindrome7(String s) {
        char[] arr = s.toCharArray();
        int questionMarks = 0; // To count the number of question marks

        // First pass to count '?' and make it palindrome
        for (int i = 0; i < arr.length / 2; i++) {
            int j = arr.length - 1 - i;
            if (arr[i] == '?' && arr[j] == '?') {
                questionMarks += 2; // Count both '?' characters
            } else if (arr[i] == '?') {
                arr[i] = arr[j]; // Make the string palindrome
            } else if (arr[j] == '?') {
                arr[j] = arr[i]; // Make the string palindrome
            } else if (arr[i] != arr[j]) {
                return "-1"; // Cannot form a palindrome
            }
        }

        if (questionMarks == 0) {
            return new String(arr); // No '?' found, already a palindrome
        }

        // Second pass to replace remaining '?' with the lexicographically smallest valid character
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] == '?') {
                arr[i] = arr[arr.length - 1 - i] = 'a'; // Replace '?' with 'a'
            }
        }
        if (arr.length % 2 != 0 && arr[arr.length / 2] == '?') {
            arr[arr.length / 2] = 'a'; // Handle the middle character for odd length strings
        }

        return new String(arr); // Return the smallest lexicographic palindrome
    }

    public static String getSmallestPalindrome8(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;

        // Initialize two pointers for the left and right ends of the string
        int left = 0;
        int right = n - 1;

        while (left < right) {
            // If both characters are '?', replace them with 'a'
            if (arr[left] == '?' && arr[right] == '?') {
                arr[left] = 'a';
                arr[right] = 'a';
            } else if (arr[left] == '?') {
                // If only the left character is '?', replace it with the right character
                arr[left] = arr[right];
            } else if (arr[right] == '?') {
                // If only the right character is '?', replace it with the left character
                arr[right] = arr[left];
            } else if (arr[left] != arr[right]) {
                // If both characters are different, it's not possible to form a palindrome
                return "-1";
            }

            // Move the pointers towards the center
            left++;
            right--;
        }

        // Convert the char array back to a string
        return new String(arr);
    }

    public static String getSmallestPalindrome9(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] indices = new int[n];
        int count = 0;

        // Find the indices of '?' characters
        for (int i = 0; i < n; i++) {
            if (chars[i] == '?') {
                indices[count++] = i;
            }
        }

        // Try to form a palindrome by replacing '?' with 'a'
        return tryPalindrome(chars, indices, count, 0, new char[]{'a'});
    }

    private static String tryPalindrome(char[] chars, int[] indices, int count, int start, char[] replacement) {
        if (start == count) {
            // Replace '?' with the current replacement
            for (int i = 0; i < count; i++) {
                chars[indices[i]] = replacement[0];
            }

            // Check if the string is a palindrome
            if (isPalindrome(chars)) {
                return new String(chars);
            }

            return "-1";
        }

        // Try replacing the current '?' with 'a'
        replacement[0] = 'a';
        String result = tryPalindrome(chars, indices, count, start + 1, replacement);
        if (!result.equals("-1")) {
            return result;
        }

        // If 'a' doesn't work, try 'b', 'c', ..., 'z'
        for (char c = 'b'; c <= 'z'; c++) {
            replacement[0] = c;
            result = tryPalindrome(chars, indices, count, start + 1, replacement);
            if (!result.equals("-1")) {
                return result;
            }
        }

        return "-1";
    }

    private static boolean isPalindrome(char[] chars) {
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static String getSmallestPalindrome(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // Iterate from both ends, filling '?' with characters that make it a palindrome
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (chars[i] == '?' && chars[j] != '?') {
                chars[i] = chars[j];
            } else if (chars[i] != '?' && chars[j] == '?') {
                chars[j] = chars[i];
            } else if (chars[i] != chars[j]) {
                return "-1"; // Not possible to form a palindrome
            }
        }

        // Check if any '?' remains unfilled (no valid palindrome)
        for (char c : chars) {
            if (c == '?') {
                return "-1";
            }
        }

        // Convert char array back to string
        return new String(chars);
    }

}