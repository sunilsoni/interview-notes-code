package com.interview.notes.code.year.y2024.may24.test13;

public class NumberConverter1 {

    // Function to find the minimum number of operations required to convert A to B
    public static int minOperations(int A, int B) {
        int operations = 0;

        // Keep performing operations until A becomes equal to B
        while (A != B) {
            // If B is greater than A, we have two options: multiply A by 2 or subtract 1
            if (B > A) {
                // If B is even, we prefer to multiply A by 2
                if (B % 2 == 0) {
                    A *= 2;
                } else {
                    // If B is odd, we prefer to subtract 1 to make A closer to B
                    A--;
                }
            } else {
                // If B is less than A, we simply subtract 1 from A
                A--;
            }
            operations++; // Increment the number of operations performed
        }

        return operations;
    }

    public static void main(String[] args) {
        // Sample inputs
        int[] A = {1, 9, 8, 400};
        int[] B = {1, 8, 6, 400};
        int[] C = {1, 8, 6, 40};
        int[] D = {1, 8, 5, 41};


        // Calculate and print the minimum number of operations for each sample
        for (int i = 0; i < A.length; i++) {
            System.out.print(minOperations(A[i], B[i]) + " ");
        }
        // Calculate and print the minimum number of operations for each sample
        for (int i = 0; i < A.length; i++) {
            System.out.print(minOperations(C[i], D[i]) + " ");
        }
    }
}
