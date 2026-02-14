package com.interview.notes.code.year.y2026.feb.Twilio.test1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class CacheOptimizer {

    public static long getMinimumSize(List<Integer> payloadSize, List<Integer> cacheA, List<Integer> cacheB, int minThreshold) {
        List<Long> both = new ArrayList<>();
        List<Long> onlyA = new ArrayList<>();
        List<Long> onlyB = new ArrayList<>();

        for (int i = 0; i < payloadSize.size(); i++) {
            long size = payloadSize.get(i);
            int a = cacheA.get(i);
            int b = cacheB.get(i);
            if (a == 1 && b == 1) both.add(size);
            else if (a == 1) onlyA.add(size);
            else if (b == 1) onlyB.add(size);
        }

        Collections.sort(both);
        Collections.sort(onlyA);
        Collections.sort(onlyB);

        long minTotal = Long.MAX_VALUE;

        for (int k = 0; k <= Math.min(minThreshold, both.size()); k++) {
            int needed = minThreshold - k;
            if (needed <= onlyA.size() && needed <= onlyB.size()) {
                long current = 0;
                current += both.stream().limit(k).mapToLong(Long::longValue).sum();
                current += onlyA.stream().limit(needed).mapToLong(Long::longValue).sum();
                current += onlyB.stream().limit(needed).mapToLong(Long::longValue).sum();
                minTotal = Math.min(minTotal, current);
            }
        }

        return minTotal == Long.MAX_VALUE ? -1 : minTotal;
    }

    public static void main(String[] args) {
        test(1, List.of(10, 8, 12, 4, 5, 25), List.of(1, 0, 1, 1, 0, 1), List.of(1, 0, 1, 0, 1, 1), 3, 31);
        test(2, List.of(3, 2, 4, 1, 5), List.of(0, 0, 0, 0, 1), List.of(1, 1, 0, 1, 1), 2, -1);
        
        List<Integer> largePayload = IntStream.range(0, 200000).map(i -> 1000).boxed().toList();
        List<Integer> largeA = IntStream.range(0, 200000).map(i -> 1).boxed().toList();
        List<Integer> largeB = IntStream.range(0, 200000).map(i -> 1).boxed().toList();
        test(3, largePayload, largeA, largeB, 100000, 100000000L);
    }

    private static void test(int id, List<Integer> p, List<Integer> a, List<Integer> b, int t, long expected) {
        long result = getMinimumSize(p, a, b, t);
        System.out.println("Test " + id + ": " + (result == expected ? "PASS" : "FAIL (Expected " + expected + ", got " + result + ")"));
    }
}