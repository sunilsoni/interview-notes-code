package com.interview.notes.code.year.y2026.feb.Codility.test1;

class MinStackTokensSolver {
    public static void main(String[] args) {
        MinStackTokensSolver solver = new MinStackTokensSolver();

        var tests = new Object[][] {
            {new int[]{2, 3}, 1},
            {new int[]{1, 0, 4, 1}, 1},
            {new int[]{5}, 2},
            {new int[]{4, 0, 3, 0}, 3},
            {new int[]{0}, 0},
            {new int[]{1}, 1},
            {new int[]{2}, 1},
            {new int[]{3}, 2},
            {new int[]{100000}, 3},
            {new int[]{1000000}, 6},
            {new int[]{3, 3, 3, 3}, 2},
            {new int[]{1, 1, 1, 1, 1}, 1},
            {new int[]{7, 15, 31}, 5},
            {new int[]{0, 0, 0, 0, 0}, 0},
            {new int[]{999999, 999999}, 6}
        };

        int pass = 0, fail = 0;
        for (var t : tests) {
            int[] arr = (int[]) t[0];
            int exp = (int) t[1];
            int res = solver.solution(arr);
            if (res == exp) {
                System.out.println("✓ PASS: " + java.util.Arrays.toString(arr) + " → " + res);
                pass++;
            } else {
                System.out.println("✗ FAIL: " + java.util.Arrays.toString(arr) + " → " + res + " (expected " + exp + ")");
                fail++;
            }
        }

        System.out.println("\n" + pass + " passed, " + fail + " failed out of " + tests.length);
    }
    
    public int solution(int[] A) {
        long total = 0;
        for (int x : A) total += x;
        return Long.bitCount(total);
    }
}