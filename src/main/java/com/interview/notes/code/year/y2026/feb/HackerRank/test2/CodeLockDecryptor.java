package com.interview.notes.code.year.y2026.feb.HackerRank.test2;

import java.util.ArrayList;
import java.util.List;

public class CodeLockDecryptor {

    public static int decryptCodeLock(List<Integer> codeSequence, int maxValue) {
        int n = codeSequence.size();
        int maxResult = 0;

        for (int i = 0; i < n; i++) {
            int currentTarget = codeSequence.get(i);
            int baseCost = 0;
            List<Integer> others = new ArrayList<>();
            
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                others.add(codeSequence.get(j));
            }

            int bestValForThisPos = findBestValue(currentTarget, others, maxValue);
            int totalCost = (bestValForThisPos == currentTarget) ? 0 : 1;

            for (int j = 0; j < others.size(); j++) {
                if (gcd(bestValForThisPos, others.get(j)) != 1) {
                    totalCost++;
                }
            }
            
            maxResult = Math.max(maxResult, bestValForThisPos - totalCost);
        }

        return maxResult;
    }

    private static int findBestValue(int original, List<Integer> neighbors, int maxValue) {
        if (neighbors.stream().allMatch(num -> gcd(original, num) == 1)) {
            return original;
        }

        for (int v = maxValue; v >= 1; v--) {
            final int candidate = v;
            if (neighbors.stream().allMatch(num -> gcd(candidate, num) == 1)) {
                return candidate;
            }
            if (v < original && v < maxValue - 100) break; 
        }
        return 1;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            a %= b;
            int temp = a;
            a = b;
            b = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        test(List.of(1, 2, 3), 6, 4);
        test(List.of(2, 4, 6, 8), 8, 6);
        test(List.of(3, 2, 4), 6, 4);
        
        List<Integer> largeData = new ArrayList<>();
        for(int i=0; i<1000; i++) largeData.add(i % 2 == 0 ? 2 : 4);
        test(largeData, 1000000, 999999);
    }

    private static void test(List<Integer> seq, int maxV, int expected) {
        int result = decryptCodeLock(new ArrayList<>(seq), maxV);
        System.out.println(result == expected ? "PASS" : "FAIL (Expected " + expected + " but got " + result + ")");
    }
}