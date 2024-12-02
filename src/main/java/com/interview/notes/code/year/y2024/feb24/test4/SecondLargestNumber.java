package com.interview.notes.code.year.y2024.feb24.test4;

public class SecondLargestNumber {

    public static int findSecondLargest(int[] arr) {
        // Initialize the largest and second largest with minimum values
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        // Iterate through the array
        for (int i = 0; i < arr.length; i++) {
            // If current element is greater than largest
            if (arr[i] > largest) {
                secondLargest = largest; // update secondLargest to largest's value
                largest = arr[i]; // update largest to current element
            } else if (arr[i] > secondLargest && arr[i] != largest) {
                // If current element is in between largest and secondLargest
                secondLargest = arr[i];
            }
        }
        return secondLargest;
    }

    public static void main(String[] args) {
        int[] a = {1, 4, 2, 7, 5, 9};
        int secondLargest = findSecondLargest(a);
        System.out.println("The second largest number is: " + secondLargest);
    }
}
