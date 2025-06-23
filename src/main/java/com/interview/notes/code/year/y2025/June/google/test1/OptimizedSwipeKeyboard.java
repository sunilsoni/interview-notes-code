package com.interview.notes.code.year.y2025.June.google.test1;

import java.util.*;

public class OptimizedSwipeKeyboard {

    // Cache for frequently accessed words
    private static final Map<String, Set<String>> patternCache = new HashMap<>();

    // Preprocess dictionary to group words by length for faster filtering
    private static Map<Integer, List<String>> lengthMap;

    // Initialize the dictionary with preprocessing
    public static void initializeDictionary(List<String> dictionary) {
        lengthMap = dictionary.stream()
                .collect(HashMap::new,
                        (map, word) -> map.computeIfAbsent(word.length(), k -> new ArrayList<>()).add(word),
                        (map1, map2) -> map2.forEach((k, v) -> map1.merge(k, v, (v1, v2) -> {
                            v1.addAll(v2);
                            return v1;
                        })));
    }

    // Optimized method to find matching words
    public static List<String> findMatchingWords(String swipePattern, List<String> dictionary) {
        // Check cache first
        String cacheKey = swipePattern;
        if (patternCache.containsKey(cacheKey)) {
            return new ArrayList<>(patternCache.get(cacheKey));
        }

        // Initialize dictionary if not done
        if (lengthMap == null) {
            initializeDictionary(dictionary);
        }

        // Create result set for unique matches
        Set<String> matches = Collections.synchronizedSet(new HashSet<>());

        // Only check words that could potentially match (length less than or equal to swipe pattern)
        lengthMap.entrySet().stream()
                .filter(entry -> entry.getKey() <= swipePattern.length())
                .flatMap(entry -> entry.getValue().parallelStream())
                .filter(word -> isWordMatch(word, swipePattern))
                .forEach(matches::add);

        // Cache the result
        patternCache.put(cacheKey, matches);

        return new ArrayList<>(matches);
    }

    // Optimized matching algorithm using character indexing
    private static boolean isWordMatch(String word, String swipePattern) {
        int lastFoundIndex = -1;

        for (char c : word.toCharArray()) {
            lastFoundIndex = swipePattern.indexOf(c, lastFoundIndex + 1);
            if (lastFoundIndex == -1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Performance test setup
        int dictionarySize = 100000;
        int swipePatternLength = 1000;

        // Generate large test dictionary
        List<String> largeDictionary = generateLargeDictionary(dictionarySize);
        String largeSwipePattern = generateRandomSwipePattern(swipePatternLength);

        // Warm-up run
        findMatchingWords(largeSwipePattern, largeDictionary);

        // Actual performance test
        long startTime = System.nanoTime();
        List<String> results = findMatchingWords(largeSwipePattern, largeDictionary);
        long endTime = System.nanoTime();

        System.out.println("Performance Test Results:");
        System.out.println("Dictionary size: " + dictionarySize);
        System.out.println("Swipe pattern length: " + swipePatternLength);
        System.out.println("Matching words found: " + results.size());
        System.out.println("Execution time: " + ((endTime - startTime) / 1_000_000) + " ms");

        // Basic functionality tests
        runBasicTests();
    }

    private static void runBasicTests() {
        // Test case 1: Basic matching
        List<String> dict1 = Arrays.asList("hello", "help", "world");
        String swipe1 = "hgeflmlko";
        System.out.println("\nTest 1: " + findMatchingWords(swipe1, dict1));

        // Test case 2: Multiple matches
        List<String> dict2 = Arrays.asList("ace", "bad", "ag");
        String swipe2 = "abcdefg";
        System.out.println("Test 2: " + findMatchingWords(swipe2, dict2));

        // Test case 3: No matches
        List<String> dict3 = Arrays.asList("hello", "world");
        String swipe3 = "xyz";
        System.out.println("Test 3: " + findMatchingWords(swipe3, dict3));
    }

    // Helper method to generate test dictionary
    private static List<String> generateLargeDictionary(int size) {
        List<String> dictionary = new ArrayList<>();
        Random random = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < size; i++) {
            StringBuilder word = new StringBuilder();
            int wordLength = random.nextInt(10) + 1; // words of length 1-10

            for (int j = 0; j < wordLength; j++) {
                word.append(chars.charAt(random.nextInt(chars.length())));
            }
            dictionary.add(word.toString());
        }
        return dictionary;
    }

    // Helper method to generate random swipe pattern
    private static String generateRandomSwipePattern(int length) {
        Random random = new Random();
        StringBuilder pattern = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < length; i++) {
            pattern.append(chars.charAt(random.nextInt(chars.length())));
        }
        return pattern.toString();
    }
}
