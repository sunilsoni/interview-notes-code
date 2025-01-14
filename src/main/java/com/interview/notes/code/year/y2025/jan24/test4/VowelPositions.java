package com.interview.notes.code.year.y2025.jan24.test4;

import java.util.*;
import java.util.stream.*;

public class VowelPositions {
    public static void main(String[] args) {
        String str = "programming";
        
        // Get positions of vowels
        List<Integer> vowelPositions = IntStream.range(0, str.length()) // Create a stream of indices
            .filter(i -> "aeiouAEIOU".indexOf(str.charAt(i)) != -1) // Filter vowels
            .boxed() // Convert IntStream to Stream<Integer>
            .collect(Collectors.toList()); // Collect results into a list
        
        // Output the vowel positions
        System.out.println(vowelPositions);
    }
}
