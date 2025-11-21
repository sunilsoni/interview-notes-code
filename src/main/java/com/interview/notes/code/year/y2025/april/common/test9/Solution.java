package com.interview.notes.code.year.y2025.april.common.test9;

import java.util.*;
import java.util.stream.IntStream;

public class Solution {

    public static String calculateScore(String text, String prefixString, String suffixString) {
        int n = text.length();
        return IntStream.range(0, n)
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, n + 1)
                        .mapToObj(j -> text.substring(i, j)))
                .map(sub -> new AbstractMap.SimpleEntry<>(
                        sub, computeScore(sub, prefixString, suffixString)))
                .max(Comparator
                        .comparing(Map.Entry<String, Integer>::getValue)
                        .thenComparing(Map.Entry::getKey,
                                Comparator.reverseOrder()))
                .get()
                .getKey();
    }

    private static int computeScore(String sub, String prefixString, String suffixString) {
        int ps = 0;
        for (int l = 1; l <= sub.length() && l <= prefixString.length(); l++) {
            if (prefixString.endsWith(sub.substring(0, l))) ps = l;
        }
        int ss = 0;
        for (int l = 1; l <= sub.length() && l <= suffixString.length(); l++) {
            if (suffixString.startsWith(sub.substring(sub.length() - l))) ss = l;
        }
        return ps + ss;
    }

    public static void main(String[] args) {
        class Test {
            String t, p, s, desc;
            String exp;
        }
        List<Test> tests = Arrays.asList(
                new Test() {{
                    t = "nothing";
                    p = "bruno";
                    s = "ingenious";
                    exp = "nothing";
                    desc = "Sample0";
                }},
                new Test() {{
                    t = "ab";
                    p = "b";
                    s = "a";
                    exp = "a";
                    desc = "Sample1";
                }},
                new Test() {{
                    t = "engine";
                    p = "raven";
                    s = "ginkgo";
                    exp = "engin";
                    desc = "Example";
                }},
                new Test() {{
                    t = "banana";
                    p = "bana";
                    s = "nana";
                    exp = "banana";
                    desc = "Overlap";
                }},
                new Test() {{
                    t = "abracadabra";
                    p = "habrahabr";
                    s = "bracket";
                    exp = "abrac";
                    desc = "Custom";
                }}
        );
        for (Test tc : tests) {
            String got = calculateScore(tc.t, tc.p, tc.s);
            System.out.println((got.equals(tc.exp) ? "PASS" : "FAIL")
                    + ": " + tc.desc
                    + " expected=" + tc.exp
                    + " got=" + got);
        }
    }
}