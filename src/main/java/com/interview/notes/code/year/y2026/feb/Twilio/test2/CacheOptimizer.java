package com.interview.notes.code.year.y2026.feb.Twilio.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CacheOptimizer {

    public static long getMinimumSize(List<Integer> payloadSize, List<Integer> cacheA, List<Integer> cacheB, int minThreshold) {
        List<Long> both = new ArrayList<>(), onlyA = new ArrayList<>(), onlyB = new ArrayList<>();
        int n = payloadSize.size();

        for (int i = 0; i < n; i++) {
            long p = payloadSize.get(i);
            int a = cacheA.get(i);
            int b = cacheB.get(i);
            if (a == 1 && b == 1) both.add(p);
            else if (a == 1) onlyA.add(p);
            else if (b == 1) onlyB.add(p);
        }

        if (both.size() + onlyA.size() < minThreshold || both.size() + onlyB.size() < minThreshold) {
            return -1;
        }

        Collections.sort(both);
        Collections.sort(onlyA);
        Collections.sort(onlyB);

        long[] preBoth = getPrefixSum(both);
        long[] preA = getPrefixSum(onlyA);
        long[] preB = getPrefixSum(onlyB);

        long minTotal = Long.MAX_VALUE;
        int minX = Math.max(0, Math.max(minThreshold - onlyA.size(), minThreshold - onlyB.size()));
        int maxX = Math.min(both.size(), minThreshold);

        for (int x = minX; x <= maxX; x++) {
            long currentCost = preBoth[x];
            int needA = minThreshold - x;
            int needB = minThreshold - x;
            currentCost += preA[needA] + preB[needB];
            if (currentCost < minTotal) {
                minTotal = currentCost;
            }
        }

        return minTotal;
    }

    private static long[] getPrefixSum(List<Long> list) {
        long[] pre = new long[list.size() + 1];
        for (int i = 0; i < list.size(); i++) {
            pre[i + 1] = pre[i] + list.get(i);
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println("Running Tests...");

        // Test Case 1: Example 1
        test("Example 1", 
            List.of(10, 8, 12, 4, 5, 25), 
            List.of(1, 0, 1, 1, 0, 1), 
            List.of(1, 0, 1, 0, 1, 1), 
            3, 31L);

        // Test Case 2: Example 2 (Impossible)
        test("Example 2", 
            List.of(3, 2, 4, 1, 5), 
            List.of(0, 0, 0, 0, 1), 
            List.of(1, 1, 0, 1, 1), 
            2, -1L);

        // Test Case 3: Only A and B, no Both
        test("Disjoint Sets", 
            List.of(10, 20, 30, 40), 
            List.of(1, 1, 0, 0), 
            List.of(0, 0, 1, 1), 
            2, 100L); // 10+20 + 30+40

        // Test Case 4: All Both
        test("All Both", 
            List.of(5, 10, 15), 
            List.of(1, 1, 1), 
            List.of(1, 1, 1), 
            2, 15L); // 5+10

        // Test Case 5: Large Data Simulation
        List<Integer> largeP = new ArrayList<>();
        List<Integer> largeA = new ArrayList<>();
        List<Integer> largeB = new ArrayList<>();
        for(int i=0; i<10000; i++) {
            largeP.add(100);
            largeA.add(1);
            largeB.add(1);
        }
        test("Large Input", largeP, largeA, largeB, 5000, 500000L);

        System.out.println("Testing Complete.");
    }

    private static void test(String name, List<Integer> p, List<Integer> a, List<Integer> b, int k, long expected) {
        long start = System.currentTimeMillis();
        long result = getMinimumSize(p, a, b, k);
        long end = System.currentTimeMillis();
        String status = result == expected ? "PASS" : "FAIL";
        System.out.printf("[%s] %s | Expected: %d, Got: %d | Time: %dms%n", status, name, expected, result, (end - start));
    }
}