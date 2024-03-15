package com.interview.notes.code.months.march24.test11;

class Result {

    public static String getSmallestPalindrome1(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        int questionMarks = 0;

        // Count question marks and verify if the given characters can form a palindrome
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                questionMarks++;
            } else if (i < chars.length / 2) {
                if (chars[chars.length - 1 - i] != '?' && chars[i] != chars[chars.length - 1 - i]) {
                    return "-1";
                }
            }
        }

        while (left <= right) {
            if (chars[left] != '?' && chars[right] != '?' && chars[left] != chars[right]) {
                return "-1";
            }
            if (chars[left] == '?' && chars[right] == '?') {
                if (left == right) {
                    chars[left] = 'a';
                } else {
                    chars[left] = 'a';
                    chars[right] = 'a';
                }
            } else if (chars[left] == '?') {
                chars[left] = chars[right];
            } else if (chars[right] == '?') {
                chars[right] = chars[left];
            }
            left++;
            right--;
        }
        return new String(chars);
    }

    public static String getSmallestPalindrome(String s) {
        char[] result = s.toCharArray();
        int left = 0, right = s.length() - 1;

        // Replace '?' from outside towards the center
        while (left < right) {
            if (result[left] == '?' && result[right] == '?') {
                result[left] = result[right] = 'a';
            } else if (result[left] == '?') {
                result[left] = result[right];
            } else if (result[right] == '?') {
                result[right] = result[left];
            }
            left++;
            right--;
        }

        // If there is a single '?' in the middle of an odd-length string, replace it with 'a'
        if (left == right && result[left] == '?') {
            result[left] = 'a';
        }

        String palindrome = new String(result);
        // Check if the string is a palindrome
        if (!isPalindrome(palindrome)) {
            return "-1";
        }

        return palindrome;
    }

    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(getSmallestPalindrome("ai?a??u")); // should output "aaiuiaa"
        System.out.println(getSmallestPalindrome("a?rt???")); // should output "aartraa"
        System.out.println(getSmallestPalindrome("a?x??b")); // should output "abxxba"
        System.out.println(getSmallestPalindrome("y?h??tx")); // should output "-1"
    }
}
