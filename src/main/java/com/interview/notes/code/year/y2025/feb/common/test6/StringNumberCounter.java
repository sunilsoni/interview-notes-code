package com.interview.notes.code.year.y2025.feb.common.test6;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class StringNumberCounter {
    private static final Logger LOGGER = Logger.getLogger(StringNumberCounter.class.getName());

    public static void main(String[] args) {
        // Test data
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6", "abc", "8", "9", "10");

        System.out.println("Original list: " + numbers);

        // Method 1: Basic counting
        long basicCount = countEvenBasic(numbers);
        System.out.println("\n1. Basic count of even numbers: " + basicCount);

        // Method 2: With exception handling
        long safeCount = countEvenSafe(numbers);
        System.out.println("\n2. Safe count of even numbers (with exception handling): " + safeCount);

        // Method 3: Using parallel stream
        long parallelCount = countEvenParallel(numbers);
        System.out.println("\n3. Parallel stream count of even numbers: " + parallelCount);

        // Method 4: Using Optional
        long optionalCount = countEvenOptional(numbers);
        System.out.println("\n4. Optional-based count of even numbers: " + optionalCount);

        // Method 5: With detailed statistics
        Map<String, Object> stats = getEvenNumberStats(numbers);
        System.out.println("\n5. Detailed statistics:");
        stats.forEach((key, value) -> System.out.println("   " + key + ": " + value));
    }

    // Method 1: Basic counting
    private static long countEvenBasic(List<String> numbers) {
        return numbers.stream()
                .filter(str -> str.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(num -> num % 2 == 0)
                .count();
    }

    // Method 2: Safe counting with exception handling
    private static long countEvenSafe(List<String> numbers) {
        return numbers.stream()
                .map(str -> {
                    try {
                        return Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        LOGGER.warning("Invalid number format: " + str);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(num -> num % 2 == 0)
                .count();
    }

    // Method 3: Parallel stream counting
    private static long countEvenParallel(List<String> numbers) {
        return numbers.parallelStream()
                .filter(str -> str.matches("\\d+"))
                .map(Integer::parseInt)
                .filter(num -> num % 2 == 0)
                .count();
    }

    // Method 4: Optional-based counting
    private static long countEvenOptional(List<String> numbers) {
        return numbers.stream()
                .map(str -> {
                    return Optional.ofNullable(str)
                            .filter(s -> s.matches("\\d+"))
                            .map(Integer::parseInt);
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(num -> num % 2 == 0)
                .count();
    }

    // Method 5: Detailed statistics
    private static Map<String, Object> getEvenNumberStats(List<String> numbers) {
        Map<String, Object> stats = new HashMap<>();

        List<Integer> validNumbers = numbers.stream()
                .filter(str -> str.matches("\\d+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> evenNumbers = validNumbers.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());

        stats.put("Total strings", numbers.size());
        stats.put("Valid numbers", validNumbers.size());
        stats.put("Even numbers count", evenNumbers.size());
        stats.put("Even numbers", evenNumbers);
        stats.put("Invalid entries", numbers.size() - validNumbers.size());

        if (!evenNumbers.isEmpty()) {
            stats.put("Maximum even number", Collections.max(evenNumbers));
            stats.put("Minimum even number", Collections.min(evenNumbers));
        }

        return stats;
    }
}
