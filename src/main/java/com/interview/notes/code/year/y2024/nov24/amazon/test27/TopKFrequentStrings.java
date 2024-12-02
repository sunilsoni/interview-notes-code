package com.interview.notes.code.year.y2024.nov24.amazon.test27;

import java.util.*;

public class TopKFrequentStrings {

    public static void main(String[] args) {
        // Test cases
        String[] test1 = {"amazon", "amazon", "media", "experience", "amazon", "media"};
        int k1 = 2;
        String[] expected1 = {"amazon", "media"};
        test(test1, k1, expected1);

        String[] test2 = {};
        int k2 = 1;
        String[] expected2 = {};
        test(test2, k2, expected2);

        String[] test3 = {"a", "b", "c", "a", "b", "a"};
        int k3 = 2;
        String[] expected3 = {"a", "b"};
        test(test3, k3, expected3);

        String[] test4 = {"a", "b", "c"};
        int k4 = 5;
        String[] expected4 = {"a", "b", "c"};
        test(test4, k4, expected4);

        String[] test5 = {"a", "b", "b", "c", "c"};
        int k5 = 2;
        String[] expected5 = {"b", "c"};
        test(test5, k5, expected5);

        // Test with large input
        String[] largeTest = new String[100000];
        Arrays.fill(largeTest, "test");
        int k6 = 1;
        String[] expected6 = {"test"};
        test(largeTest, k6, expected6);
    }

    public static void test(String[] strings, int k, String[] expected) {
        String[] result = topKFrequent(strings, k);
        if (Arrays.equals(result, expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Got:      " + Arrays.toString(result));
        }
    }

    public static String[] topKFrequent(String[] strings, int k) {
        if (strings == null || strings.length == 0 || k <= 0) {
            return new String[0];
        }

        // Step 1: Count frequencies
        Map<String, Integer> freqMap = new HashMap<>();
        for (String s : strings) {
            freqMap.put(s, freqMap.getOrDefault(s, 0) + 1);
        }

        // Step 2: Use a min-heap to keep top k elements
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(
                (a, b) -> {
                    if (!a.getValue().equals(b.getValue())) {
                        return a.getValue() - b.getValue();
                    } else {
                        return b.getKey().compareTo(a.getKey());
                    }
                }
        );

        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            heap.offer(entry);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // Step 3: Build the result array
        List<String> resultList = new ArrayList<>();
        while (!heap.isEmpty()) {
            resultList.add(heap.poll().getKey());
        }
        Collections.reverse(resultList); // Reverse to get highest frequency first
        return resultList.toArray(new String[0]);
    }
}
