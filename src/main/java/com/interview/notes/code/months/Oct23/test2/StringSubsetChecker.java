package com.interview.notes.code.months.Oct23.test2;

public class StringSubsetChecker {

    public static void main(String[] args) {
        String A1 = "abcd";
        String B1 = "abcde";
        System.out.println(isSubset(A1, B1));  // true

        String A2 = "abcd";
        String B2 = "edcba";
        System.out.println(isSubset(A2, B2));  // true
    }

    /**
     * Checks if A is a subset of B (i.e., if all characters in A are present in B)
     *
     * @param A the first string
     * @param B the second string
     * @return true if A is a subset of B, false otherwise
     */
    public static boolean isSubset(String A, String B) {
        for (char c : A.toCharArray()) {
            if (B.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
