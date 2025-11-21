package com.interview.notes.code.year.y2024.aug24.test21;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();

        // Test case 1
        String beginWord1 = "hit";
        String endWord1 = "cog";
        List<String> wordList1 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(solution.findLadders(beginWord1, endWord1, wordList1));

        // Test case 2
        String beginWord2 = "hit";
        String endWord2 = "cog";
        List<String> wordList2 = Arrays.asList("hot", "dot", "dog", "lot", "log");
        System.out.println(solution.findLadders(beginWord2, endWord2, wordList2));

        // Additional test case
        String beginWord3 = "red";
        String endWord3 = "tax";
        List<String> wordList3 = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        System.out.println(solution.findLadders(beginWord3, endWord3, wordList3));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!wordSet.contains(endWord)) return res;

        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();

        bfs(beginWord, endWord, wordSet, graph, distances);
        dfs(beginWord, endWord, graph, distances, new ArrayList<>(List.of(beginWord)), res);

        return res;
    }

    private void bfs(String start, String end, Set<String> wordSet,
                     Map<String, List<String>> graph, Map<String, Integer> distances) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distances.put(start, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                int currDistance = distances.get(word);
                List<String> neighbors = getNeighbors(word, wordSet);

                for (String neighbor : neighbors) {
                    graph.putIfAbsent(word, new ArrayList<>());
                    graph.get(word).add(neighbor);

                    if (!distances.containsKey(neighbor)) {
                        distances.put(neighbor, currDistance + 1);
                        if (neighbor.equals(end)) {
                            foundEnd = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            if (foundEnd) break;
        }
    }

    private void dfs(String word, String end, Map<String, List<String>> graph,
                     Map<String, Integer> distances, List<String> path, List<List<String>> res) {
        if (word.equals(end)) {
            res.add(new ArrayList<>(path));
            return;
        }

        if (!graph.containsKey(word)) return;

        for (String neighbor : graph.get(word)) {
            if (distances.get(neighbor) == distances.get(word) + 1) {
                path.add(neighbor);
                dfs(neighbor, end, graph, distances, path, res);
                path.remove(path.size() - 1);
            }
        }
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = original;
        }
        return neighbors;
    }
}
