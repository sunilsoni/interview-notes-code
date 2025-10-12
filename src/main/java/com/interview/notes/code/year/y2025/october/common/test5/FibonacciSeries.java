package com.interview.notes.code.year.y2025.october.common.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class FibonacciSeries {
    public static List<Integer> generateFibonacci(int n) {
        if (n <= 0) return Collections.emptyList();
        if (n == 1) return List.of(0);
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1));
        IntStream.range(2, n).forEach(i -> list.add(list.get(i - 1) + list.get(i - 2)));
        return list;
    }

    public static void main(String[] args) {
        List<Integer> result1 = generateFibonacci(5);
        List<Integer> result2 = generateFibonacci(10);
        List<Integer> result3 = generateFibonacci(1);
        List<Integer> result4 = generateFibonacci(0);
        List<Integer> result5 = generateFibonacci(30);
        boolean pass1 = result1.equals(Arrays.asList(0, 1, 1, 2, 3));
        boolean pass2 = result2.equals(Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13, 21, 34));
        boolean pass3 = result3.equals(List.of(0));
        boolean pass4 = result4.isEmpty();
        boolean pass5 = result5.size() == 30;
        System.out.println(pass1 ? "PASS" : "FAIL");
        System.out.println(pass2 ? "PASS" : "FAIL");
        System.out.println(pass3 ? "PASS" : "FAIL");
        System.out.println(pass4 ? "PASS" : "FAIL");
        System.out.println("Large input test passed: " + pass5);
    }
}