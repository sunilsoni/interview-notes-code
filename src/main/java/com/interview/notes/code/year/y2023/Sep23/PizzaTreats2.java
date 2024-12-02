package com.interview.notes.code.year.y2023.Sep23;

import java.util.Scanner;

public class PizzaTreats2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] visitors = new int[n];

        for (int i = 0; i < n; i++) {
            visitors[i] = sc.nextInt();
        }

        // Add one pizza to the next day if the current day has odd visitors
        for (int i = 0; i < n - 1; i++) {
            if (visitors[i] % 2 == 1) {
                visitors[i + 1]++;
            }
        }

        // Check if last day has odd visitors
        if (visitors[n - 1] % 2 == 1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }

        sc.close();
    }
}
