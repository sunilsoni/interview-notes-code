package com.interview.notes.code.year.y2025.october.CodeSignal.test5;

import java.util.TreeSet;
import java.util.stream.IntStream;

public class Main {

    static int solution(int[] heights, int viewingGap) {
        TreeSet<Integer> set = new TreeSet<>();
        long best = Long.MAX_VALUE;
        for (int i = viewingGap; i < heights.length; i++) {
            set.add(heights[i - viewingGap]);
            Integer lo = set.floor(heights[i]);
            if (lo != null) best = Math.min(best, Math.abs((long) heights[i] - lo));
            Integer hi = set.ceiling(heights[i]);
            if (hi != null) best = Math.min(best, Math.abs((long) heights[i] - hi));
        }
        return (int) best;
    }

    static int brute(int[] heights, int viewingGap) {
        long best = Long.MAX_VALUE;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + viewingGap; j < heights.length; j++) {
                best = Math.min(best, Math.abs((long) heights[i] - heights[j]));
            }
        }
        return (int) best;
    }

    static void run(String name, int[] heights, int gap, int expected) {
        int got = solution(heights, gap);
        System.out.println(name + ": " + (got == expected ? "PASS" : "FAIL") +
                " expected=" + expected + " got=" + got);
    }

    static void runEq(String name, int[] heights, int gap) {
        int a = solution(heights, gap);
        int b = brute(heights, gap);
        System.out.println(name + ": " + (a == b ? "PASS" : "FAIL") + " fast=" + a + " slow=" + b);
    }

    public static void main(String[] args) {
        run("Ex1", new int[]{1, 5, 4, 10, 9}, 3, 4);
        run("Ex2", new int[]{3, 10, 5, 8}, 1, 2);

        runEq("Check1", new int[]{1, 1}, 1);
        runEq("Check2", new int[]{5, 1, 5}, 2);
        runEq("Check3", new int[]{9, 7, 5, 3, 1}, 2);
        runEq("Check4", new int[]{2, 8, 6, 4, 10, 12}, 3);
        runEq("Check5", new int[]{1000000000, 1, 999999999, 2}, 2);

        int n = 100000;
        int[] large = IntStream.range(0, n).map(i -> (i * 1103515245 + 12345) >>> 1).map(x -> x % 1_000_000_000 + 1).toArray();
        long t1 = System.currentTimeMillis();
        int res = solution(large, 500);
        long t2 = System.currentTimeMillis();
        System.out.println("Large: " + res + " timeMs=" + (t2 - t1));
    }
}
