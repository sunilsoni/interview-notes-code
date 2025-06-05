package com.interview.notes.code.year.y2025.may.google.test2;

import java.util.*;

public class DFSFiveWordPuzzleSolver {
    private static final int TARGET_WORDS = 5;
    private static final int ALPHABET_SIZE = 26;
    
    // Store preprocessed word information
    static class Word {
        String text;
        int bitMask;
        int uniqueLetterCount;
        
        Word(String text) {
            this.text = text;
            this.bitMask = createBitMask(text);
            this.uniqueLetterCount = Integer.bitCount(bitMask);
        }
    }
    
    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList(
            "CHUNK", "FJORD", "GYMPS", "VIBEX", "WALTZ",
            "QUICK", "BROWN", "JUMPS", "OVER", "LAZY"
        );
        
        long startTime = System.currentTimeMillis();
        List<List<String>> solutions = findSolutions(dictionary);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Found " + solutions.size() + " solutions in " + 
                          (endTime - startTime) + "ms");
        solutions.forEach(System.out::println);
    }

    public static List<List<String>> findSolutions(List<String> dictionary) {
        // Preprocess dictionary
        List<Word> words = preprocessDictionary(dictionary);
        
        // Sort words by unique letter count for better pruning
        words.sort((a, b) -> b.uniqueLetterCount - a.uniqueLetterCount);
        
        List<List<String>> results = new ArrayList<>();
        List<Word> currentPath = new ArrayList<>();
        int[] usedLetters = new int[1]; // Using array to pass by reference
        
        // Start DFS from each possible first word
        for (int i = 0; i < words.size(); i++) {
            if (words.size() - i < TARGET_WORDS) break; // Not enough words remaining
            dfs(words, i, currentPath, usedLetters, results);
        }
        
        return results;
    }

    private static void dfs(List<Word> words, 
                          int currentIndex,
                          List<Word> currentPath, 
                          int[] usedLetters,
                          List<List<String>> results) {
        
        // Base case: if we have 5 words
        if (currentPath.size() == TARGET_WORDS) {
            if (Integer.bitCount(usedLetters[0]) == 25) {
                results.add(currentPath.stream()
                    .map(w -> w.text)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll));
            }
            return;
        }
        
        // Get current letter count
        int currentLetterCount = Integer.bitCount(usedLetters[0]);
        
        // Calculate minimum required unique letters for remaining words
        int remainingWords = TARGET_WORDS - currentPath.size();
        int minRequiredLetters = 25 - currentLetterCount;
        
        // Early pruning: check if we can possibly reach 25 unique letters
        if (minRequiredLetters > remainingWords * 5) {
            return;
        }

        // Try each remaining word
        for (int i = currentIndex; i < words.size(); i++) {
            Word word = words.get(i);
            
            // Skip if this word doesn't add enough new letters
            int newLetters = Integer.bitCount(word.bitMask & ~usedLetters[0]);
            if (currentLetterCount + newLetters + 
                (remainingWords - 1) * 5 < 25) continue;
            
            // Skip if this word has letters we already used
            if ((usedLetters[0] & word.bitMask) != 0) continue;
            
            // Add word to path
            currentPath.add(word);
            int oldMask = usedLetters[0];
            usedLetters[0] |= word.bitMask;
            
            // Recurse
            dfs(words, i + 1, currentPath, usedLetters, results);
            
            // Backtrack
            currentPath.remove(currentPath.size() - 1);
            usedLetters[0] = oldMask;
        }
    }

    private static List<Word> preprocessDictionary(List<String> dictionary) {
        return dictionary.stream()
            .filter(word -> word.length() == 5)
            .map(String::toUpperCase)
            .distinct()
            .map(Word::new)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    private static int createBitMask(String word) {
        return word.chars()
            .map(ch -> ch - 'A')
            .map(pos -> 1 << pos)
            .reduce(0, (a, b) -> a | b);
    }

    // Helper method to generate test data
    private static List<String> generateTestDictionary(int size) {
        Random rand = new Random();
        Set<String> words = new HashSet<>();
        while (words.size() < size) {
            StringBuilder word = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                word.append((char) ('A' + rand.nextInt(26)));
            }
            words.add(word.toString());
        }
        return new ArrayList<>(words);
    }
}
