package com.interview.notes.code.months.aug24.test12;

import java.util.HashSet;
import java.util.Set;

public class SecondRepeatingElement {
    public static int findSecondRepeatingElement(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int firstRepeatingElement = -1;
        int secondRepeatingElement = -1;

        for (int num : arr) {
            if (set.contains(num)) {
                if (firstRepeatingElement == -1) {
                    firstRepeatingElement = num;
                } else if (num != firstRepeatingElement) {
                    secondRepeatingElement = num;
                    break;
                }
            } else {
                set.add(num);
            }
        }

        return secondRepeatingElement;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 3, 1, 2, 5, 5};
        int secondRepeatingElement1 = findSecondRepeatingElement(arr1);
        System.out.println("Second repeating element in {1, 2, 3, 4, 3, 1, 2, 5, 5}: " + secondRepeatingElement1); // Output: 5

        int[] arr2 = {5, 6, 7, 8, 9};
        int secondRepeatingElement2 = findSecondRepeatingElement(arr2);
        System.out.println("Second repeating element in {5, 6, 7, 8, 9}: " + secondRepeatingElement2); // Output: -1 (no second repeating element)

        int[] arr3 = {1, 1, 2, 3, 4, 2};
        int secondRepeatingElement3 = findSecondRepeatingElement(arr3);
        System.out.println("Second repeating element in {1, 1, 2, 3, 4, 2}: " + secondRepeatingElement3); // Output: 2
    }
}
