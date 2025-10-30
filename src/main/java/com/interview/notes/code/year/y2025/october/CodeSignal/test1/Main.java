package com.interview.notes.code.year.y2025.october.CodeSignal.test1;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

public class Main {
    static int solution(String text) {
        if (text == null || text.length() < 3) return 0;
        String s = text.toLowerCase(Locale.ROOT);
        return (int) IntStream.range(0, s.length() - 2)
                .filter(i -> s.charAt(i) == s.charAt(i + 2))
                .count();
    }

    static void runTest(String name, String input, int expected) {
        int got = solution(input);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL")
                + " expected=" + expected + " got=" + got
                + " len=" + (input == null ? 0 : input.length()));
    }

    public static void main(String[] args) {
        runTest("T1_basic", "aXA", 1);
        runTest("T2_empty", "", 0);
        runTest("T3_mixed", "abCXccc", 2);
        runTest("T4_singleTriplet", "aaa", 1);
        runTest("T5_case", "AbA", 1);
        runTest("T6_none", "ABC", 0);
        runTest("T7_overlap", "aaaa", 2);
        runTest("T8_len2", "ab", 0);
        runTest("T9_midMatch", "abcba", 1);

        int L = 200000;
        char[] arr = new char[L];
        Arrays.fill(arr, 'a');
        String large = new String(arr);
        runTest("T10_large_all_same", large, L - 2);

        StringBuilder sb = new StringBuilder(1000);
        for (int i = 0; i < 333; i++) sb.append("abA");
        String s = sb.toString();
        runTest("T11_pattern_aba", s, s.length() - 2);
    }
}
