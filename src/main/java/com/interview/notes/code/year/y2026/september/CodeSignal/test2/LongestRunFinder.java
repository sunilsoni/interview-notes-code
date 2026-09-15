package com.interview.notes.code.year.y2026.september.CodeSignal.test2;

public class LongestRunFinder {

    static String solution(String source) {
        int best = 0, count = 0;
        char result = source.charAt(0);

        for (int i = 0; i < source.length(); i++) {
            count = i > 0 && source.charAt(i) == source.charAt(i - 1) ? count + 1 : 1;

            if (count >= best) {
                best = count;
                result = source.charAt(i);
            }
        }

        return result + String.valueOf(best);
    }

    static void test(String source, String expected) {
        System.out.println(solution(source).equals(expected) ? "PASS" : "FAIL");
    }

    static void main(String[] args) {
        test("bbacccdbbab", "c3");
        test("bbaacaa", "a2");
        test("a", "a1");
        test("aaaa", "a4");
        test("abcd", "d1");
        test("aabb", "b2");
        test("aaabb", "a3");
        test("aabbb", "b3");
        test("aaabbb", "b3");
        test("abababa", "a1");
        test("zzzyyyxxx", "x3");
        test("a".repeat(1_000_000), "a1000000");
    }
}