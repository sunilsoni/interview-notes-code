package com.interview.notes.code.year.y2026.march.visa.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromicSequenceOperations {

    public static int getMinOperations(List<Integer> data) {
        Map<Integer, Integer> parent = new HashMap<>();
        int operations = 0;
        
        for (int i = 0, j = data.size() - 1; i < j; i++, j--) {
            int rootU = find(parent, data.get(i));
            int rootV = find(parent, data.get(j));
            
            if (rootU != rootV) {
                parent.put(rootU, rootV);
                operations++;
            }
        }
        
        return operations;
    }

    private static int find(Map<Integer, Integer> parent, int i) {
        parent.putIfAbsent(i, i);
        int root = i;
        while (root != parent.get(root)) {
            parent.put(root, parent.get(parent.get(root)));
            root = parent.get(root);
        }
        return root;
    }

    public static void main(String[] args) {
        runTest("Sample Case 0", List.of(1, 3, 5, 2, 1), 1);
        runTest("Sample Case 1", List.of(2, 4, 5, 7, 4, 5, 5), 2);
        runTest("Example Case", List.of(1, 2, 3, 3, 1, 4), 2);
        runTest("Already Palindrome", List.of(1, 2, 3, 2, 1), 0);
        
        List<Integer> largeData = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeData.add(i);
        }
        for (int i = 100000; i < 200000; i++) {
            largeData.add(i);
        }
        runTest("Large Data Case", largeData, 100000);
    }

    private static void runTest(String testName, List<Integer> input, int expected) {
        int result = getMinOperations(input);
        if (result == expected) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName + " | Expected: " + expected + " | Got: " + result);
        }
    }
}