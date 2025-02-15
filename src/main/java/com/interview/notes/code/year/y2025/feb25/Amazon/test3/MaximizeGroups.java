package com.interview.notes.code.year.y2025.feb25.Amazon.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaximizeGroups {

    public static int maximizeGroups(List<Integer> products) {
        int n = products.size();
        int[] prods = new int[n];
        for (int i = 0; i < n; i++) {
            prods[i] = products.get(i);
        }
        Arrays.sort(prods);

        int batches = 0;
        int required = 1;
        while (true) {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (prods[i] > 0) {
                    prods[i]--;
                    count++;
                }
            }
            if (count < required) {
                break;
            }
            batches++;
            required++;
        }
        return batches;
    }

    public static void main(String[] args) {
        // Sample Case 0
        List<Integer> products0 = new ArrayList<>(Arrays.asList(1, 2, 7));
        int result0 = maximizeGroups(products0);
        System.out.println("Sample Case 0: " + (result0 == 3 ? "PASS" : "FAIL"));

        // Sample Case 1
        List<Integer> products1 = new ArrayList<>(Arrays.asList(1, 2, 8, 9));
        int result1 = maximizeGroups(products1);
        System.out.println("Sample Case 1: " + (result1 == 4 ? "PASS" : "FAIL"));

        // Additional Test Cases

        // Test Case 2: All zeros
        List<Integer> products2 = new ArrayList<>(Arrays.asList(0, 0, 0));
        int result2 = maximizeGroups(products2);
        System.out.println("Test Case 2: " + (result2 == 0 ? "PASS" : "FAIL"));

        // Test Case 3: Single large value
        List<Integer> products3 = new ArrayList<>(Arrays.asList(1000000000));
        int result3 = maximizeGroups(products3);
        System.out.println("Test Case 3: " + (result3 == 1 ? "PASS" : "FAIL"));

        // Test Case 4: Large input with varied values
        List<Integer> products4 = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            products4.add(i % 1000); // Adding values from 0 to 999 repeatedly
        }
        int result4 = maximizeGroups(products4);
        System.out.println("Test Case 4: " + (result4 == 447 ? "PASS" : "FAIL")); //Expected 447

        // Test Case 5: Few products, large quantities
        List<Integer> products5 = new ArrayList<>(Arrays.asList(1000000000, 1000000000, 1000000000));
        int result5 = maximizeGroups(products5);
        System.out.println("Test Case 5: " + (result5 == 18257 ? "PASS" : "FAIL")); //Expected 18257

    }
}