package com.interview.notes.code.year.y2024.aug24.test12;

import java.util.HashSet;
import java.util.Set;

public class FirstRepeatingElement {
    public static int findFirstRepeatingElement(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int firstRepeatingElement = -1;

        for (int num : arr) {
            if (set.contains(num)) {
                firstRepeatingElement = num;
                break;
            } else {
                set.add(num);
            }
        }

        return firstRepeatingElement;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 3, 1, 2};
        int firstRepeatingElement1 = findFirstRepeatingElement(arr1);
        System.out.println("First repeating element in {1, 2, 3, 4, 3, 1, 2}: " + firstRepeatingElement1); // Output: 3

        int[] arr2 = {5, 6, 7, 8, 9};
        int firstRepeatingElement2 = findFirstRepeatingElement(arr2);
        System.out.println("First repeating element in {5, 6, 7, 8, 9}: " + firstRepeatingElement2); // Output: -1 (no repeating element)

        int[] arr3 = {1, 1, 2, 3, 4};
        int firstRepeatingElement3 = findFirstRepeatingElement(arr3);
        System.out.println("First repeating element in {1, 1, 2, 3, 4}: " + firstRepeatingElement3); // Output: 1
    }
}
