package com.interview.notes.code.year.y2025.jan24.test15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static List<Integer> getOptimalPriority(List<Integer> priority) {
        List<Integer> cpu = new ArrayList<>();
        List<Integer> io = new ArrayList<>();
        for (int p : priority) {
            if (p % 2 == 1) {
                cpu.add(p);
            } else {
                io.add(p);
            }
        }

        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < cpu.size() && j < io.size()) {
            if (cpu.get(i) < io.get(j)) {
                merged.add(cpu.get(i));
                i++;
            } else {
                merged.add(io.get(j));
                j++;
            }
        }
        // Add remaining elements from CPU or IO
        while (i < cpu.size()) {
            merged.add(cpu.get(i++));
        }
        while (j < io.size()) {
            merged.add(io.get(j++));
        }
        return merged;
    }

    public static void main(String[] args) {
        // Test case 1
        List<Integer> input1 = Arrays.asList(2, 4, 6, 4, 3, 2);
        List<Integer> expected1 = Arrays.asList(2, 3, 4, 6, 4, 2);
        List<Integer> result1 = getOptimalPriority(input1);
        System.out.println("Test case 1: " + (result1.equals(expected1) ? "Passed" : "Failed"));

        // Test case 2
        List<Integer> input2 = Arrays.asList(0, 7, 0, 9);
        List<Integer> expected2 = Arrays.asList(0, 0, 7, 9);
        List<Integer> result2 = getOptimalPriority(input2);
        System.out.println("Test case 2: " + (result2.equals(expected2) ? "Passed" : "Failed"));

        // Test case 3
        List<Integer> input3 = Arrays.asList(9, 4, 8, 6, 3);
        List<Integer> expected3 = Arrays.asList(4, 8, 6, 9, 3);
        List<Integer> result3 = getOptimalPriority(input3);
        System.out.println("Test case 3: " + (result3.equals(expected3) ? "Passed" : "Failed"));

        // Additional test case
        List<Integer> input4 = Arrays.asList(3, 1, 2, 0);
        List<Integer> expected4 = Arrays.asList(2, 0, 3, 1);
        List<Integer> result4 = getOptimalPriority(input4);
        System.out.println("Test case 4: " + (result4.equals(expected4) ? "Passed" : "Failed"));
    }
}