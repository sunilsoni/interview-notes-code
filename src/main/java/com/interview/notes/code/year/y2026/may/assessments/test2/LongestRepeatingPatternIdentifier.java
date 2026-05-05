package com.interview.notes.code.year.y2026.may.assessments.test2;

public class LongestRepeatingPatternIdentifier {
    public static String SearchingChallenge(String str) {
        str = str != null ? str.trim() : "";
        var max = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 2; j <= str.length(); j++) {
                var sub = str.substring(i, j);
                if (str.indexOf(sub, i + sub.length()) != -1 && sub.length() > max.length()) {
                    max = sub;
                }
            }
        }
        return max.isEmpty() ? "no null" : "yes " + max;
    }

    public static void main(String[] args) {
        String[][] tests = {
                {"da2kr32a2", "yes a2"},
                {"sskfssbbb9bbb", "yes bbb"},
                {"aabecaa", "yes aa"},
                {"abbbaac", "no null"},
                {"aabejiabkfabed", "yes abe"},
                {"123224", "no null"},
                {"aa2bbbaacbbb", "yes bbb"},
                {"abcdefghijklmnopqrst", "no null"},
                {"01234567890123456789", "yes 0123456789"}
        };
        for (var t : tests) {
            var result = SearchingChallenge(t[0]);
            System.out.println(result.equals(t[1]) ? "PASS: " + t[0] : "FAIL: " + t[0] + " -> Expected: '" + t[1] + "' Got: '" + result + "'");
        }
    }
}