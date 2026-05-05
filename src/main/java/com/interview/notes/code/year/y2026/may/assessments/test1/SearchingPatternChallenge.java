package com.interview.notes.code.year.y2026.may.assessments.test1;

import java.util.stream.IntStream;

public class SearchingPatternChallenge {

    public static String SearchingChallenge(String str) {
        int n = str.length();

        return IntStream.iterate(n / 2, len -> len >= 2, len -> len - 1)
                .mapToObj(len -> IntStream.rangeClosed(0, n - len)
                        .mapToObj(i -> str.substring(i, i + len))
                        .filter(p -> hasRepeat(str, p))
                        .findFirst()
                        .orElse(null))
                .filter(p -> p != null)
                .findFirst()
                .map(p -> "yes " + p)
                .orElse("no null");
    }

    static boolean hasRepeat(String s, String p) {
        int len = p.length();

        return IntStream.rangeClosed(0, s.length() - len)
                .filter(i -> s.startsWith(p, i))
                .anyMatch(i -> IntStream.rangeClosed(i + len, s.length() - len)
                        .anyMatch(j -> s.startsWith(p, j)));
    }

    static void test(String input, String expected) {
        String got = SearchingChallenge(input);
        System.out.println((got.equals(expected) ? "PASS" : "FAIL") +
                " | input=" + input +
                " | got=" + got +
                " | expected=" + expected);
    }

    public static void main(String[] args) {
        test("da2kr32a2", "yes a2");
        test("sskfssbbb9bbb", "yes bbb");
        test("aabejiabkfabed", "yes abe");
        test("123224", "no null");
        test("aabecaa", "yes aa");
        test("abbbbac", "no null");
        test("aa2bbbaacbbb", "yes bbb");
        test("abcdefghijklmnopqrst", "no null");
        test("abcabcabcabcabcabcab", "yes abcabcabc");
    }
}