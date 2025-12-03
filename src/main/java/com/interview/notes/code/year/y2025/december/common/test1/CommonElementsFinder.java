package com.interview.notes.code.year.y2025.december.common.test1;

import java.util.HashSet;
import java.util.Set;

public class CommonElementsFinder {

    public static Set<Integer> findCommon(int[] arr1, int[] arr2) {

        Set<Integer> set = new HashSet<>();
        Set<Integer> common = new HashSet<>();

        // Add all elements of arr1
        for (int n : arr1) {
            set.add(n);
        }

        // Check arr2 elements
        for (int n : arr2) {
            if (set.contains(n)) {
                common.add(n);   // add only if common
            }
        }

        return common;
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4};
        int[] b = {3, 4, 5, 6};

        System.out.println("Common: " + findCommon(a, b));
    }
}
