package com.interview.notes.code.year.y2026.feb.HackerRank.test2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CoprimeLockOptimizer {

    public static int decryptCodeLock(List<Integer> codeSequence, int maxValue) {

        int n = codeSequence.size();
        int ans = 0;

        for (int x = 1; x <= maxValue; x++) {

            int cost = 0;
            boolean ok = true;

            for (int v : codeSequence) {
                if (v != x) cost++;
                if (gcd(x, v) != 1 && v != x) {
                    if (gcd(x, v) != 1) {
                        ok = false;
                        break;
                    }
                }
            }

            if (ok) ans = Math.max(ans, x - cost);
        }

        return ans;
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    static void test(List<Integer> arr, int max, int exp) {
        int r = decryptCodeLock(arr, max);
        System.out.println(r == exp ? "PASS" : "FAIL -> " + r + " expected " + exp);
    }

    public static void main(String[] args) {

        test(Arrays.asList(1, 2, 3), 6, 4);
        test(Arrays.asList(2, 4, 6, 8), 8, 6);
        test(Arrays.asList(3, 2, 4), 6, 4);

        List<Integer> large = IntStream.rangeClosed(1, 1000).boxed().collect(Collectors.toList());
        test(large, 1000, decryptCodeLock(large, 1000));
    }
}
