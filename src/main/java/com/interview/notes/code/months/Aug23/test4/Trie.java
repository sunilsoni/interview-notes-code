package com.interview.notes.code.months.Aug23.test4;

import java.util.ArrayList;
import java.util.List;

/*
In Jav a how to desing
Dictionary of words

input string i want to atch and return that words

Input word is : abpplead
So out put:
Apple
Bad
LAD
LEAD
 */

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words into Trie
        trie.insert("apple");
        trie.insert("bad");
        trie.insert("lad");
        trie.insert("lead");

        // Test findMatchingWords
        String input = "abpplead";
        List<String> result = trie.findMatchingWords(input);

        // Print result
        for (String word : result) {
            System.out.println(word);
        }
    }

    // Insert a word into Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            node.children.putIfAbsent(ch, new TrieNode());
            node = node.children.get(ch);
        }
        node.isWord = true;
    }

    // DFS function to find all matching words
    private void dfs(TrieNode node, String prefix, String remaining, List<String> result) {
        if (node.isWord) {
            result.add(prefix);
        }

        for (char ch : remaining.toCharArray()) {
            TrieNode child = node.children.get(ch);
            if (child != null) {
                dfs(child, prefix + ch, remaining.replaceFirst(Character.toString(ch), ""), result);
            }
        }
    }

    // Find all matching words
    public List<String> findMatchingWords(String s) {
        List<String> result = new ArrayList<>();
        dfs(root, "", s, result);
        return result;
    }
}
