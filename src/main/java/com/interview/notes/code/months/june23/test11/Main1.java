package com.interview.notes.code.months.june23.test11;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // read the size of the array
        int n = scanner.nextInt();
        int[] array = new int[n];

        // read the array
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // compute the product modulo 100
        int result = 1;
        for (int i = 0; i < n; i++) {
            result = (result * array[i]) % 100;
        }

        // print the result
        if (result < 10) {
            System.out.printf("0%d%n", result);
        } else {
            System.out.println(result);
        }
    }
}
