package com.interview.notes.code.year.y2026.jan.assessments.imocha.test3;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VirusSolver {

    public static int virusResolving(int N, int X, int Y, int[] damages) {
        if (X >= 2 * Y) return N * Y;

        Map<Integer, Long> counts = Arrays.stream(damages)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        long maxFreq = counts.values().stream().mapToLong(v -> v).max().orElse(0);
        
        long pairs, singles;

        if (maxFreq > N / 2) {
            pairs = N - maxFreq;
            singles = 2 * maxFreq - N;
        } else {
            pairs = N / 2;
            singles = N % 2;
        }

        return (int) (pairs * X + singles * Y);
    }

    public static void main(String[] args) {
        // Screenshot Case 1
        int[] d1 = {83, 86, 77, 15, 93, 35, 86, 92, 49, 21, 86, 77, 15, 93, 35};
        runTest("Screenshot 1", 15, 4, 5, d1, 33);

        // Screenshot Case 2
        int[] d2 = {2, 2, 3, 4, 1, 3};
        runTest("Screenshot 2", 6, 1, 3, d2, 3);

        // Screenshot Case 3
        int[] d3 = {64, 25, 1, 87, 29, 41, 16, 70, 82, 99, 29, 41, 16, 70, 1, 1, 1};
        runTest("Screenshot 3", 17, 6, 10, d3, 58);

        // Original Sample
        runTest("Sample", 5, 2, 3, new int[]{1, 3, 1, 4, 3}, 7);

        // Large Data
        int largeN = 50000;
        int[] largeD = new int[largeN];
        Arrays.fill(largeD, 0, 40000, 1); // Majority element case
        Arrays.fill(largeD, 40000, 50000, 2);
        // MaxFreq=40000. Pairs = 50000-40000 = 10000. Singles = 30000.
        // Cost = 10000*2 + 30000*3 = 20000 + 90000 = 110000
        runTest("Large Majority", largeN, 2, 3, largeD, 110000);
    }

    private static void runTest(String name, int N, int X, int Y, int[] dmg, int exp) {
        int act = virusResolving(N, X, Y, dmg);
        System.out.printf("[%s] %s | Exp: %d | Act: %d%n", 
            act == exp ? "PASS" : "FAIL", name, exp, act);
    }
}