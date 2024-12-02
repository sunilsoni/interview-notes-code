package com.interview.notes.code.year.y2024.oct24.amazon.test11;

import java.util.ArrayList;
import java.util.Arrays;

/*
Given an array with n objects colored red, white or blue,
sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: Using library sort function is not allowed.
Example :
Input : 10 1 2 0 1 2]
Modify array so that it becomes : [0 0 1 1 2 2]
 */
public class ColorSorter {
    public static void sortColors(ArrayList<Integer> a) {
        int low = 0, mid = 0, high = a.size() - 1;

        while (mid <= high) {
            switch (a.get(mid)) {
                case 0:
                    swap(a, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(a, mid, high);
                    high--;
                    break;
            }
        }
    }

    private static void swap(ArrayList<Integer> a, int i, int j) {
        int temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    public static void main(String[] args) {
        // Test cases
        ArrayList<Integer> test1 = new ArrayList<>(Arrays.asList(2, 0, 2, 1, 1, 0));
        ArrayList<Integer> test2 = new ArrayList<>(Arrays.asList(2, 0, 1));
        ArrayList<Integer> test3 = new ArrayList<>(Arrays.asList(0));
        ArrayList<Integer> test4 = new ArrayList<>(Arrays.asList(1, 2, 0, 1, 2, 0, 0, 2, 1, 1));

        // Large input test
        ArrayList<Integer> largeTest = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeTest.add((int) (Math.random() * 3));
        }

        runTest(test1, "Test 1");
        runTest(test2, "Test 2");
        runTest(test3, "Test 3");
        runTest(test4, "Test 4");
        runTest(largeTest, "Large Input Test");
    }

    private static void runTest(ArrayList<Integer> test, String testName) {
        ArrayList<Integer> original = new ArrayList<>(test);
        long startTime = System.nanoTime();
        sortColors(test);
        long endTime = System.nanoTime();

        boolean passed = isSorted(test) && containsSameElements(original, test);

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Time taken: " + (endTime - startTime) / 1000000.0 + " ms");
        System.out.println("Original: " + original);
        System.out.println("Sorted: " + test);
        System.out.println();
    }

    private static boolean isSorted(ArrayList<Integer> a) {
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < a.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean containsSameElements(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() != b.size()) {
            return false;
        }
        int[] countA = new int[3];
        int[] countB = new int[3];
        for (int i = 0; i < a.size(); i++) {
            countA[a.get(i)]++;
            countB[b.get(i)]++;
        }
        return Arrays.equals(countA, countB);
    }
}
