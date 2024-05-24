package com.interview.notes.code.months.may24.test7;

public class Main1 {

    // Main method
    public static void main(String[] args) {
        // Call the createIntArray method to create and print the integer array
        Main1 main = new Main1();
        main.createIntArray();
    }

    // Method to create and print an integer array
    public void createIntArray() {
        int[] array = {1, 2, 3, 4, 5}; // Example array, you can modify it as needed
        System.out.print("Array elements: ");
        for (int i = 0; i <= array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
