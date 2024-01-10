package com.interview.notes.code.months.year2023.nov23.test5;

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isWord = false; // Flag to mark the end of a word

    public TrieNode() {
    }

    public void insert(String word) {
        TrieNode node = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord = true;
    }
}
