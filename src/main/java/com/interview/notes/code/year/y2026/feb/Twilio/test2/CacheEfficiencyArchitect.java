package com.interview.notes.code.year.y2026.feb.Twilio.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CacheEfficiencyArchitect {

    public static long getMinimumSize(List<Integer> payloadSize, List<Integer> cacheA, List<Integer> cacheB, int minThreshold) {
        List<Long> both = new ArrayList<>();
        List<Long> onlyA = new ArrayList<>();
        List<Long> onlyB = new ArrayList<>();

        for (int i = 0; i < payloadSize.size(); i++) {
            long s = payloadSize.get(i);
            int a = cacheA.get(i), b = cacheB.get(i);
            if (a == 1 && b == 1) both.add(s);
            else if (a == 1) onlyA.add(s);
            else if (b == 1) onlyB.add(s);
        }

        Collections.sort(both);
        Collections.sort(onlyA);
        Collections.sort(onlyB);

        long[] prefBoth = computePrefixSum(both);
        long[] prefA = computePrefixSum(onlyA);
        long[] prefB = computePrefixSum(onlyB);

        long minTotal = Long.MAX_VALUE;

        for (int k = 0; k <= Math.min(minThreshold, both.size()); k++) {
            int needed = minThreshold - k;
            if (needed <= onlyA.size() && needed <= onlyB.size()) {
                long current = prefBoth[k] + prefA[needed] + prefB[needed];
                minTotal = Math.min(minTotal, current);
            }
        }

        return minTotal == Long.MAX_VALUE ? -1 : minTotal;
    }

    private static long[] computePrefixSum(List<Long> list) {
        long[] res = new long[list.size() + 1];
        for (int i = 0; i < list.size(); i++) res[i + 1] = res[i] + list.get(i);
        return res;
    }

    public static void main(String[] args) {
        run(1, List.of(10, 8, 12, 4, 5, 25), List.of(1, 0, 1, 1, 0, 1), List.of(1, 0, 1, 0, 1, 1), 3, 31);
        run(2, List.of(3, 2, 4, 1, 5), List.of(0, 0, 0, 0, 1), List.of(1, 1, 0, 1, 1), 2, -1);

        int largeN = 200000;
        List<Integer> lp = new ArrayList<>(Collections.nCopies(largeN, 1000));
        List<Integer> la = new ArrayList<>(Collections.nCopies(largeN, 1));
        List<Integer> lb = new ArrayList<>(Collections.nCopies(largeN, 1));
        run(3, lp, la, lb, 100000, 100000000L);
    }

    private static void run(int id, List<Integer> p, List<Integer> a, List<Integer> b, int t, long exp) {
        long res = getMinimumSize(p, a, b, t);
        System.out.println("Case " + id + ": " + (res == exp ? "PASS" : "FAIL"));
    }
}