package com.interview.notes.code.months.year2023.Sep23;

import java.util.Scanner;

public class SumOfNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        // BEGIN OF MISSING CODE SEGMENT
        int sum = N * (N + 1) / 2;
        // END OF MISSING CODE SEGMENT

        System.out.println(sum);
    }
}
