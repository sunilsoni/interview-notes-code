package com.interview.notes.code.july23.test12;

/*
input: { 1, 0, 0, 0, 1, 0, 1, 1, 1}

segregate 0's and 1's without using sorting

output: {0,0,0,0,1,1,1,1,1}

 */
public class SegregateZerosAndOnes {
    public static void main(String[] args) {
        int[] arr = { 1, 0, 0, 0, 1, 0, 1, 1, 1 };
        segregate(arr);
        
        // print the segregated array
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void segregate(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left < right) {
            // Move left pointer to the right until a 1 is found
            while (arr[left] == 0 && left < right) {
                left++;
            }

            // Move right pointer to the left until a 0 is found
            while (arr[right] == 1 && left < right) {
                right--;
            }

            // If left is still less than right, swap the elements
            if (left < right) {
                // Swap arr[left] and arr[right]
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                
                // Move the pointers
                left++;
                right--;
            }
        }
    }
}
