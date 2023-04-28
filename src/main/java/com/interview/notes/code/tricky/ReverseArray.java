package com.interview.notes.code.tricky;

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = {2, 4, 8, 6};
        int n = arr.length;
        
        // Printing the original array
        System.out.print("Original array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        
        // Reversing the array
        for (int i = 0; i < n/2; i++) {
            int temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }
        
        // Printing the reversed array
        System.out.print("\nReversed array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
