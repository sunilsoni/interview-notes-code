package com.interview.notes.code.year.y2026.jan.cognizant.test2;

public class SumOfMultiples {
    public static void main(String[] args) {
        int[] a = new int[20];
        for (int i = 0; i < 20; i++) {
            a[i] = i + 1; // fill array with 1 to 20
        }

        int sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0;

        for (int num : a) {
            if (num % 2 == 0) sum2 += num;
            if (num % 3 == 0) sum3 += num;
            if (num % 4 == 0) sum4 += num;
            if (num % 5 == 0) sum5 += num;
        }

        System.out.println("Sum of multiples of 2: " + sum2);
        System.out.println("Sum of multiples of 3: " + sum3);
        System.out.println("Sum of multiples of 4: " + sum4);
        System.out.println("Sum of multiples of 5: " + sum5);
    }
}
