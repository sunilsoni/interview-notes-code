package com.interview.notes.code.year.y2024.feb24.test8;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindDuplicateCounts {

    public static Map<Integer, Long> findDuplicateCounts(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.groupingBy(Integer::valueOf, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 1, 3, 4, 4, 5);
        Map<Integer, Long> duplicateCounts = findDuplicateCounts(numbers);

        System.out.println("Duplicate counts: ");
        for (Map.Entry<Integer, Long> entry : duplicateCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
