package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Result {
    public static int getMaxPositiveTransactions(List<Integer> transactions) {
        long sumPos = transactions.stream().filter(x -> x > 0).mapToLong(Integer::intValue).sum();
        if (sumPos == 0) return 0;
        int posCount = (int) transactions.stream().filter(x -> x > 0).count();
        int zeroCount = (int) transactions.stream().filter(x -> x == 0).count();
        List<Integer> negs = transactions.stream().filter(x -> x < 0).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        long curr = sumPos;
        int count = posCount + zeroCount;
        for (int v : negs) {
            if (curr + v > 0) {
                curr += v;
                count++;
            } else break;
        }
        return count;
    }
}

public class Main {
    private static void run(String name, List<Integer> tx, int expected) {
        int got = Result.getMaxPositiveTransactions(tx);
        System.out.println((got == expected ? "PASS" : "FAIL") + " " + name + " expected=" + expected + " got=" + got);
    }

    public static void main(String[] args) {
        run("Sample0", Arrays.asList(-3, 0, 2, 1), 3);
        run("Sample1", Arrays.asList(-3, 0, -2), 0);
        run("OnlyPositives", Arrays.asList(1, 2, 3), 3);
        run("OnlyZeros", Arrays.asList(0, 0, 0), 0);
        run("Mixed1", Arrays.asList(5, -2, -2, -2, 0), 4);
        run("EdgeBig", Arrays.asList(1_000_000_000, -999_999_999), 2);
        run("TwoItems", Arrays.asList(-1, 2), 2);

        List<Integer> large1 = new ArrayList<>();
        for (int i = 0; i < 50000; i++) large1.add(1);
        for (int i = 0; i < 30000; i++) large1.add(0);
        for (int i = 0; i < 10000; i++) large1.add(-1);
        for (int i = 0; i < 10000; i++) large1.add(-2);
        System.out.println("Large input test passed: " + (Result.getMaxPositiveTransactions(large1) == 100000));

        List<Integer> large2 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) large2.add(1);
        for (int i = 0; i < 30000; i++) large2.add(-1);
        System.out.println("Large input test passed: " + (Result.getMaxPositiveTransactions(large2) == 19999));
    }
}