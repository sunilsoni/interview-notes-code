package com.interview.notes.code.year.y2026.feb.common.test11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinimumUniqueArraySumApp
{
    public static int getMinimumUniqueSum(List<Integer> arr)
    {
        Collections.sort(arr);
        int sum = 0;
        int prev = 0;

        for (int x : arr)
        {
            if (x <= prev)
            {
                x = prev + 1;
            }
            sum += x;
            prev = x;
        }

        return sum;
    }

    private static void check(int expected, List<Integer> input)
    {
        int actual = getMinimumUniqueSum(new ArrayList<>(input));
        System.out.println(actual == expected ? "PASS" : "FAIL");
    }

    public static void main(String[] args)
    {
        check(6, Arrays.asList(1, 2, 2));
        check(6, Arrays.asList(1, 2, 3));
        check(14, Arrays.asList(2, 2, 4, 5));
        check(17, Arrays.asList(3, 2, 1, 2, 7));
        check(1, List.of(1));
        check(15, Arrays.asList(1, 1, 1, 1, 1));

        List<Integer> large = new ArrayList<>();
        for (int i = 0; i < 2000; i++)
        {
            large.add(1);
        }
        int actual = getMinimumUniqueSum(large);
        int expected = 2000 * 2001 / 2;
        System.out.println(actual == expected ? "PASS" : "FAIL");
    }
}