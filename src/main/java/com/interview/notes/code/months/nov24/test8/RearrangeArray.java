package com.interview.notes.code.months.nov24.test8;

public class RearrangeArray {

    public static int[] rearrange(int[] arr) {
        int n = arr.length;

        // If the array is empty or has only one element, it's already valid
        if (n <= 1) {
            return arr;
        }

        // Index to place elements at the correct position
        int index = 1;  // Start from the second position as first element is fine

        for (int i = 1; i < n; i++) {
            // Check if the current element is the same as the previous one
            if (arr[i] == arr[i - 1]) {
                // If the previous two elements are the same, skip this one
                if (i + 1 < n && arr[i] == arr[i + 1]) {
                    continue;
                }
            }

            // Place the element at the correct index
            arr[index] = arr[i];
            index++;
        }

        return arr;
    }

    public static void test(int[] arr, int[] expected) {
        int[] result = rearrange(arr);

        // Check if the result matches the expected
        boolean pass = true;
        if (result.length != expected.length) {
            pass = false;
        } else {
            for (int i = 0; i < result.length; i++) {
                if (result[i] != expected[i]) {
                    pass = false;
                    break;
                }
            }
        }

        // Output result
        if (pass) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }

    public static void main(String[] args) {
        // Test Case 1
        int[] arr1 = {1, 1, 2, 2, 2, 3, 4, 4, 4, 4, 5};
        int[] expected1 = {1, 1, 2, 2, 3, 4, 4, 5, 2, 4, 4};
        test(arr1, expected1);

        // Test Case 2: Edge Case - Empty Array
        int[] arr2 = {};
        int[] expected2 = {};
        test(arr2, expected2);

        // Test Case 3: Edge Case - Single Element Array
        int[] arr3 = {5};
        int[] expected3 = {5};
        test(arr3, expected3);

        // Test Case 4: All elements are the same
        int[] arr4 = {3, 3, 3, 3, 3, 3};
        int[] expected4 = {3, 3, 3, 3};
        test(arr4, expected4);

        // Test Case 5: No duplicates
        int[] arr5 = {1, 2, 3, 4, 5};
        int[] expected5 = {1, 2, 3, 4, 5};
        test(arr5, expected5);

        // Test Case 6: Large Data
        int[] arr6 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr6[i] = i % 5;
        }
        // We expect no more than two of any number consecutively, so it's hard to manually compute the expected.
        // Let's just check if no number appears more than twice in a row.
        System.out.println("Testing large data...");
        int[] result6 = rearrange(arr6);
        boolean isValid = true;
        for (int i = 2; i < result6.length; i++) {
            if (result6[i] == result6[i - 1] && result6[i] == result6[i - 2]) {
                isValid = false;
                break;
            }
        }
        if (isValid) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }
}
