package com.interview.notes.code.year.y2026.march.common.test9;

import java.util.List;

public class OddOnesPairCounter
{
    public static long solve(List<Integer> a)
    {
        long even = 0, odd = 0;
        for (int n : a)
        {
            if ((n & 1) == 0) even++;
            else odd++;
        }
        return even * odd;
    }

    private static void test(String name, List<Integer> a, long expected)
    {
        long actual = solve(a);
        System.out.println(name + ": " + (actual == expected ? "PASS" : "FAIL expected=" + expected + " actual=" + actual));
    }

    public static void main(String[] args)
    {
        test("Example1", List.of(1, 2, 3, 4), 4);
        test("Example2", List.of(4, 7, 2), 2);
        test("Single", List.of(5), 0);
        test("AllEven", List.of(2, 4, 6, 8), 0);
        test("AllOdd", List.of(1, 3, 5, 7), 0);
        test("Mixed", List.of(1, 2, 3, 4, 5, 6), 9);

        int n = 100000;
        Integer[] big = new Integer[n];
        for (int i = 0; i < n; i++)
        {
            big[i] = i + 1;
        }
        test("Large", List.of(big), 2500000000L);
    }
}