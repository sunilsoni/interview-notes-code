package com.interview.notes.code.year.y2025.october.CodeSignal.test2;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    static int solution(int finish, int[] scooters) {
        if (finish <= 0 || scooters == null || scooters.length == 0) return 0;
        int[] a = Arrays.stream(scooters).sorted().toArray();
        int i = 0, n = a.length, pos = 0, total = 0;
        while (pos < finish) {
            while (i < n && a[i] <= pos) i++;
            if (i == n) break;
            int s = a[i];
            int ride = Math.min(10, finish - s);
            total += Math.max(0, ride);
            pos = Math.min(finish, s + 10);
        }
        return total;
    }

    static int slowSolution(int finish, int[] scooters) {
        if (finish <= 0 || scooters == null || scooters.length == 0) return 0;
        int pos = 0, total = 0;
        while (pos < finish) {
            int next = Integer.MAX_VALUE;
            for (int v : scooters) if (v > pos && v < next) next = v;
            if (next == Integer.MAX_VALUE) break;
            int ride = Math.min(10, finish - next);
            total += Math.max(0, ride);
            pos = Math.min(finish, next + 10);
        }
        return total;
    }

    static void runTest(String name, int finish, int[] scooters, int expected) {
        int got = solution(finish, scooters);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL") +
                " expected=" + expected + " got=" + got);
    }

    static void runEq(String name, int finish, int[] scooters) {
        int fast = solution(finish, scooters);
        int slow = slowSolution(finish, scooters);
        System.out.println(name + ": " + (fast == slow ? "PASS" : "FAIL") +
                " fast=" + fast + " slow=" + slow);
    }

    public static void main(String[] args) {
        runTest("Ex1", 23, new int[]{7, 4, 14}, 19);
        runTest("Ex2", 27, new int[]{15, 7, 3, 10}, 20);
        runTest("Ex3", 10, new int[]{}, 0);
        runTest("Edge1", 5, new int[]{1}, 4);
        runTest("Edge2", 20, new int[]{19}, 1);
        runTest("Edge3", 1, new int[]{}, 0);
        runTest("Edge4", 1000, new int[]{1, 2, 3}, 10 + 9);
        runTest("Edge5", 30, new int[]{5, 6, 7, 8, 9}, 10 + 10 + 1);

        Random rnd = new Random(42);
        for (int t = 1; t <= 20; t++) {
            int finish = 1 + rnd.nextInt(1000);
            int m = rnd.nextInt(200);
            int[] scooters = IntStream.generate(() -> 1 + rnd.nextInt(Math.max(1, finish - 1)))
                    .distinct().limit(m).toArray();
            runEq("Random" + t, finish, scooters);
        }

        int finishLarge = 1000;
        int[] large = IntStream.iterate(1, x -> x + 5).limit(200).map(x -> Math.min(x, finishLarge - 1)).distinct().toArray();
        System.out.println("Large: " + solution(finishLarge, large));
    }
}
