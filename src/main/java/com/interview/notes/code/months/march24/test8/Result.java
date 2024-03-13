package com.interview.notes.code.months.march24.test8;

class Result {
    public static void main(String[] args) {
        String input = "axxb??";
        System.out.println(getSmallestPalindrome(input));


        String input1 = "a?rt?????";
        System.out.println(getSmallestPalindrome(input1));
    }

    public static String getSmallestPalindrome(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);

        // Iterate from the middle of the string outwards
        for (int i = n / 2 - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);

            // If current character is not '?' or already matches its mirrored position, skip
            if (currentChar != '?' && currentChar == s.charAt(n - 1 - i)) {
                continue;
            }

            // Find the smallest lexicographically valid character to fill the gap
            char fillChar = findSmallestChar(s, i);

            // Update the character in the original string and its mirrored position
            sb.setCharAt(i, fillChar);
            sb.setCharAt(n - 1 - i, fillChar);
        }

        // Check if a palindrome is formed
        return isPalindrome(sb.toString()) ? sb.toString() : "-1";
    }

    private static char findSmallestChar(String s, int i) {
        int n = s.length();
        char currentChar = s.charAt(i);

        // Check for smallest character that's not already present in the mirrored position
        for (char c = 'a'; c <= 'z'; c++) {
            if (c != currentChar && (i == n - 1 - i || s.charAt(n - 1 - i) != c)) {
                return c;
            }
        }

        // No valid character found, return '?'
        return '?';
    }

    private static boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
