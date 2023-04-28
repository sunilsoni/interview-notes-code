package com.interview.notes.code.array;

import java.util.*;

public class RemoveDuplicatesAndCount {

    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 3, 2, 4, 5, 3};
        
        // Remove duplicates
        Set<Integer> uniqueNumbers = new HashSet<>(Arrays.asList(numbers));
        Integer[] uniqueArray = uniqueNumbers.toArray(new Integer[0]);
        System.out.println("Unique Array: " + Arrays.toString(uniqueArray));
        
        // Count duplicates
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Integer number : numbers) {
            countMap.put(number, countMap.getOrDefault(number, 0) + 1);
        }
        System.out.println("Count Map: " + countMap);
    }
}
