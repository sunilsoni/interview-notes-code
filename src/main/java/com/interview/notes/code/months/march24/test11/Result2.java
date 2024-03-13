package com.interview.notes.code.months.march24.test11;

class Result2 {

    public static String getSmallestPalindrome(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            if (c != '?') {
                charCount[c - 'a']++;
            }
        }

        int oddCount = 0;
        for (int count : charCount) {
            if (count % 2 != 0) {
                oddCount++;
            }
        }

        if (oddCount > 1) {
            return "-1";
        }

        char[] result = new char[s.length()];
        int start = 0;
        int end = s.length() - 1;
        char middleChar = 0;
        while (start <= end) {
            char cStart = s.charAt(start);
            char cEnd = s.charAt(end);

            if (cStart != '?' && cEnd != '?') {
                if (cStart != cEnd) {
                    return "-1";
                }
                result[start] = cStart;
                result[end] = cEnd;
            } else if (cStart == '?' && cEnd == '?') {
                if (oddCount == 1) {
                    for (int i = 0; i < 26; i++) {
                        if (charCount[i] % 2 != 0) {
                            middleChar = (char) (i + 'a');
                            charCount[i]--;
                            break;
                        }
                    }
                }
                if (start == end && oddCount == 1) {
                    result[start] = middleChar;
                } else {
                    result[start] = 'a';
                    result[end] = 'a';
                }
            } else {
                char toFill = cStart == '?' ? cEnd : cStart;
                result[start] = toFill;
                result[end] = toFill;
                charCount[toFill - 'a'] -= 2;
            }

            start++;
            end--;
        }
        return new String(result);
    }

    public static void main(String[] args) {
        System.out.println(getSmallestPalindrome("a?x??b"));//
        System.out.println(getSmallestPalindrome("a?rt???"));//aartraa
        System.out.println(getSmallestPalindrome("y?h??tx"));
    }
}
