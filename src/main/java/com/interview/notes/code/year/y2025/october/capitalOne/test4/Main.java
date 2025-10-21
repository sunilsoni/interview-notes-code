package com.interview.notes.code.year.y2025.october.capitalOne.test4;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

    static long solution(String[] titles) {
        String[] a = Arrays.copyOf(titles, titles.length);
        Arrays.sort(a);
        long ans = 0L;
        for (int i = 0; i < a.length; i++) {
            int hi = lowerBound(a, a[i] + "{");
            ans += (hi - i - 1);
        }
        return ans;
    }

    static int lowerBound(String[] a, String key) {
        int lo = 0, hi = a.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid].compareTo(key) < 0) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    static void runTest(int idx, String[] input, long expected) {
        long start = System.nanoTime();
        long out = solution(input);
        long ms = (System.nanoTime() - start) / 1_000_000;
        if (out == expected)
            System.out.println("Test " + idx + ": PASS | Output=" + out + " | Expected=" + expected + " | " + ms + " ms");
        else
            System.out.println("Test " + idx + ": FAIL | Output=" + out + " | Expected=" + expected + " | " + ms + " ms");
    }

    static String[] genRandom(int n, long seed) {
        Random r = new Random(seed);
        return IntStream.range(0, n).mapToObj(i -> {
            int len = 1 + r.nextInt(10);
            char[] c = new char[len];
            for (int j = 0; j < len; j++) c[j] = (char) ('a' + r.nextInt(26));
            return new String(c);
        }).toArray(String[]::new);
    }

    static String[] repeat(String s, int n) {
        return IntStream.range(0, n).mapToObj(i -> s).toArray(String[]::new);
    }

    public static void main(String[] args) {
        int testId = 1;
        runTest(testId++, new String[]{"wall", "wallpaper", "science", "wallet", "philosophy", "phil", "book"}, 3L);
        runTest(testId++, new String[]{"abc", "a", "a", "b", "ab", "ac"}, 8L);
        runTest(testId++, new String[]{"a"}, 0L);
        runTest(testId++, new String[]{"abc", "def", "ghi"}, 0L);
        runTest(testId++, new String[]{"a", "a", "a"}, 3L);
        runTest(testId++, new String[]{"a", "ab", "abc", "abcd"}, 6L);

        int n = 100000;
        String[] allSame = repeat("a", n);
        long expectedAllSame = (long) n * (n - 1) / 2;
        runTest(testId++, allSame, expectedAllSame);

        String[] large = genRandom(100000, 42L);
        long t1 = System.nanoTime();
        long res = solution(large);
        long t2 = System.nanoTime();
        System.out.println("Large data test executed in " + ((t2 - t1) / 1_000_000) + " ms | Result=" + res);
    }
}
