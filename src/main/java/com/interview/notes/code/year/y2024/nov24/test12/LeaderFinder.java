package com.interview.notes.code.year.y2024.nov24.test12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
int[] arr = {14, 9, 11, 7, 8, 5, 3}
Output : {14,11,8,5,3}

14>9 so 14 is leader elements here..similar to this..and so on 7 is not eader to 8 because 7<8

java program to find all the leaders in an integer array

So an element said to be a leader. If all the elements are its right side are smaller than it.

Print all the leader elements.
 */
public class LeaderFinder {

    public static List<Integer> findLeaders(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int n = arr.length;
        int maxFromRight = arr[n - 1];

        // The rightmost element is always a leader
        leaders.add(maxFromRight);

        // Traverse the array from right to left
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > maxFromRight) {
                leaders.add(arr[i]);
                maxFromRight = arr[i];
            }
        }

        // Reverse the list to maintain original order
        Collections.reverse(leaders);
        return leaders;
    }

    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{14, 9, 11, 7, 8, 5, 3}, Arrays.asList(14, 11, 8, 5, 3));
        runTest(new int[]{16, 17, 4, 3, 5, 2}, Arrays.asList(17, 5, 2));
        runTest(new int[]{1, 2, 3, 4, 5}, List.of(5));
        runTest(new int[]{5, 4, 3, 2, 1}, Arrays.asList(5, 4, 3, 2, 1));
        runTest(new int[]{7}, List.of(7));
        runTest(new int[]{}, List.of());

        // Large data input test
        int[] largeArray = new int[100000];
        for (int i = 0; i < 100000; i++) {
            largeArray[i] = 100000 - i;
        }
        List<Integer> expectedLargeOutput = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            expectedLargeOutput.add(i);
        }
        Collections.reverse(expectedLargeOutput);
        runTest(largeArray, expectedLargeOutput);
    }

    private static void runTest(int[] input, List<Integer> expected) {
        List<Integer> result = findLeaders(input);
        boolean passed = result.equals(expected);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Expected: " + expected);
        System.out.println("Result: " + result);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println();
    }
}
