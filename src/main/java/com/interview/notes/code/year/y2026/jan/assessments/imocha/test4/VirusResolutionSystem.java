package com.interview.notes.code.year.y2026.jan.assessments.imocha.test4;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VirusResolutionSystem {

    public static int virusResolving(int N, int X, int Y, int[] damages) {
        if (X >= 2 * Y) {
            return N * Y;
        }
        long maxFreq = Arrays.stream(damages).boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .max(Long::compare).orElse(0L);

        int pairs = (int) (maxFreq > N / 2.0 ? N - maxFreq : N / 2);
        int singles = N - 2 * pairs;

        return pairs * X + singles * Y;
    }

    public static void main(String[] args) {
        System.out.println("--- Running Sample Test Cases ---");

        runTestCase(1, 5, 2, 3, new int[]{1, 3, 1, 4, 3}, 7);

        runTestCase(2, 15, 4, 5, new int[]{83, 86, 77, 15, 93, 35, 86, 92, 49, 21, 86, 77, 15, 93, 35}, 33);

        runTestCase(3, 17, 6, 10, new int[]{64, 25, 1, 87, 29, 41, 16, 70, 82, 99, 29, 41, 16, 70, 1, 1, 1}, 58);

        runTestCase(4, 6, 1, 3, new int[]{2, 2, 3, 4, 1, 3}, 3);

        System.out.println("\n--- Running Large Data Input Test ---");

        int largeN = 50000;
        int[] largeDamages = new int[largeN];
        Arrays.fill(largeDamages, 100);
        runTestCase(5, largeN, 10, 50, largeDamages, largeN * 50);

        int[] randomDamages = new Random().ints(largeN, 1, 1000000).toArray();
        long startTime = System.currentTimeMillis();
        int result = virusResolving(largeN, 10, 100, randomDamages);
        long endTime = System.currentTimeMillis();

        System.out.println("Test Case 6 (Random 50k inputs): Result=" + result +
                " | Execution Time: " + (endTime - startTime) + "ms [PASS]");
    }

    private static void runTestCase(int id, int N, int X, int Y, int[] damages, int expected) {
        int actual = virusResolving(N, X, Y, damages);
        String status = (actual == expected) ? "PASS" : "FAIL";
        System.out.println("Test Case " + id + ": Expected=" + expected +
                ", Actual=" + actual + " [" + status + "]");
    }
}