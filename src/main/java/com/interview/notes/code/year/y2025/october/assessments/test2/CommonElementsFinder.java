package com.interview.notes.code.year.y2025.october.assessments.test2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommonElementsFinder {
    public static List<Integer> findCommon(int[] a, int[] b, int[] c) {
        Set<Integer> set1 = Arrays.stream(a).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(b).boxed().collect(Collectors.toSet());
        Set<Integer> set3 = Arrays.stream(c).boxed().collect(Collectors.toSet());
        return set1.stream()
                .filter(set2::contains)
                .filter(set3::contains)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5};
        int[] b1 = {0, 9, 8, 7, 6, 5, 1};
        int[] c1 = {1, 4, 7, 9, 11, 44};
        List<Integer> result1 = findCommon(a1, b1, c1);
        System.out.println("Test1: " + result1 + " -> " + (result1.equals(List.of(1)) ? "PASS" : "FAIL"));

        int[] a2 = {5, 6, 7, 8};
        int[] b2 = {7, 8, 9};
        int[] c2 = {8, 10, 7};
        List<Integer> result2 = findCommon(a2, b2, c2);
        System.out.println("Test2: " + result2 + " -> " + (result2.equals(Arrays.asList(7,8)) ? "PASS" : "FAIL"));

        int[] a3 = {1, 2, 3};
        int[] b3 = {4, 5, 6};
        int[] c3 = {7, 8, 9};
        List<Integer> result3 = findCommon(a3, b3, c3);
        System.out.println("Test3: " + result3 + " -> " + (result3.isEmpty() ? "PASS" : "FAIL"));

        int[] a4 = {};
        int[] b4 = {1,2,3};
        int[] c4 = {2,3,4};
        List<Integer> result4 = findCommon(a4, b4, c4);
        System.out.println("EdgeCase Empty: " + result4 + " -> " + (result4.isEmpty() ? "PASS" : "FAIL"));

        int[] largeA = IntStream.range(1, 1000000).toArray();
        int[] largeB = IntStream.range(500000, 1500000).toArray();
        int[] largeC = IntStream.range(750000, 1750000).toArray();
        List<Integer> largeResult = findCommon(largeA, largeB, largeC);
        System.out.println("LargeData: " + (largeResult.size() > 0 ? "PASS" : "FAIL"));
    }
}