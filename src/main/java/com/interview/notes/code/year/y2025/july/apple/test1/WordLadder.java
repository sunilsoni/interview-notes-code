package com.interview.notes.code.year.y2025.july.apple.test1;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        // Test cases
        test1();
        test2();
        test3();
    }

    private static void test1() {
        System.out.println("Test 1:");
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<List<List<String>>> result = findLaddersGroupedByLength(beginWord, endWord, wordList, 5);
        printResults(result);
    }

    private static void test2() {
        System.out.println("\nTest 2:");
        String beginWord = "red";
        String endWord = "tax";
        List<String> wordList = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        List<List<List<String>>> result = findLaddersGroupedByLength(beginWord, endWord, wordList, 2);
        printResults(result);
    }

    private static void test3() {
        System.out.println("\nTest 3:");
        String beginWord = "cat";
        String endWord = "dog";
        List<String> wordList = Arrays.asList("cat", "cot", "cog", "dog", "dat", "dot");
        List<List<List<String>>> result = findLaddersGroupedByLength(beginWord, endWord, wordList, 3);
        printResults(result);
    }

    public static List<List<List<String>>> findLaddersGroupedByLength(String beginWord, String endWord,
                                                                      List<String> wordList, int multiple) {
        List<List<String>> allPaths = findLadders(beginWord, endWord, wordList);

        // Group paths by length
        Map<Integer, List<List<String>>> lengthToPathsMap = new HashMap<>();
        for (List<String> path : allPaths) {
            lengthToPathsMap.computeIfAbsent(path.size(), k -> new ArrayList<>()).add(path);
        }

        // Filter paths by multiple and sort by length
        List<List<List<String>>> result = new ArrayList<>();
        lengthToPathsMap.keySet().stream()
                .sorted()
                .filter(length -> length % multiple == 0)
                .forEach(length -> result.add(lengthToPathsMap.get(length)));

        return result;
    }

    private static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) return result;

        Map<String, List<List<String>>> paths = new HashMap<>();
        paths.put(beginWord, List.of(Collections.singletonList(beginWord)));
        Set<String> currentLevel = new HashSet<>(Collections.singletonList(beginWord));

        while (!currentLevel.isEmpty() && !currentLevel.contains(endWord)) {
            Set<String> nextLevel = new HashSet<>();
            dictionary.removeAll(currentLevel);

            for (String word : currentLevel) {
                char[] wordArray = word.toCharArray();
                for (int i = 0; i < wordArray.length; i++) {
                    char original = wordArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        wordArray[i] = c;
                        String newWord = new String(wordArray);

                        if (dictionary.contains(newWord)) {
                            nextLevel.add(newWord);
                            List<List<String>> newPaths = new ArrayList<>();
                            for (List<String> path : paths.get(word)) {
                                List<String> newPath = new ArrayList<>(path);
                                newPath.add(newWord);
                                newPaths.add(newPath);
                            }
                            paths.computeIfAbsent(newWord, k -> new ArrayList<>()).addAll(newPaths);
                        }
                    }
                    wordArray[i] = original;
                }
            }
            currentLevel = nextLevel;
        }

        return paths.getOrDefault(endWord, new ArrayList<>());
    }

    private static void printResults(List<List<List<String>>> results) {
        if (results.isEmpty()) {
            System.out.println("No paths found with required length multiple");
            return;
        }

        for (List<List<String>> pathsOfLength : results) {
            System.out.println("Paths of length " + pathsOfLength.get(0).size() + ":");
            for (List<String> path : pathsOfLength) {
                System.out.println("  " + path);
            }
        }
    }
}
