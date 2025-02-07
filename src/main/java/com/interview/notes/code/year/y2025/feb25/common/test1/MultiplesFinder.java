package com.interview.notes.code.year.y2025.feb25.common.test1;

public class MultiplesFinder {
    public static void main(String[] args) {
        // Create array from 1 to 20
        int[] numbers = new int[20];
        for (int i = 0; i < 20; i++) {
            numbers[i] = i + 1;
        }

        // Initialize sums for each multiple
        int sumMultiplesOf2 = 0;
        int sumMultiplesOf3 = 0;
        int sumMultiplesOf4 = 0;
        int sumMultiplesOf5 = 0;

        // Find and print multiples
        System.out.println("Multiples of 2: ");
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.print(num + " ");
                sumMultiplesOf2 += num;
            }
        }
        System.out.println("\nSum of multiples of 2: " + sumMultiplesOf2);

        System.out.println("\nMultiples of 3: ");
        for (int num : numbers) {
            if (num % 3 == 0) {
                System.out.print(num + " ");
                sumMultiplesOf3 += num;
            }
        }
        System.out.println("\nSum of multiples of 3: " + sumMultiplesOf3);

        System.out.println("\nMultiples of 4: ");
        for (int num : numbers) {
            if (num % 4 == 0) {
                System.out.print(num + " ");
                sumMultiplesOf4 += num;
            }
        }
        System.out.println("\nSum of multiples of 4: " + sumMultiplesOf4);

        System.out.println("\nMultiples of 5: ");
        for (int num : numbers) {
            if (num % 5 == 0) {
                System.out.print(num + " ");
                sumMultiplesOf5 += num;
            }
        }
        System.out.println("\nSum of multiples of 5: " + sumMultiplesOf5);

        // Calculate total sum of all multiples
        int totalSum = sumMultiplesOf2 + sumMultiplesOf3 + sumMultiplesOf4 + sumMultiplesOf5;
        System.out.println("\nTotal sum of all multiples: " + totalSum);
    }
}
