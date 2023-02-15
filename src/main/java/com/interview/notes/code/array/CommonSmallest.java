package com.interview.notes.code.array;

//Here's a Java program to find the common smallest number among 3 arrays:
public class CommonSmallest {

    /**
     * The approach used here is similar to the previous program, but with a couple of key differences:
     *
     * The if-else conditions used to check which pointer to increment have been modified to ensure that we always increment the pointer for the smallest current element among the three arrays.
     *
     * The equality check for the current elements at the three pointers has been moved to the beginning of the while loop. This is to ensure that we exit the loop as soon as we find a common element, rather than continuing to increment pointers unnecessarily.
     * @param arr1
     * @param arr2
     * @param arr3
     * @return
     */
    public static int findCommonSmallest(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                return arr1[i];
            } else if (arr1[i] <= arr2[j] && arr1[i] <= arr3[k]) {
                i++;
            } else if (arr2[j] <= arr1[i] && arr2[j] <= arr3[k]) {
                j++;
            } else {
                k++;
            }
        }
        return -1; // no common smallest element found
    }

    /**
     * Here's how the function works:
     *
     * Initialize three pointers, one for each array.
     * While all three pointers are within their respective arrays:
     * a. If the current element at all three pointers is the same, return that element.
     * b. Otherwise, increment the pointer for the array with the smallest current element.
     * If no common smallest element is found, return -1.
     * @param arr1
     * @param arr2
     * @param arr3
     * @return
     */
    public static int findCommonSmallest1(int[] arr1, int[] arr2, int[] arr3) {
        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                return arr1[i];
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr2[j] < arr3[k]) {
                j++;
            } else {
                k++;
            }
        }
        return -1; // no common smallest element found
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 6, 9, 10};
        int[] arr2 = {2, 3, 4, 5, 6, 8, 9};
        int[] arr3 = {1, 2, 3, 5, 6, 7, 9};

        int commonSmallest = findCommonSmallest(arr1, arr2, arr3);
        System.out.println("The smallest common element is: " + commonSmallest);

    }
}
