package com.interview.notes.code.year.y2025.may.google.test2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FiveWordDistinctLettersSimple {

    // Once we find any valid 5-word combination, we store it here and stop searching.
    private static List<String> solution = null;

    public static void main(String[] args) {
        // --------- TEST CASE 1: should find a solution ----------
        List<String> dict1 = Arrays.asList(
                "chunk",  // c, h, u, n, k
                "fjord",  // f, j, o, r, d
                "vibex",  // v, i, b, e, x
                "gucks",  // g, u, c, k, s
                "waltz"   // w, a, l, t, z
                // One valid 5-word combo: ["chunk","fjord","vibex","gucks","waltz"]
                // covers 25 distinct letters (missing only 'm').
        );
        runTest("Test 1", dict1, true);

        // --------- TEST CASE 2: should NOT find a solution ----------
        List<String> dict2 = Arrays.asList(
                "apple",  // has duplicates → filtered out
                "altar",  // all share 'a'
                "agent",  // all share 'a'
                "angle",  // all share 'a'
                "amuse",  // all share 'a'
                "abril"   // all share 'a'
        );
        runTest("Test 2", dict2, false);

        // --------- TEST CASE 3: minimal example that DOES have a solution ----------
        List<String> dict3 = Arrays.asList(
                "abcde",  // a, b, c, d, e
                "fghij",  // f, g, h, i, j
                "klmno",  // k, l, m, n, o
                "pqrst",  // p, q, r, s, t
                "uvwxy",  // u, v, w, x, y
                // "aaaaa", "zzzzz" would be filtered out automatically
                "aaaaa",  // invalid (duplicates)
                "zzzzz"   // invalid (duplicates)
        );
        runTest("Test 3", dict3, true);

        // --------- TEST CASE 4: large dictionary from file (words.txt) ----------
        String fullDictPath = "words.txt";
        try {
            List<String> fullDict = readDictionaryFromFile(fullDictPath);
            long t0 = System.currentTimeMillis();
            List<String> result = findFiveWordsWith25DistinctLetters(fullDict);
            long t1 = System.currentTimeMillis();
            if (result != null) {
                System.out.println("Full-dict test: FOUND solution in " + (t1 - t0) + " ms → " + result);
            } else {
                System.out.println("Full-dict test: NO solution found in " + (t1 - t0) + " ms");
            }
        } catch (IOException e) {
            System.out.println("Error reading dictionary: " + e.getMessage());
        }
    }

    /**
     * Reads every non-blank line from 'filename' and returns a List<String>.
     * Each line is treated as one word. If the file has M lines of average length L,
     * this costs roughly O(M·L).
     */
    private static List<String> readDictionaryFromFile(String filename) throws IOException {
        List<String> words = new ArrayList<>();                       // Allocate list
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {                  // O(M) iterations
                line = line.trim();                                   // O(L)
                if (!line.isEmpty()) {
                    words.add(line);                                  // amortized O(1)
                }
            }
        }
        return words;                                                 // Return list
    }

    /**
     * Runs the solver on 'dictionary', then compares result against expectedFind.
     * Prints PASS or FAIL accordingly.
     */
    private static void runTest(String testName, List<String> dictionary, boolean expectedFind) {
        System.out.println("=== " + testName + " ===");
        solution = null;                                              // Reset any previous solution
        List<String> result = findFiveWordsWith25DistinctLetters(dictionary);

        if (expectedFind) {
            if (result == null || result.size() != 5) {
                System.out.println("FAIL – expected a 5-word solution, but got: " + result);
            } else if (!verifySolution(result)) {
                System.out.println("FAIL – returned words do not cover 25 distinct letters: " + result);
            } else {
                System.out.println("PASS – found valid solution: " + result);
            }
        } else {
            if (result != null) {
                System.out.println("FAIL – expected NO solution, but got: " + result);
            } else {
                System.out.println("PASS – correctly found no solution.");
            }
        }
    }

    /**
     * The main solver. Steps:
     * 1. Normalize dictionary to lowercase, filter to 5-letter words with distinct letters.
     * 2. Convert each to a WordLetters (word + Set<Character>).
     * 3. Run DFS to try all combinations of 5 words, carrying a Set<Character> of used letters.
     * 4. As soon as we find depth=5 with 25 distinct letters, we store it in 'solution'.
     * <p>
     * Worst-case time: O(N) to filter, plus O(W^5) to DFS (where W is # of valid 5-letter words).
     * In practice, the pruning (reject as soon as any letter overlaps) makes it much faster.
     */
    public static List<String> findFiveWordsWith25DistinctLetters(List<String> dictionary) {
        // 1) Filter & map: keep only 5-letter words with all distinct letters.
        List<WordLetters> wordList = dictionary.stream()
                .filter(Objects::nonNull)                                // remove nulls
                .map(String::trim)                                        // strip whitespace
                .map(String::toLowerCase)                                 // normalize to lowercase
                .filter(w -> w.length() == 5)                             // keep only length==5
                .map(FiveWordDistinctLettersSimple::toWordLetters)        // build WordLetters or null if invalid
                .filter(Objects::nonNull)                                 // remove invalid ones
                .collect(Collectors.toList());                            // collect into List<WordLetters>

        // If fewer than 5 valid words remain, no solution is possible.
        if (wordList.size() < 5) {
            return null;
        }

        // 2) Kick off DFS with an empty "used letters" set and an empty combo.
        dfs(wordList, 0, new HashSet<>(), new ArrayList<>());

        // 3) At this point, 'solution' is either a valid 5-word list or remains null.
        return solution;
    }

    /**
     * Converts a 5-letter string 'w' into a WordLetters (word + Set of its chars).
     * Returns null if:
     * - any character is not in [a..z], or
     * - the word has any repeated letter inside it.
     * <p>
     * Time: O(5) = O(1), since length is exactly 5.
     */
    private static WordLetters toWordLetters(String w) {
        Set<Character> chars = new HashSet<>();                     // to collect unique letters
        for (int i = 0; i < 5; i++) {                                // exactly 5 iterations
            char c = w.charAt(i);                                    // O(1)
            if (c < 'a' || c > 'z') {                                // if not a–z
                return null;                                         // reject
            }
            if (!chars.add(c)) {                                     // add() returns false if already present
                return null;                                         // duplicate letter → reject
            }
        }
        // If we reach here, 'chars' has exactly 5 distinct letters.
        return new WordLetters(w, chars);
    }

    /**
     * Recursive DFS.
     *
     * @param wordList List of WordLetters (word + its letter set).
     * @param startIdx Next index in wordList we can pick from.
     * @param used     Set<Character> of letters already chosen so far.
     * @param combo    List<String> of chosen words so far.
     *                 <p>
     *                 At each level, we try to add one more word, as long as it shares no letter with 'used'.
     *                 As soon as combo.size() == 5 and used.size() == 25, we record 'combo' in 'solution'.
     *                 We then stop descending further.
     */
    private static void dfs(
            List<WordLetters> wordList,
            int startIdx,
            Set<Character> used,
            List<String> combo
    ) {
        // If we've already found a solution, bail out immediately.
        if (solution != null) {
            return;
        }

        // If we've chosen 5 words, check if 'used' has 25 distinct letters.
        if (combo.size() == 5) {
            if (used.size() == 25) {
                // FOUND a valid combination → store a copy and stop.
                solution = new ArrayList<>(combo);
            }
            return;
        }

        // Otherwise, try picking the next word from indices [startIdx..end of list).
        int W = wordList.size();
        for (int i = startIdx; i < W; i++) {
            WordLetters wl = wordList.get(i);
            Set<Character> lettersOfWord = wl.letters;            // this word’s 5 letters

            // Quick overlap check: does 'used' already contain any letter in 'lettersOfWord'?
            boolean conflict = false;
            for (char c : lettersOfWord) {
                if (used.contains(c)) {                            // O(1) per check
                    conflict = true;
                    break;
                }
            }
            if (conflict) {
                continue;  // skip this word since it shares a letter
            }

            // No conflict: we can choose this word.
            combo.add(wl.word);                                   // pick the word
            // Add its letters to 'used'. We must remove them on backtrack.
            for (char c : lettersOfWord) {
                used.add(c);                                      // O(1) per letter
            }

            // Recurse, forcing the next word to come from i+1 onwards.
            dfs(wordList, i + 1, used, combo);

            // If solution was found deeper, we can bail out immediately.
            if (solution != null) {
                return;
            }

            // Backtrack: remove this word and its letters, then try the next i.
            combo.remove(combo.size() - 1);                       // O(1)
            for (char c : lettersOfWord) {
                used.remove(c);                                   // O(1) per letter
            }
        }
    }

    /**
     * Verifies that the returned 5-word list covers exactly 25 distinct letters.
     * Time: O(5 words × 5 letters) = O(25) = O(1).
     */
    private static boolean verifySolution(List<String> fiveWords) {
        if (fiveWords == null || fiveWords.size() != 5) {
            return false;
        }
        Set<Character> seen = new HashSet<>();                     // to collect all letters
        for (String w : fiveWords) {                               // iterate 5 words
            if (w.length() != 5) {
                return false;                                      // each must be length 5
            }
            for (char c : w.toCharArray()) {                        // 5 letters per word
                if (c < 'a' || c > 'z') {
                    return false;                                  // must be lowercase letters
                }
                seen.add(c);
            }
        }
        // Finally, we need exactly 25 different letters
        return (seen.size() == 25);
    }

    /**
     * A helper class that pairs a 5-letter word with its Set<Character>.
     * We use this so that, during DFS, we can quickly check letter-overlap
     * by calling set operations, rather than recomputing characters each time.
     */
    private static class WordLetters {
        final String word;               // the 5-letter word itself
        final Set<Character> letters;    // a Set of its 5 distinct letters

        WordLetters(String w, Set<Character> letters) {
            this.word = w;
            this.letters = letters;
        }
    }
}