package com.interview.notes.code.months.nov23.test6;

import java.util.Scanner;


/**
 * Complexity Analysis:
 * Time Complexity: O(n), where n is the length of the array. We iterate through the array once.
 * Space Complexity: O(1), as we use a fixed amount of extra space.
 */
public class ClosestElementFinder {

    public static void main(String[] args) {
        int[] arr = {2, 5, 6, 7, 8, 9};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int userInput = scanner.nextInt();
        int closestElement = findClosestElement(arr, userInput);
        System.out.println("Closest element: " + closestElement);
    }

    private static int findClosestElement(int[] arr, int target) {
        int closest = arr[0];
        int smallestDifference = Math.abs(arr[0] - target);

        for (int i = 1; i < arr.length; i++) {
            int currentDifference = Math.abs(arr[i] - target);
            if (currentDifference < smallestDifference) {
                smallestDifference = currentDifference;
                closest = arr[i];
            }
        }
        return closest;
    }
}
