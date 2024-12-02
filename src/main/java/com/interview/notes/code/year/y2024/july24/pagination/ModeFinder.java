package com.interview.notes.code.year.y2024.july24.pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModeFinder {

    public static List<Integer> findModes(List<Integer> numbers) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxFrequency = 0;

        // Count the frequency of each number
        for (int num : numbers) {
            int frequency = frequencyMap.getOrDefault(num, 0) + 1;
            frequencyMap.put(num, frequency);
            maxFrequency = Math.max(maxFrequency, frequency);
        }

        List<Integer> modes = new ArrayList<>();
        // If all numbers occur with the same frequency and that frequency is 1,
        // then all numbers are modes.
        if (maxFrequency == 1) {
            return new ArrayList<>(numbers);
        }

        // Find all numbers that occur with maxFrequency
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }

        return modes;
    }

    public static void main(String[] args) {
        List<Integer> numbers1 = List.of(2, 2, 3, 4, 5);
        System.out.println("Modes of the list are: " + findModes(numbers1));

        List<Integer> numbers2 = List.of(2, 2, 3, 3, 4);
        System.out.println("Modes of the list are: " + findModes(numbers2));

        List<Integer> numbers3 = List.of(1, 2, 3);
        System.out.println("Modes of the list are: " + findModes(numbers3));
    }
}
