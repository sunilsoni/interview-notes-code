package com.interview.notes.code.year.y2024.jan24.test13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GetMinLength {

    public static int getMinLength(List<Integer> a, int k) {
        int n = a.size();
        boolean mergeOccurred;

        do {
            mergeOccurred = false;
            for (int i = 0; i < n - 1; i++) {
                if (a.get(i) * a.get(i + 1) <= k) {
                    a.set(i, a.get(i) * a.get(i + 1)); // Perform the merge operation
                    a.remove(i + 1); // Remove the element that has been merged
                    n = a.size(); // Update the size of the list after the merge
                    mergeOccurred = true;
                    break; // Restart the process after every merge
                }
            }
        } while (mergeOccurred);

        return n; // Return the reduced size of the array
    }


    public static void main(String[] args) {
        // Example 1
        List<Integer> array1 = new ArrayList<>(Arrays.asList(2, 3, 3, 7, 5));
        int k1 = 20;
        int minLength1 = getMinLength(array1, k1);
        System.out.println("The minimum length of the first array after operations is: " + minLength1);

        // Example 2
        List<Integer> array2 = new ArrayList<>(Arrays.asList(1, 3, 2, 5, 4));
        int k2 = 6;
        int minLength2 = getMinLength(array2, k2);
        System.out.println("The minimum length of the second array after operations is: " + minLength2);
    }
}
