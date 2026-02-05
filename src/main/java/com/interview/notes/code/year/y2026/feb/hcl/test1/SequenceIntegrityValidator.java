package com.interview.notes.code.year.y2026.feb.hcl.test1;

public class SequenceIntegrityValidator {

    public static void main(String[] args) {
        verify(isSubsequence("abc", "ahbgdc"), true, "Standard Case");
        verify(isSubsequence("axc", "ahbgdc"), false, "Negative Case");
        verify(isSubsequence("", "ahbgdc"), true, "Empty Source");
        verify(isSubsequence("abc", ""), false, "Empty Target");

        String largeT = "a".repeat(10_000_000) + "b";
        verify(isSubsequence("ab", largeT), true, "Large Data Input");
    }

    public static boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;

        int[] index = {0};
        t.chars().filter(c -> index[0] < s.length() && c == s.charAt(index[0]))
                .forEach(c -> index[0]++);

        return index[0] == s.length();
    }

    private static void verify(boolean actual, boolean expected, String scenario) {
        String status = (actual == expected) ? "PASS" : "FAIL";
        System.out.printf("Scenario: %-20s | Status: %s%n", scenario, status);
    }
}