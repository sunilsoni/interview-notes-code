package com.interview.notes.code.months.march24.test11;

class Result3 {

    public static String getSmallestPalindrome(String s) {
        char[] palindrome = s.toCharArray();
        int left = 0, right = palindrome.length - 1;
        int[] charFreq = new int[26];
        int questionMarks = 0;

        for (int i = 0; i <= right; i++) {
            char ch = palindrome[i];
            if (ch == '?') {
                questionMarks++;
            } else {
                charFreq[ch - 'a']++;
            }
        }

        while (left < right) {
            if (palindrome[left] == '?' && palindrome[right] == '?') {
                questionMarks -= 2;
            } else if (palindrome[left] == '?') {
                palindrome[left] = palindrome[right];
                charFreq[palindrome[right] - 'a']++;
            } else if (palindrome[right] == '?') {
                palindrome[right] = palindrome[left];
                charFreq[palindrome[left] - 'a']++;
            } else if (palindrome[left] != palindrome[right]) {
                return "-1";
            }
            left++;
            right--;
        }

        int oddCount = 0;
        for (int freq : charFreq) {
            oddCount += freq % 2;
        }

        if (oddCount > 1 - (questionMarks % 2)) {
            return "-1";
        }

        left = 0;
        right = palindrome.length - 1;

        while (left <= right) {
            if (palindrome[left] == '?') {
                if (left == right) {
                    palindrome[left] = 'a';
                } else {
                    int ch = 0;
                    while (charFreq[ch] % 2 != 0 || charFreq[ch] == 0) {
                        ch++;
                    }
                    palindrome[left] = (char) (ch + 'a');
                    palindrome[right] = (char) (ch + 'a');
                    charFreq[ch] += 2;
                }
            }
            left++;
            right--;
        }

        return new String(palindrome);
    }

    public static void main(String[] args) {
        System.out.println(getSmallestPalindrome("a?rt???"));
        System.out.println(getSmallestPalindrome("a?x??b"));
        System.out.println(getSmallestPalindrome("y?h??tx"));
        System.out.println(getSmallestPalindrome("a?rt???"));//=>aartraa
        System.out.println(getSmallestPalindrome("yh??tx"));//=>-1
        System.out.println(getSmallestPalindrome("ai?a??u"));//=>Output is: -1: Expected: aaiuiaa
    }
}
