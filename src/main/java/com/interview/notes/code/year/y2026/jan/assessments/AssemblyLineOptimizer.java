package com.interview.notes.code.year.y2026.jan.assessments;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class AssemblyLineOptimizer {

    public static int minTime(int[] a, int[] b, int x, int y) {
        int n = a.length;
        long da = a[0], db = b[0];
        for (int i = 1; i < n; i++) {
            long na = Math.min(da + a[i], db + y + a[i]);
            long nb = Math.min(db + b[i], da + x + b[i]);
            da = na;
            db = nb;
        }
        return (int) Math.min(da, db);
    }

    static boolean test(int[] a, int[] b, int x, int y, int exp) {
        return minTime(a, b, x, y) == exp;
    }

    static boolean largeTest() {
        int n = 100000;
        int[] a = IntStream.generate(() -> 1).limit(n).toArray();
        int[] b = IntStream.generate(() -> 1).limit(n).toArray();
        return minTime(a, b, 5, 5) == n;
    }

    public static void main(String[] args) {
        Map<String, Boolean> results = new LinkedHashMap<>();
        results.put("T1", test(new int[]{1,6,2}, new int[]{3,2,5}, 2, 1, 8));
        results.put("T2", test(new int[]{2,11,4,4}, new int[]{9,2,5,11}, 8, 4, 21));
        results.put("T3", test(new int[]{1,10,1}, new int[]{10,1,10}, 1, 5, 9));
        results.put("T4", test(new int[]{8,3,3}, new int[]{6,1,10}, 4, 3, 13));
        results.put("LARGE", largeTest());

        results.forEach((k,v) ->
                System.out.println(k + " : " + (v ? "PASS" : "FAIL")));
    }
}
