package com.interview.notes.code.year.y2025.april.common.test1;

import java.util.Arrays;

public class SecondLargestStream {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 5, 6, 7}; // Your given array

        // Using Stream API to find the second largest element
        Integer secondLargest = Arrays.stream(array)
                .boxed() // Convert the int[] to Stream<Integer>
                .sorted((a, b) -> b - a) // Sort elements in descending order
                .skip(1) // Skip the first (largest) element
                .findFirst() // Get the next element (second largest)
                .orElseThrow(() -> new RuntimeException("Array must have at least two distinct elements!"));

        System.out.println("The second largest element is: " + secondLargest);
    }
}
