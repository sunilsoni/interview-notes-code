package com.interview.notes.code.year.y2025.october.Amazon.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static int minOperationsToUnbias(int n, String data1, String data2) {
        int[] pref = new int[n + 1];
        for (int i = 1; i <= n; i++) pref[i] = pref[i - 1] + (data1.charAt(i - 1) == '1' ? 1 : -1);
        int[] suff = new int[n + 1];
        for (int j = n - 1; j >= 0; j--) suff[j] = suff[j + 1] + (data2.charAt(j) == '1' ? 1 : -1);
        Map<Integer, Integer> bestI = new HashMap<>();
        for (int i = 0; i <= n; i++) bestI.merge(pref[i], i, Math::max);
        int ans = 2 * n;
        for (int j = 0; j <= n; j++) {
            Integer i = bestI.get(-suff[j]);
            if (i != null) ans = Math.min(ans, n - i + j);
        }
        return ans;
    }

    private static int minOperationsToUnbiasBrute(int n, String a, String b) {
        int best = 2 * n;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                int ones = 0, zeros = 0;
                for (int k = 0; k < i; k++) {
                    if (a.charAt(k) == '1') ones++;
                    else zeros++;
                }
                for (int k = j; k < n; k++) {
                    if (b.charAt(k) == '1') ones++;
                    else zeros++;
                }
                if (ones == zeros) best = Math.min(best, n - i + j);
            }
        }
        return best;
    }

    private static String randBits(int n, Random r) {
        return IntStream.range(0, n).mapToObj(i -> r.nextBoolean() ? "1" : "0").collect(Collectors.joining());
    }

    public static void main(String[] args) {
        int n1 = 2;
        String a1 = "01";
        String b1 = "11";
        int e1 = 2;
        System.out.println(minOperationsToUnbias(n1, a1, b1) == e1 ? "PASS" : "FAIL");

        int n2 = 6;
        String a2 = "111001";
        String b2 = "010110";
        int e2 = 6;
        System.out.println(minOperationsToUnbias(n2, a2, b2) == e2 ? "PASS" : "FAIL");

        int n3 = 4;
        String a3 = "0101";
        String b3 = "1010";
        int e3 = 0;
        System.out.println(minOperationsToUnbias(n3, a3, b3) == e3 ? "PASS" : "FAIL");

        int n4 = 3;
        String a4 = "111";
        String b4 = "111";
        int e4 = 6;
        System.out.println(minOperationsToUnbias(n4, a4, b4) == e4 ? "PASS" : "FAIL");

        boolean smallOk = true;
        Random r = new Random(1);
        for (int t = 0; t < 50; t++) {
            int n = 30 + r.nextInt(20);
            String A = randBits(n, r);
            String B = randBits(n, r);
            int fast = minOperationsToUnbias(n, A, B);
            int brute = minOperationsToUnbiasBrute(n, A, B);
            if (fast != brute) {
                smallOk = false;
                break;
            }
        }
        System.out.println(smallOk ? "PASS" : "FAIL");

        int bigN = 100000;
        String bigA = randBits(bigN, r);
        String bigB = randBits(bigN, r);
        int res = minOperationsToUnbias(bigN, bigA, bigB);
        System.out.println("Large input test passed: " + (res >= 0));
    }
}