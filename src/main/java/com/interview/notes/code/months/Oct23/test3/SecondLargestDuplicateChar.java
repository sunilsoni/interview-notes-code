package com.interview.notes.code.months.Oct23.test3;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Program on anagram with input parameter will be take from user like size
 *
 * Anagram example:
 * LISTEN = SILENT
 *
 *
 * write a program in Java how to find 2nd largest dupilcate character from given string 'abbcccddddcc' .
 */
public class SecondLargestDuplicateChar {
    public static void main(String[] args) {
        String str = "abbcccddddcc";
        char result = findSecondLargestDuplicateChar(str);
        Optional<Character> result1 = findSecondLargestDuplicateChar1(str);

        if (result != '\0') {
            System.out.println("The 2nd largest duplicate character is: " + result);
        } else {
            System.out.println("No such character found.");
        }
    }

    public static char findSecondLargestDuplicateChar(String str) {
        // Count the occurrences of each character
        Map<Character, Integer> countMap = new HashMap<>();
        for (char ch : str.toCharArray()) {
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
        }

        // Use a min heap to find the 2nd largest count
        PriorityQueue<Map.Entry<Character, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.getValue(), b.getValue()));

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            // We're interested only in duplicate characters
            if (entry.getValue() > 1) {
                minHeap.offer(entry);
                if (minHeap.size() > 2) {
                    minHeap.poll();
                }
            }
        }

        // Check if we have at least two duplicate characters
        if (minHeap.size() == 2) {
            return minHeap.poll().getKey();
        }
        
        return '\0';
    }
    public static Optional<Character> findSecondLargestDuplicateChar1(String str) {
        return Optional.ofNullable(
                str.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() > 1)
                        .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
                        .skip(1)
                        .findFirst()
                        .map(Map.Entry::getKey)
                        .orElse(null)
        );
    }
}
