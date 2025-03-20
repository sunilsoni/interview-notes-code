package com.interview.notes.code.year.y2025.march.common;

import java.util.Arrays;

public class FibonacciSeries {
    public static void main1(String[] args) {
        int n = 10; // Number of terms
        int firstTerm = 0, secondTerm = 1;


        
        System.out.println("Fibonacci Series till " + n + " terms:");
        
        for (int i = 1; i <= n; ++i) {
            System.out.print(firstTerm + " ");
            
            // Compute next term
            int nextTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = nextTerm;
        }
    }

    public static void main(String[] args) {
        System.out.println("Stream A");
        Arrays.stream(new int[]{1, 2, 3}).map(i -> {
            System.out.println("doubling " + i);
            return i * 2;
        });

        System.out.println("Stream B");
        Arrays.stream(new int[]{1, 2, 3}).map(i -> {
            System.out.println("doubling " + i);
            return i * 2;
        }).sum();
    }
}
