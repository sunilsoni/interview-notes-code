package com.interview.notes.code.year.y2026.may.assessments.test3;

public class RepeatingPatternSearcher {
    public static String SearchingChallenge(String str) {
        var m = "";
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 2; j <= str.length(); j++) {
                var s = str.substring(i, j);
                if (str.indexOf(s, i + s.length()) > -1 && s.length() > m.length()) {
                    m = s;
                }
            }
        }
        return m.isEmpty() ? "no null" : "yes " + m;
    }

    public static void main(String[] args) {
        var tests = new String[][] {
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
            var res = SearchingChallenge(t[0]);
            System.out.println(res.equals(t[1]) ? "PASS: " + t[0] : "FAIL: " + t[0] + " -> Expected: '" + t[1] + "', Got: '" + res + "'");
        }
    }
}