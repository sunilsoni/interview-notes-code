package com.interview.notes.code.year.y2025.october.Amazon.test3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static int moveUnits(List<Integer> population, String unit) {
        int n = population.size();
        int[] p = new int[n + 1];
        for (int i = 1; i <= n; i++) p[i] = population.get(i - 1);
        int neg = Integer.MIN_VALUE / 4;
        int prevEmpty = 0;
        int prevOcc = neg;
        for (int i = 1; i <= n; i++) {
            int curEmpty = Math.max(prevEmpty, prevOcc);
            int curOcc = neg;
            if (unit.charAt(i - 1) == '1') {
                curOcc = Math.max(curOcc, Math.max(prevEmpty, prevOcc) + p[i]);
                if (i > 1) curEmpty = Math.max(curEmpty, prevEmpty + p[i - 1]);
            }
            prevEmpty = curEmpty;
            prevOcc = curOcc;
        }
        return Math.max(prevEmpty, prevOcc);
    }

    private static int bruteForce(List<Integer> population, String unit) {
        int n = population.size();
        List<Integer> ones = IntStream.range(0, n).filter(i -> unit.charAt(i) == '1').boxed().collect(Collectors.toList());
        int m = ones.size();
        int best = 0;
        int totalComb = 1 << m;
        for (int mask = 0; mask < totalComb; mask++) {
            boolean[] occ = new boolean[n];
            for (int k = 0; k < m; k++) {
                int i = ones.get(k);
                boolean moveLeft = ((mask >> k) & 1) == 1 && i > 0;
                int pos = moveLeft ? i - 1 : i;
                occ[pos] = true;
            }
            int sum = 0;
            for (int i = 0; i < n; i++) if (occ[i]) sum += population.get(i);
            best = Math.max(best, sum);
        }
        return best;
    }

    private static void runTest(String name, List<Integer> pop, String unit, int expected) {
        long start = System.nanoTime();
        int out = moveUnits(pop, unit);
        long dur = (System.nanoTime() - start) / 1_000_000;
        String res = out == expected ? "PASS" : "FAIL";
        System.out.println("Test=" + name + " | Output=" + out + " | Expected=" + expected + " | TimeMs=" + dur + " | Result=" + res);
    }

    private static void runBruteCheck(String name, List<Integer> pop, String unit) {
        int expected = bruteForce(pop, unit);
        runTest(name, pop, unit, expected);
    }

    public static void main(String[] args) {
        System.out.println("Running Test Cases...");
        runTest("Sample0", Arrays.asList(20, 10, 9, 30, 20, 19), "011011", 80);
        runTest("Sample1", Arrays.asList(5, 4, 5, 1), "0111", 14);
        runTest("Example", Arrays.asList(10, 5, 8, 9, 6), "01101", 27);

        runBruteCheck("Edge_Single1", Collections.singletonList(7), "1");
        runBruteCheck("Edge_Single0", Collections.singletonList(7), "0");
        runBruteCheck("Edge_AllZero", Arrays.asList(3, 2, 5, 6), "0000");
        runBruteCheck("Edge_AllOne", Arrays.asList(3, 2, 5, 6), "1111");
        runBruteCheck("Edge_Alternating", Arrays.asList(5, 1, 4, 2, 7, 3), "101010");
        runBruteCheck("Edge_BlockOnes", Arrays.asList(9, 1, 8, 2, 7, 3), "011110");

        Random rnd = new Random(42);
        int n = 100000;
        List<Integer> pop = IntStream.range(0, n).map(i -> 1 + rnd.nextInt(10000)).boxed().collect(Collectors.toList());
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(rnd.nextBoolean() ? '1' : '0');
        String unit = sb.toString();
        long t1 = System.nanoTime();
        int result = moveUnits(pop, unit);
        long t2 = System.nanoTime();
        System.out.println("Large data test | N=" + n + " | Output=" + result + " | TimeMs=" + ((t2 - t1) / 1_000_000) + " | Result=COMPLETED");
    }
}
