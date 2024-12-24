package com.interview.notes.code.months.dec24.amazon.test13;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FrequencyCounter {
    public static void main(String[] args) {
        Integer[] array = {1, 2, 3, 2, 1, 4, 5, 3, 2, 1};
        
        Map<Integer, Long> frequency = Arrays.stream(array)
                .collect(Collectors.groupingBy(
                        element -> element,
                        Collectors.counting()
                ));
        
        // Print results
        frequency.forEach((key, value) -> 
            System.out.println("Element " + key + " occurs " + value + " times"));
    }
}
