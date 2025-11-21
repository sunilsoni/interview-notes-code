package com.interview.notes.code.year.y2024.march24.test6;

class Result {
    public static String getSmallestPalindrome(String s) {
        char[] arr = s.toCharArray();
        int[] freq = new int[26];
        int n = arr.length;
        int questionMarks = 0;

        // Count the frequency of each character and the number of question marks
        for (char c : arr) {
            if (c == '?') {
                questionMarks++;
            } else {
                freq[c - 'a']++;
            }
        }

        // Check if it's possible to form a palindrome
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
            }
        }

        // If the number of question marks is not enough to fill the odd counts, return -1
        if (questionMarks < oddCount) {
            return "-1";
        }

        // Interpolate the string with characters
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n / 2; i++) {
            if (arr[i] == '?') {
                char c = getSmallestChar(freq);
                sb.append(c);
                sb.append(c);
                freq[c - 'a'] -= 2;
            } else {
                sb.append(arr[i]);
                sb.append(arr[i]);
                freq[arr[i] - 'a'] -= 2;
            }
        }

        // If the length is odd, append the middle character
        if (n % 2 != 0) {
            char c = getSmallestChar(freq);
            sb.append(c);
        }

        // Reverse the first half and append to form the palindrome
        StringBuilder result = new StringBuilder(sb.toString());
        result.reverse();
        result.insert(0, sb);

        return result.toString();
    }

    // Get the lexicographically smallest available character
    private static char getSmallestChar(int[] freq) {
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                freq[i]--;
                return (char) (i + 'a');
            }
        }
        return 'a'; // This won't be reached as we check if it's possible to form a palindrome first
    }

    public static void main(String[] args) {
        String input = "axxb??";
        System.out.println(getSmallestPalindrome(input));


        String input1 = "a?rt?????";
        System.out.println(getSmallestPalindrome(input1));
    }
}