package com.interview.notes.code.year.y2023.Sep23;

import java.util.HashSet;

public class DuplicateTest {

    public static int[] removeDuplicates(int[] arr) {
        // If the array is empty or only has one element, there's no need to process
        if (arr.length < 2) {
            return arr;
        }

        int noOfUniqueElements = arr.length; // Initially consider all elements as unique

        // Loop through the array to identify duplicates
        for (int i = 0; i < noOfUniqueElements; i++) {
            for (int j = i + 1; j < noOfUniqueElements; j++) {
                if (arr[i] == arr[j]) {
                    // Move the last unique element to current j position
                    arr[j] = arr[noOfUniqueElements - 1];
                    noOfUniqueElements--;
                    j--; // Decrement j to check with the swapped element
                }
            }
        }

        // Create a new array to hold the unique elements
        int[] newArray = new int[noOfUniqueElements];
        for (int i = 0; i < noOfUniqueElements; i++) {
            newArray[i] = arr[i];
        }

        return newArray;
    }

    public static int[] removeDuplicates2(int[] arr) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : arr) {
            set.add(num);
        }

        int[] uniqueArray = new int[set.size()];
        int index = 0;

        for (int num : set) {
            uniqueArray[index++] = num;
        }

        return uniqueArray;
    }

    public static int[] removeDuplicates3(int[] arr) {
        // Create a new array to store the unique elements of the original array.
        int[] uniqueArr = new int[arr.length];

        // Iterate over the original array.
        int uniqueArrIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            // Check if the current element is already present in the new array.
            boolean isPresent = false;
            for (int j = 0; j < uniqueArrIndex; j++) {
                if (arr[i] == uniqueArr[j]) {
                    isPresent = true;
                    break;
                }
            }

            // If the current element is not present in the new array, add it to the new array.
            if (!isPresent) {
                uniqueArr[uniqueArrIndex] = arr[i];
                uniqueArrIndex++;
            }
        }

        // Return the new array.
        return uniqueArr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 5, 5};
        int[] result = removeDuplicates(arr);

        //int[] result = removeDuplicates2(arr);
        for (int val : result) {
            System.out.print(val + " ");
        }
    }

}
