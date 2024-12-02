package com.interview.notes.code.year.y2024.oct24.test8;

import java.util.Arrays;

public class ArrayReorder {

    public static int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] result = new int[n];
        int left = 0, right = n - 1;
        int index = 0;

        // Pick elements alternately from start and end
        while (left <= right) {
            if (index % 2 == 0) {
                result[index] = numbers[left];
                left++;
            } else {
                result[index] = numbers[right];
                right--;
            }
            index++;
        }
        return result;
    }

    // Test cases method
    public static void runTests() {
        // Test Case 1
        int[] test1 = {0, 4, 3, 2, 1};
        int[] expected1 = {0, 1, 4, 2, 3};
        System.out.println(Arrays.equals(solution(test1), expected1) ? "Test 1 PASS" : "Test 1 FAIL");

        // Test Case 2
        int[] test2 = {-5, 4, 0, 3, 2, 2};
        int[] expected2 = {-5, 2, 4, 2, 0, 3};
        System.out.println(Arrays.equals(solution(test2), expected2) ? "Test 2 PASS" : "Test 2 FAIL");

        // Test Case 3: Large Data
        int[] largeTest = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeTest[i] = i; // Fill array with 0, 1, 2, ..., 999
        }
        int[] result = solution(largeTest);
        System.out.println(result.length == 1000 ? "Test 3 (Large Data) PASS" : "Test 3 (Large Data) FAIL");
    }

    public static void main(String[] args) {
        runTests();
    }
}
