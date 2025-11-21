package com.interview.notes.code.year.y2025.november.common.test3;

import java.util.*;
import java.util.stream.Collectors;

class BoxGroupingService {

    public static int countBoxGroups(List<Integer> boxSizes) {
        if (boxSizes == null || boxSizes.isEmpty()) {
            return 0;
        }

        List<Integer> sorted = boxSizes.stream()
                .sorted()
                .collect(Collectors.toList());

        int groups = 1;
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i) - sorted.get(i - 1) > 1) {
                groups++;
            }
        }
        return groups;
    }

    public static void main(String[] args) {
        Map<List<Integer>, Integer> testCases = new LinkedHashMap<>();
        testCases.put(Arrays.asList(1, 2, 3, 5, 6, 8), 3);
        testCases.put(Arrays.asList(2, 1, 1, 3), 2);
        testCases.put(Arrays.asList(10, 12, 14, 16), 4);
        testCases.put(Arrays.asList(1, 1, 1, 1), 1);
        testCases.put(Arrays.asList(1, 3, 5, 7, 9), 5);
        testCases.put(Arrays.asList(1000000, 999999, 1000001, 1000002), 1);

        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(i * 2);
        }
        testCases.put(largeInput, 100000);

        int index = 1;
        for (Map.Entry<List<Integer>, Integer> entry : testCases.entrySet()) {
            int result = countBoxGroups(entry.getKey());
            String status = result == entry.getValue() ? "PASS" : "FAIL";
            System.out.println("Test " + index + ": Expected=" + entry.getValue() + ", Got=" + result + " → " + status);
            index++;
        }
    }
}
