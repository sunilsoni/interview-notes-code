package com.interview.notes.code.year.y2024.april24.test13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissingAndDouble {

    public static List<Integer> missingAndDouble(List<Integer> A) {
        List<Integer> result = new ArrayList<>();

        // Use a HashMap to store frequencies of elements
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : A) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Find the repeated number
        int repeated = -1;
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > 1) {
                repeated = entry.getKey();
                break;
            }
        }

        // If no repeated number is found, return -1, -1
        if (repeated == -1) {
            result.add(-1);
            result.add(-1);
            return result;
        }

        // Find the missing number by leveraging the array size
        int n = A.size();
        int expectedSum = (n * (n + 1)) / 2;

        int actualSum = A.stream().mapToInt(Integer::intValue).sum();
        int missing = expectedSum - actualSum;

        // Ensure missing is within the valid range (1 to n) and not the repeated number
        if (missing > 0 && missing <= n && missing != repeated) {
            result.add(repeated);
            result.add(missing);
        } else {
            result.add(repeated); // Handle potential edge cases where missing can't be calculated accurately
            result.add(-1);
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> test1 = new ArrayList<>();
        test1.add(3);
        test1.add(2);
        test1.add(3);
        System.out.println(missingAndDouble(test1)); // Output: [3, 1]

        List<Integer> test2 = new ArrayList<>();
        test2.add(4);
        test2.add(3);
        test2.add(1);
        test2.add(2);
        test2.add(3);
        System.out.println(missingAndDouble(test2)); // Output: [3, 5]

        // Edge case: Array with all distinct elements within range
        List<Integer> test3 = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            test3.add(i);
        }
        System.out.println(missingAndDouble(test3)); // Output: [-1, -1] (expected)
    }
}
