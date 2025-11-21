package com.interview.notes.code.year.y2025.may.google.test3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/*
**Problem Statement:**

You are given a complete dictionary as a list with **all possible English words**.

**Task:**

Find a **tuple of 5 five-lettered words** that together contain **25 distinct letters** from the English alphabet.

There can be **many such answers**.

**Examples:**

* `'chunk fjord gymps vibex waltz'`
* `'fjord gucks nymph vibex waltz'`
* ...
 */

public class FiveWordDistinctLettersSimple {

    // Once we find one valid 5-word combo, we store it here and stop searching.
    private static List<String> solution = null;

    public static void main(String[] args) {
        // --------- TEST CASE 1: corrected, now has a valid solution ----------
        List<String> dict1 = Arrays.asList(
                "chunk",   // c,h,u,n,k
                "fjord",   // f,j,o,r,d
                "gymps",   // g,y,m,p,s
                "vibex",   // v,i,b,e,x
                "waltz"    // w,a,l,t,z
                // These five cover exactly 25 letters (only 'q' is missing).
        );
        runTest("Test 1", dict1, true);

        // --------- TEST CASE 2: correctly has no solution ----------
        List<String> dict2 = Arrays.asList(
                "apple",   // duplicates inside → filtered out
                "altar",   // all share 'a'
                "agent",   // all share 'a'
                "angle",   // all share 'a'
                "amuse",   // all share 'a'
                "abril"    // all share 'a'
        );
        runTest("Test 2", dict2, false);

        // --------- TEST CASE 3: minimal example that does have a solution ----------
        List<String> dict3 = Arrays.asList(
                "abcde",   // a,b,c,d,e
                "fghij",   // f,g,h,i,j
                "klmno",   // k,l,m,n,o
                "pqrst",   // p,q,r,s,t
                "uvwxy",   // u,v,w,x,y
                "aaaaa",   // invalid (duplicates)
                "zzzzz"    // invalid (duplicates)
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
     * Read every nonblank line from 'filename' into a List<String>.
     */
    private static List<String> readDictionaryFromFile(String filename) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
        }
        return words;
    }

    /**
     * Run solver on 'dictionary', compare against expectedFind, and print PASS/FAIL.
     */
    private static void runTest(String testName, List<String> dictionary, boolean expectedFind) {
        System.out.println("=== " + testName + " ===");
        solution = null;
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
     * Main solver: filter to 5-letter words with 5 distinct letters, then DFS.
     */
    public static List<String> findFiveWordsWith25DistinctLetters(List<String> dictionary) {
        // 1) Filter & map: keep only 5-letter words with distinct letters.
        List<WordLetters> wordList = dictionary.stream()
                .filter(Objects::nonNull)                                // drop nulls
                .map(String::trim)                                        // strip whitespace
                .map(String::toLowerCase)                                 // normalize to lowercase
                .filter(w -> w.length() == 5)                             // only length‐5
                .map(FiveWordDistinctLettersSimple::toWordLetters)        // build WordLetters or null
                .filter(Objects::nonNull)                                 // drop invalid
                .collect(Collectors.toList());                            // collect

        // If fewer than 5 survive, no solution is possible.
        if (wordList.size() < 5) {
            return null;
        }

        // 2) DFS with an empty “used letters” set and empty combo.
        dfs(wordList, 0, new HashSet<>(), new ArrayList<>());

        return solution;
    }

    /**
     * Convert a 5-letter string w into WordLetters (word + Set<Character>).
     * Return null if any char is not [a..z] or there’s a duplicate.
     */
    private static WordLetters toWordLetters(String w) {
        Set<Character> chars = new HashSet<>();
        for (int i = 0; i < 5; i++) {
            char c = w.charAt(i);
            if (c < 'a' || c > 'z') {
                return null;  // invalid character
            }
            if (!chars.add(c)) {
                return null;  // duplicate letter inside the word
            }
        }
        return new WordLetters(w, chars);
    }

    /**
     * DFS to pick 5 words. ‘used’ is the set of letters already chosen.
     * ‘combo’ holds the chosen words so far.
     */
    private static void dfs(
            List<WordLetters> wordList,
            int startIdx,
            Set<Character> used,
            List<String> combo
    ) {
        if (solution != null) {
            return;  // already found one
        }
        if (combo.size() == 5) {
            if (used.size() == 25) {
                solution = new ArrayList<>(combo); // record and stop
            }
            return;
        }

        int W = wordList.size();
        for (int i = startIdx; i < W; i++) {
            WordLetters wl = wordList.get(i);
            Set<Character> lettersOfWord = wl.letters;

            // Check for any overlap with ‘used’:
            boolean conflict = false;
            for (char c : lettersOfWord) {
                if (used.contains(c)) {
                    conflict = true;
                    break;
                }
            }
            if (conflict) {
                continue; // skip if any letter is already used
            }

            // No conflict → choose this word:
            combo.add(wl.word);
            for (char c : lettersOfWord) {
                used.add(c);
            }

            // Recurse from the next index
            dfs(wordList, i + 1, used, combo);
            if (solution != null) {
                return; // once found, bail out completely
            }

            // Backtrack: remove wl.word and its letters
            combo.remove(combo.size() - 1);
            for (char c : lettersOfWord) {
                used.remove(c);
            }
        }
    }

    /**
     * Confirm that the returned 5 words do indeed cover exactly 25 distinct letters.
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
        return (seen.size() == 25);
    }

    /**
         * Helper class: a 5-letter word plus its Set<Character>.
         */
        private record WordLetters(String word, Set<Character> letters) {
    }
}