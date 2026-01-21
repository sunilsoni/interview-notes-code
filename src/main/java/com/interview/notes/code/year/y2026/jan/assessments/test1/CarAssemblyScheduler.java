package com.interview.notes.code.year.y2026.jan.assessments.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class CarAssemblyScheduler {

    public static int solution(int[] A, int[] B, int X, int Y) {
        int n = A.length;
        long a = A[0];
        long b = B[0];
        for (int i = 1; i < n; i++) {
            long na = Math.min(a + A[i], b + Y + A[i]);
            long nb = Math.min(b + B[i], a + X + B[i]);
            a = na;
            b = nb;
        }
        return (int) Math.min(a, b);
    }

    static boolean check(int[] a, int[] b, int x, int y, int e) {
        return solution(a, b, x, y) == e;
    }

    static boolean large() {
        int n = 100000;
        int[] a = IntStream.generate(() -> 1).limit(n).toArray();
        int[] b = IntStream.generate(() -> 2).limit(n).toArray();
        return solution(a, b, 10000, 10000) == n;
    }

    public static void main(String[] args) {
        Map<String, Boolean> r = new LinkedHashMap<>();
        r.put("EX1", check(new int[]{1, 6, 2}, new int[]{3, 2, 5}, 2, 1, 8));
        r.put("EX2", check(new int[]{2, 11, 4, 4}, new int[]{9, 2, 5, 11}, 8, 4, 21));
        r.put("EX3", check(new int[]{1, 10, 1}, new int[]{10, 1, 10}, 1, 5, 9));
        r.put("EX4", check(new int[]{8, 3, 3}, new int[]{6, 1, 10}, 4, 3, 13));
        r.put("LARGE", large());
        r.forEach((k, v) -> System.out.println(k + " : " + (v ? "PASS" : "FAIL")));
    }
}
