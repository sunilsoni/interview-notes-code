package com.interview.notes.code.year.y2025.June.common.test10;

import java.util.Arrays;

public class StreamPipelineExample {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};

        long count = Arrays.stream(arr)
                .filter(n -> n % 2 == 0)     // Step 1: Even numbers
                .map(n -> n * 2)             // Step 2: Multiply by 2
                .map(n -> n - 1)             // Step 3: Subtract 1
                .filter(n -> n > 4)          // Step 4: Greater than 4
                .count();                    // Step 5: Count

        System.out.println("Count of matching elements: " + count);
    }
}
