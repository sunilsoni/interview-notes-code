package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SameSubstringSolution {

    public static int sameSubstring(String s, String t, int K) {
        int n = s.length();
        int left = 0, sum = 0, best = 0;
        for (int right = 0; right < n; right++) {
            sum += Math.abs(s.charAt(right) - t.charAt(right));
            while (sum > K) {
                sum -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    private static String repeat(char c, int n) {
        char[] a = new char[n];
        Arrays.fill(a, c);
        return new String(a);
    }

    private static boolean run(String s, String t, int k, int exp) {
        int got = sameSubstring(s, t, k);
        System.out.println(got == exp ? "PASS" : "FAIL");
        return got == exp;
    }

    public static void main(String[] args) {
        run("uaccd", "gbbeg", 4, 3);
        run("hffk", "larb", 3, 0);
        run("adpgki", "cdmqki", 6, 3);
        run("abcd", "abxy", 0, 2);
        run("abcde", "bcdef", 3, 3);

        int n = 200000;
        String sLarge = repeat('a', n);
        String tLarge = repeat('a', n);
        boolean largeOk1 = sameSubstring(sLarge, tLarge, 0) == n;
        System.out.println("Large input test passed: " + largeOk1);

        char[] alt = new char[n];
        for (int i = 0; i < n; i++) alt[i] = (i & 1) == 0 ? 'a' : 'b';
        String tAlt = new String(alt);
        int kAlt = IntStream.range(0, n).map(i -> Math.abs(sLarge.charAt(i) - tAlt.charAt(i))).sum();
        boolean largeOk2 = sameSubstring(sLarge, tAlt, kAlt) == n;
        System.out.println("Large input test passed: " + largeOk2);
    }
}