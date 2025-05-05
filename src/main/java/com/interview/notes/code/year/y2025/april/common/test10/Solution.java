package com.interview.notes.code.year.y2025.april.common.test10;

import java.util.ArrayList;
import java.util.List;
//WORKING 100%

public class Solution {

    public static String calculateScore(String text, String prefixString, String suffixString) {
        String bestSub = "";
        int bestScore = Integer.MIN_VALUE;

        int n = text.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = text.substring(i, j);
                int ps = 0;
                for (int l = 1; l <= sub.length() && l <= prefixString.length(); l++) {
                    if (prefixString.endsWith(sub.substring(0, l))) {
                        ps = l;
                    }
                }
                int ss = 0;
                for (int l = 1; l <= sub.length() && l <= suffixString.length(); l++) {
                    if (suffixString.startsWith(sub.substring(sub.length() - l))) {
                        ss = l;
                    }
                }
                int score = ps + ss;
                if (score > bestScore ||
                        (score == bestScore && sub.compareTo(bestSub) < 0)) {
                    bestScore = score;
                    bestSub = sub;
                }
            }
        }
        return bestSub;
    }

    public static void main(String[] args) {
        class Test {
            String t, p, s, exp, d;
        }
        List<Test> tests = new ArrayList<Test>();
        tests.add(new Test() {{
            t = "nothing";
            p = "bruno";
            s = "ingenious";
            exp = "nothing";
            d = "Sample0";
        }});
        tests.add(new Test() {{
            t = "ab";
            p = "b";
            s = "a";
            exp = "a";
            d = "Sample1";
        }});
        tests.add(new Test() {{
            t = "engine";
            p = "raven";
            s = "ginkgo";
            exp = "engin";
            d = "Example";
        }});
        tests.add(new Test() {{
            t = "banana";
            p = "bana";
            s = "nana";
            exp = "banana";
            d = "Overlap";
        }});
        tests.add(new Test() {{
            t = "abracadabra";
            p = "habrahabr";
            s = "bracket";
            exp = "abrac";
            d = "Custom";
        }});

        for (Test tc : tests) {
            String got = calculateScore(tc.t, tc.p, tc.s);
            System.out.println(
                    (got.equals(tc.exp) ? "PASS" : "FAIL")
                            + ": " + tc.d
                            + " expected=" + tc.exp
                            + " got=" + got
            );
        }
    }
}