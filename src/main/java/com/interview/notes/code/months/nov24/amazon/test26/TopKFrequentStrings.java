package com.interview.notes.code.months.nov24.amazon.test26;

import java.util.*;

public class TopKFrequentStrings {
    public static String[] topKFrequent(String[] words, int k) {
        // Count the frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Use a PriorityQueue to keep the top k frequent words
        PriorityQueue<String> pq = new PriorityQueue<>(
            (w1, w2) -> frequencyMap.get(w1).equals(frequencyMap.get(w2)) ?
                w2.compareTo(w1) : frequencyMap.get(w1) - frequencyMap.get(w2)
        );

        for (String word : frequencyMap.keySet()) {
            pq.offer(word);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Build the result array
        String[] result = new String[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        testCase(new String[]{"amazon", "amazon", "media", "experience", "amazon", "media"}, 2, new String[]{"amazon", "media"});
        testCase(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4, new String[]{"the", "is", "sunny", "day"});
        testCase(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2, new String[]{"i", "love"});
        testCase(new String[]{"a", "a", "a", "b", "b", "c"}, 2, new String[]{"a", "b"});
        
        // Large input test case
        String[] largeInput = new String[1000000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = "word" + (i % 1000);
        }
        long startTime = System.currentTimeMillis();
        String[] largeResult = topKFrequent(largeInput, 10);
        long endTime = System.currentTimeMillis();
        System.out.println("Large input test case (1,000,000 words):");
        System.out.println("Time taken: " + (endTime - startTime) + " ms");
        System.out.println("Result: " + Arrays.toString(largeResult));
    }

    private static void testCase(String[] words, int k, String[] expected) {
        String[] result = topKFrequent(words, k);
        boolean pass = Arrays.equals(result, expected);
        System.out.println("Test case: " + Arrays.toString(words) + ", k=" + k);
        System.out.println("Expected: " + Arrays.toString(expected));
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println("PASS: " + pass);
        System.out.println();
    }
}
