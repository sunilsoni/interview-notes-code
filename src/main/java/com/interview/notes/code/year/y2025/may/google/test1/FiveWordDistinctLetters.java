package com.interview.notes.code.year.y2025.may.google.test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FiveWordDistinctLetters {

    // This will hold our final solution once found. We stop after first solution.
    private static List<String> solution = null;

    public static void main(String[] args) {
        // -------------------------
        // TEST CASE 1: tiny dictionary with an obvious solution
        // -------------------------
        List<String> smallDict1 = Arrays.asList(
                "chunk",  // c,h,u,n,k
                "fjord",  // f,j,o,r,d
                "nymph",  // n,y,m,p,h  <- note: duplicates n/h with chunk
                "vibex",  // v,i,b,e,x
                "waltz",  // w,a,l,t,z
                "gucks"   // g,u,c,k,s
        );
        // We know: "chunk fjord vibex waltz" is a valid 4-word combo (but we need 5 words).
        // Let's see if we can pick 5 that give 25 letters. Suppose the solver might find
        // ["fjord", "chunk", "vibex", "gucks", "waltz"] = 25 distinct letters?
        runTest("Test 1", smallDict1, true);

        // -------------------------
        // TEST CASE 2: no possible 25-distinct-letters combination
        //   - all words share letter 'a', so impossible to get 25 distinct
        // -------------------------
        List<String> smallDict2 = Arrays.asList(
                "apple",  // a,p,p,l,e
                "altar",  // a,l,t,a,r
                "agent",  // a,g,e,n,t
                "angle",  // a,n,g,l,e
                "amuse",  // a,m,u,s,e
                "abril"   // a,b,r,i,l
        );
        runTest("Test 2", smallDict2, false);

        // -------------------------
        // TEST CASE 3: Minimal reproducible example
        //    A tiny set that exactly has one 5-tuple of distinct letters
        // -------------------------
        List<String> smallDict3 = Arrays.asList(
                "abcde",  // a,b,c,d,e
                "fghij",  // f,g,h,i,j
                "klmno",  // k,l,m,n,o
                "pqrst",  // p,q,r,s,t
                "uvwxy",  // u,v,w,x,y
                "aaaab",  // invalid (duplicates)
                "zzzzz"   // invalid
        );
        runTest("Test 3", smallDict3, true);

        // -------------------------
        // TEST CASE 4: LARGER DICTIONARY (from file) to measure performance
        //    (Assume a file "words.txt" exists in the working directory, one word per line.)
        // -------------------------
        String pathToFullDictionary = "words.txt"; // change to your dictionary path
        try {
            List<String> fullDict = readDictionaryFromFile(pathToFullDictionary);
            long start = System.currentTimeMillis();
            List<String> result = findFiveWordsWith25DistinctLetters(fullDict);
            long duration = System.currentTimeMillis() - start;
            if (result != null) {
                System.out.println("Full-dictionary test: FOUND solution in " + duration + " ms => " + result);
            } else {
                System.out.println("Full-dictionary test: NO solution found in " + duration + " ms");
            }
        } catch (IOException e) {
            System.out.println("Could not read full dictionary file: " + e.getMessage());
        }
    }

    /**
     * Utility to run a single test: builds solver result and checks expected outcome.
     *
     * @param testName   Label for the test
     * @param dict       The list of dictionary words to test
     * @param shouldFind Whether we expect a valid 5-word tuple (true) or none (false)
     */
    private static void runTest(String testName, List<String> dict, boolean shouldFind) {
        System.out.println("=== " + testName + " ===");
        solution = null; // reset global solution placeholder
        List<String> result = findFiveWordsWith25DistinctLetters(dict);

        if (shouldFind) {
            if (result == null || result.size() != 5) {
                System.out.println("FAIL: Expected a 5-word solution, but got " + result);
            } else if (!verifySolution(result)) {
                System.out.println("FAIL: Returned words do not cover 25 distinct letters: " + result);
            } else {
                System.out.println("PASS: Found valid solution: " + result);
            }
        } else {
            if (result != null) {
                System.out.println("FAIL: Expected NO solution, but got " + result);
            } else {
                System.out.println("PASS: Correctly found no solution.");
            }
        }
    }

    /**
     * Reads all lines from a given file path, returns as a List<String>.
     * Each line is assumed to be one word.
     *
     * @param filename path to the file
     * @return List of words (with whitespace trimmed)
     * @throws IOException if file I/O fails
     */
    private static List<String> readDictionaryFromFile(String filename) throws IOException {
        List<String> allWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    allWords.add(line);
                }
            }
        }
        return allWords;
    }

    /**
     * The core solver:
     * 1. Normalize dictionary to lowercase.
     * 2. Filter to 5-letter words with all distinct letters.
     * 3. Compute mask for each word.
     * 4. Backtracking search to find any combination of 5 words covering 25 distinct letters.
     *
     * @param dictionary A list of all dictionary words (possibly large)
     * @return A list of 5 words that cover 25 distinct letters, or null if none.
     */
    public static List<String> findFiveWordsWith25DistinctLetters(List<String> dictionary) {
        // 1. Filter and collect only valid 5-letter words with distinct letters.
        List<WordMask> wordsWithMasks = dictionary.stream()
                // Normalize to lowercase, skip null/empty
                .filter(Objects::nonNull)
                .map(String::trim)
                .map(String::toLowerCase)
                // Keep only words of length exactly 5
                .filter(w -> w.length() == 5)
                // Compute bitmask and check no duplicate letters within word
                .map(FiveWordDistinctLetters::toWordMask) // returns null if duplicates or invalid letters
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // If fewer than 5 valid words remain, we cannot form a 5-tuple
        if (wordsWithMasks.size() < 5) {
            return null;
        }

        // Optional: sort by mask (or by letter frequency heuristic). For now, keep insertion order.
        // Collections.shuffle(wordsWithMasks); // could randomize to get a random solution each run

        // 2. Kick off backtracking. We want depth=5, usedMask initially = 0, and an empty currentList.
        backtrack(wordsWithMasks, 0, 0, new ArrayList<>());

        // Global 'solution' gets filled if we find one. Return it.
        return solution;
    }

    /**
     * Convert a 5-letter word to a WordMask. If the word has any non-[a-z] char
     * or has duplicate letters, return null. Otherwise return WordMask(word, mask).
     */
    private static WordMask toWordMask(String w) {
        int mask = 0;
        for (char c : w.toCharArray()) {
            if (c < 'a' || c > 'z') {
                return null; // invalid character
            }
            int bit = 1 << (c - 'a');
            if ((mask & bit) != 0) {
                return null; // duplicate letter inside this word, skip
            }
            mask |= bit;
        }
        // At this point, mask has exactly 5 bits set (since w.length()==5 and no duplicates)
        return new WordMask(w, mask);
    }

    /**
     * Backtracking helper:
     * - startIdx: next index in wordsWithMasks to try
     * - depth: how many words chosen so far (0..5)
     * - usedMask: the OR of bitmasks of chosen words so far
     * - currentCombo: list of chosen words so far
     * <p>
     * Once depth==5, if usedMask has exactly 25 bits set → record solution & stop.
     */
    private static void backtrack(List<WordMask> wordsWithMasks, int startIdx, int depth, List<String> currentCombo) {
        // If we've already found a solution, no need to search further.
        if (solution != null) {
            return;
        }

        // If we have chosen 5 words...
        if (depth == 5) {
            // Check if these 5 words cover exactly 25 distinct letters
            if (Integer.bitCount(getMaskForList(currentCombo, wordsWithMasks)) == 25) {
                // Found a valid solution: record it
                solution = new ArrayList<>(currentCombo);
            }
            return;
        }

        // Otherwise, try to pick word i from startIdx .. end-of-list
        int listSize = wordsWithMasks.size();
        for (int i = startIdx; i < listSize; i++) {
            WordMask wm = wordsWithMasks.get(i);
            int wordMask = wm.mask;

            // If this word shares any letter with what we already used → skip
            // usedMask can be reconstructed by OR-ing all masks in currentCombo, but we pass it down
            int usedMask = getMaskForList(currentCombo, wordsWithMasks);
            if ((usedMask & wordMask) != 0) {
                continue; // letter conflict
            }

            // Combine masks to form new usedMask
            int newUsedMask = usedMask | wordMask;

            // Optional pruning: even if we fill the next (5−depth−1) words with ALL new 5-letter words,
            // can we ever reach 25 distinct letters? We could estimate maximum additional new bits left,
            // but for depth only up to 5, this is often not necessary. We skip extra pruning here.

            // Choose this word
            currentCombo.add(wm.word);

            // Recurse to pick the next word (from i+1 onward)
            backtrack(wordsWithMasks, i + 1, depth + 1, currentCombo);

            // After recursion returns, if solution is found, we can stop immediately
            if (solution != null) {
                return;
            }

            // Otherwise, un-choose this word and continue
            currentCombo.remove(currentCombo.size() - 1);
        }
    }

    /**
     * Helper to compute the combined bitmask of all words in currentCombo.
     * We look up each word's mask in wordsWithMasks. In a more optimized version,
     * we could carry the usedMask through recursion to avoid recomputing. For clarity,
     * we recompute from scratch here.
     */
    private static int getMaskForList(List<String> combo, List<WordMask> wordsWithMasks) {
        int combined = 0;
        // Build a temporary map from word->mask for quick lookup
        // (In practice, we might pass usedMask down to avoid recomputing.)
        Map<String, Integer> wordToMask = new HashMap<>();
        for (WordMask wm : wordsWithMasks) {
            wordToMask.put(wm.word, wm.mask);
        }
        for (String w : combo) {
            combined |= wordToMask.get(w);
        }
        return combined;
    }

    /**
     * Verifies that the given list of 5 words indeed covers exactly 25 distinct letters.
     * Returns true if valid, false otherwise.
     */
    private static boolean verifySolution(List<String> fiveWords) {
        if (fiveWords == null || fiveWords.size() != 5) {
            return false;
        }
        Set<Character> seen = new HashSet<>();
        for (String w : fiveWords) {
            if (w.length() != 5) {
                return false;
            }
            for (char c : w.toCharArray()) {
                if (c < 'a' || c > 'z') {
                    return false;
                }
                seen.add(c);
            }
        }
        // We need exactly 25 distinct letters
        return seen.size() == 25;
    }

    /**
     * A small helper class that pairs a word with its 26-bit bitmask.
     */
    private static class WordMask {
        String word;   // the 5-letter word
        int mask;      // bitmask: bit 0='a', bit1='b', ..., bit25='z'

        WordMask(String word, int mask) {
            this.word = word;
            this.mask = mask;
        }
    }
}