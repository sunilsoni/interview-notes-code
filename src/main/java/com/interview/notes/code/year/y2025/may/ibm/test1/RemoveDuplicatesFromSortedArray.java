package com.interview.notes.code.year.y2025.may.ibm.test1;

public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        // Array of test cases: each entry is { inputArray, expectedUniqueCount, expectedUniqueElements... }
        Object[][] tests = {
            { new int[] {},           0 },                              // empty
            { new int[] {1},          1, 1 },                           // single element
            { new int[] {2, 2},       1, 2 },                           // all same
            { new int[] {1, 2, 3},     3, 1, 2, 3 },                     // no duplicates
            { new int[] {1,2,2,3,4,4}, 4, 1, 2, 3, 4 },                  // mixed duplicates
            { new int[] {1,1,1,1,1},   1, 1 },                           // all duplicates
            // large test: 1M elements, half duplicates
            { generateLargeTest(1_000_000), 500_000 /* expected unique count */ }
        };

        // Run each test
        for (int t = 0; t < tests.length; t++) {
            int[] arr = (int[]) tests[t][0];
            int expectedCount = (int) tests[t][1];
            // run removal
            int newCount = removeDuplicates(arr, arr.length);
            // verify count
            boolean pass = newCount == expectedCount;
            // if unique elements were provided, verify them
            if (pass && tests[t].length > 2) {
                for (int i = 0; i < expectedCount; i++) {
                    int expectedVal = (int) tests[t][2 + i];
                    if (arr[i] != expectedVal) {
                        pass = false;
                        break;
                    }
                }
            }
            System.out.printf("Test %d: %s (got %d)%n", t, pass ? "PASS" : "FAIL", newCount);
        }
    }

    /**
     * Removes duplicates from a sorted array in-place.
     * @param arr the sorted input array
     * @param n   the original length of arr
     * @return the length of the array after removing duplicates
     */
    static int removeDuplicates(int[] arr, int n) {
        // if array is empty, nothing to do
        if (n == 0) return 0;

        int writeIdx = 0; // index where we write the next unique element
        // start readIdx from 1 since we treat arr[0] as the first unique
        for (int readIdx = 1; readIdx < n; readIdx++) {
            // whenever we find a new value...
            if (arr[readIdx] != arr[writeIdx]) {
                writeIdx++;                     // advance write pointer
                arr[writeIdx] = arr[readIdx];   // copy new unique value
            }
        }
        // writeIdx is index of last unique, so count = index+1
        return writeIdx + 1;
    }

    /**
     * Generates a large sorted test array of size n
     * with exactly n/2 unique values (each repeated twice).
     */
    static int[] generateLargeTest(int n) {
        int[] big = new int[n];
        for (int i = 0; i < n; i++) {
            big[i] = i / 2; // each number appears twice
        }
        return big;
    }
}