package com.interview.notes.code.year.y2025.october.common.test4;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Result {
    private static boolean isExt(int[] a, int i) {
        return (i > 0 && i < a.length - 1)
                && ((a[i] > a[i - 1]
                && a[i] > a[i + 1])
                || (a[i] < a[i - 1]
                && a[i] < a[i + 1]));
    }

    public static int getMinInstability(List<Integer> request) {
        int n = request.size();
        if (n < 3) return 0;
        int[] a = request.stream().mapToInt(Integer::intValue).toArray();
        int base = 0;
        for (int i = 1; i < n - 1; i++) if (isExt(a, i)) base++;

        int bestReduce = 0;

        for (int i = 0; i < n; i++) {
            int cur = 0;
            for (int j = Math.max(1, i - 1); j <= Math.min(n - 2, i + 1); j++) if (isExt(a, j)) cur++;

            Set<Integer> cand = new LinkedHashSet<>();
            if (i - 1 >= 0) cand.add(a[i - 1]);
            if (i + 1 < n) cand.add(a[i + 1]);
            if (i - 2 >= 0) cand.add(a[i - 2]);
            if (i + 2 < n) cand.add(a[i + 2]);

            int old = a[i];
            for (int v : cand) {
                a[i] = v;
                int nxt = 0;
                for (int j = Math.max(1, i - 1); j <= Math.min(n - 2, i + 1); j++) if (isExt(a, j)) nxt++;
                bestReduce = Math.max(bestReduce, cur - nxt);
                if (bestReduce == cur) {
                    a[i] = old;
                    break;
                }
            }
            a[i] = old;
        }

        return base - bestReduce;
    }
}

public class Main {
    private static void run(String name, List<Integer> arr, int expected) {
        int got = Result.getMinInstability(arr);
        System.out.println((got == expected ? "PASS" : "FAIL") + " " + name + " expected=" + expected + " got=" + got);
    }

    public static void main(String[] args) {
        run("Sample0", Arrays.asList(1, 5, 3), 0);
        run("Sample1", Arrays.asList(5, 3, 6, 4, 6, 3), 1);
        run("Example", Arrays.asList(1, 2, 1, 2, 2), 0);
        run("MonoInc", IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList()), 0);
        run("MonoDec", IntStream.iterate(10, x -> x - 1).limit(10).boxed().collect(Collectors.toList()), 0);
        run("Flat", Arrays.asList(2, 2, 2, 2, 2), 0);
        run("EdgeChangeLeft", Arrays.asList(10, 1, 10), 0);
        run("EdgeChangeRight", Arrays.asList(10, 1, 10, 1), 1);

        List<Integer> largeInc = IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());
        System.out.println("Large input test passed: " + (Result.getMinInstability(largeInc) == 0));

        List<Integer> largeFlat = Collections.nCopies(100000, 7);
        System.out.println("Large input test passed: " + (Result.getMinInstability(largeFlat) == 0));
    }
}