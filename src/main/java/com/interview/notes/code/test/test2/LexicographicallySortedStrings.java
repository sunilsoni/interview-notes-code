package com.interview.notes.code.test.test2;

public class LexicographicallySortedStrings {

    public static void main(String[] args) {
        int n1 = 1;
        int n2 = 2;
        int n3 = 3;
        int n33 = 33;

        System.out.println("Number of sorted strings of length " + n1 + ": " + countVowelStrings(n1));
        System.out.println("Number of sorted strings of length " + n2 + ": " + countVowelStrings(n2));
        System.out.println("Number of sorted strings of length " + n3 + ": " + countVowelStrings(n3));
        System.out.println("Number of sorted strings of length " + n33 + ": " + countVowelStrings(n33));
    }

    public static int countVowelStrings(int n) {
        int[][] memo = new int[n + 1][6]; // Memoization table with n + 1 rows and 6 columns
        return countVowelStringsHelper(n, 1, memo);
    }

    private static int countVowelStringsHelper(int n, int vowel, int[][] memo) {
        if (n == 0) {
            return 1;
        }

        if (vowel > 5) {
            return 0;
        }

        if (memo[n][vowel] != 0) {
            return memo[n][vowel];
        }

        int count = countVowelStringsHelper(n - 1, vowel, memo) + countVowelStringsHelper(n, vowel + 1, memo);
        memo[n][vowel] = count;
        return count;
    }
}
