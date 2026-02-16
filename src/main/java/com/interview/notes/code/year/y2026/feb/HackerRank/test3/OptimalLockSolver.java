package com.interview.notes.code.year.y2026.feb.HackerRank.test3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class OptimalLockSolver {

    public static int decryptCodeLock(List<Integer> codeSequence, int maxValue) {
        int n = codeSequence.size();
        int searchLimit = Math.max(1, maxValue - n - 100);
        int maxScore = Integer.MIN_VALUE;

        var candidates = new HashSet<>(codeSequence);
        IntStream.rangeClosed(searchLimit, maxValue).forEach(candidates::add);

        for (int cand : candidates) {
            if (cand <= 0) continue;
            int conflicts = 0;
            for (int num : codeSequence) {
                if (gcd(cand, num) > 1) conflicts++;
            }

            int cost;
            if (codeSequence.contains(cand)) {
                cost = conflicts - 1; 
            } else {
                cost = Math.max(1, conflicts);
            }
            
            maxScore = Math.max(maxScore, cand - cost);
        }
        return maxScore;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        test(List.of(1, 2, 3), 6, 4);
        test(List.of(2, 4, 6, 8), 8, 6);
        
        var large = new ArrayList<Integer>();
        for(int i=0; i<1000; i++) large.add(2);
        test(large, 1000, 998);
    }

    private static void test(List<Integer> list, int max, int expected) {
        long t = System.currentTimeMillis();
        int res = decryptCodeLock(list, max);
        System.out.printf("Exp: %d | Got: %d | %s | %dms%n", expected, res, res==expected?"PASS":"FAIL", System.currentTimeMillis()-t);
    }
}