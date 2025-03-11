package com.interview.notes.code.year.y2025.march.common.test1;

import java.util.*;

public class WordLadder {
    
    /**
     * Finds the shortest transformation sequence from start word to end word.
     * @param beginWord The starting word
     * @param endWord The target word
     * @param dictionary A method that returns whether a word is valid
     * @return The shortest sequence of words from start to end, or empty list if impossible
     */
    public List<String> findWordLadder(String beginWord, String endWord, Set<String> wordList) {
        // Edge case: if end word is not in dictionary, no solution exists
        if (!wordList.contains(endWord)) {
            return Collections.emptyList();
        }
        
        // Queue for BFS traversal
        Queue<List<String>> queue = new LinkedList<>();
        // Initialize with start word
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        queue.add(path);
        
        // Keep track of visited words to avoid cycles
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        
        while (!queue.isEmpty()) {
            List<String> currentPath = queue.poll();
            String currentWord = currentPath.get(currentPath.size() - 1);
            
            // If we've reached the end word, return the path
            if (currentWord.equals(endWord)) {
                return currentPath;
            }
            
            // Try changing each character position to find valid next words
            char[] wordChars = currentWord.toCharArray();
            for (int i = 0; i < wordChars.length; i++) {
                char originalChar = wordChars[i];
                
                // Try all possible letter substitutions
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == originalChar) continue;
                    
                    wordChars[i] = c;
                    String newWord = new String(wordChars);
                    
                    // Check if the new word is valid and not visited
                    if (wordList.contains(newWord) && !visited.contains(newWord)) {
                        // Create a new path with the new word
                        List<String> newPath = new ArrayList<>(currentPath);
                        newPath.add(newWord);
                        queue.add(newPath);
                        visited.add(newWord);
                    }
                }
                
                // Restore the original character for next iteration
                wordChars[i] = originalChar;
            }
        }
        
        // If no path found
        return Collections.emptyList();
    }
    
    /**
     * Finds length of shortest path (number of transformations needed)
     */
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {
        List<String> path = findWordLadder(beginWord, endWord, wordList);
        return path.isEmpty() ? 0 : path.size() - 1; // -1 because we want transformations count
    }

    // Test method to verify solution
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        
        // Test case 1: hit -> cog
        Set<String> dict1 = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        int result1 = solution.ladderLength("hit", "cog", dict1);
        System.out.println("Test 1: " + (result1 == 5 ? "PASS" : "FAIL") + 
                         " (Expected: 5, Got: " + result1 + ")");
        // Path should be: hit -> hot -> dot -> dog -> cog
        
        // Test case 2: no solution
        Set<String> dict2 = new HashSet<>(Arrays.asList("hot", "dot", "dog", "lot", "log"));
        int result2 = solution.ladderLength("hit", "cog", dict2);
        System.out.println("Test 2: " + (result2 == 0 ? "PASS" : "FAIL") + 
                         " (Expected: 0, Got: " + result2 + ")");
        
        // Test case 3: one step
        Set<String> dict3 = new HashSet<>(Arrays.asList("dog"));
        int result3 = solution.ladderLength("dog", "dog", dict3);
        System.out.println("Test 3: " + (result3 == 0 ? "PASS" : "FAIL") + 
                         " (Expected: 0, Got: " + result3 + ")");
        
        // Test case 4: large dictionary
        Set<String> largeDictionary = generateLargeDictionary();
        long startTime = System.currentTimeMillis();
        int result4 = solution.ladderLength("tale", "fork", largeDictionary);
        long endTime = System.currentTimeMillis();
        System.out.println("Test 4 (Large Dictionary): Result = " + result4 + 
                         ", Time taken: " + (endTime - startTime) + "ms");
    }
    
    // Helper method to generate a large dictionary for testing performance
    private static Set<String> generateLargeDictionary() {
        Set<String> dictionary = new HashSet<>();
        // Add a few thousand 4-letter words
        String[] commonWords = {"tale", "talk", "tall", "tank", "tape", "task", "team", "tear", 
                              "tech", "tell", "tend", "term", "test", "text", "than", "that", 
                              "them", "then", "they", "thin", "this", "thus", "tide", "tile", 
                              "time", "tiny", "tire", "toll", "tone", "tool", "door", "fork"};
        
        dictionary.addAll(Arrays.asList(commonWords));
        
        // Generate more words by modifying existing ones
        List<String> baseWords = new ArrayList<>(dictionary);
        for (String word : baseWords) {
            for (int i = 0; i < word.length(); i++) {
                char[] chars = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    chars[i] = c;
                    dictionary.add(new String(chars));
                    if (dictionary.size() > 5000) break;
                }
                if (dictionary.size() > 5000) break;
            }
            if (dictionary.size() > 5000) break;
        }
        
        return dictionary;
    }
}
