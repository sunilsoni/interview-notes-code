package com.interview.notes.code.year.y2025.feb25.Amazon.test3;

import java.util.Arrays;

public class StreamExample {
    public static void main(String[] args) {
        System.out.println("Stream without terminal operation");
        
        // Stream without terminal operation
        Arrays.stream(new int[] { 1, 2, 3 }).map(i -> {
            System.out.println("doubling " + i);
            return i * 2;
        });

        System.out.println("Stream with terminal operation");
        
        // Stream with terminal operation
        int sum = Arrays.stream(new int[] { 1, 2, 3 }).map(i -> {
            System.out.println("doubling " + i);
            return i * 2;
        }).sum();

        // Output the sum to make it visible
        System.out.println("Sum: " + sum);
    }
}
