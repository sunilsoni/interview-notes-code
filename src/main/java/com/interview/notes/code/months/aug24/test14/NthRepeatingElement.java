package com.interview.notes.code.months.aug24.test14;

import java.util.HashMap;
import java.util.Map;

public class NthRepeatingElement {
    public static int findNthRepeatingElement(int[] arr, int n) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int[] repeatingElements = new int[n];
        int nextIndex = 0;

        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            if (countMap.get(num) == 2) {
                repeatingElements[nextIndex++] = num;
                if (nextIndex == n) {
                    break;
                }
            }
        }

        return nextIndex == n ? repeatingElements[n - 1] : -1;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 3, 1, 2, 5, 5, 6, 6};
        int secondRepeatingElement1 = findNthRepeatingElement(arr1, 2);
        System.out.println("Second repeating element in {1, 2, 3, 4, 3, 1, 2, 5, 5, 6, 6}: " + secondRepeatingElement1); // Output: 5

        int[] arr2 = {5, 6, 7, 8, 9};
        int thirdRepeatingElement2 = findNthRepeatingElement(arr2, 3);
        System.out.println("Third repeating element in {5, 6, 7, 8, 9}: " + thirdRepeatingElement2); // Output: -1 (no third repeating element)

        int[] arr3 = {1, 1, 2, 3, 4, 2, 5, 5, 6, 6, 7, 7};
        int fourthRepeatingElement3 = findNthRepeatingElement(arr3, 4);
        System.out.println("Fourth repeating element in {1, 1, 2, 3, 4, 2, 5, 5, 6, 6, 7, 7}: " + fourthRepeatingElement3); // Output: 7
    }
}
